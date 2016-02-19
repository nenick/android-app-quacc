package de.nenick.quacc.database.testsupport.testdata;

import android.content.ContentUris;
import android.net.Uri;

import java.lang.reflect.Method;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.provider.base.AbstractSelection;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

public class GenericQueryByIdSpecification<T extends AbstractSelection> extends QuerySpecification<T> {
    private long entryId;
    private final Class<T> selection;

    public GenericQueryByIdSpecification(long entryId, Class<T> selection) {
        this.entryId = entryId;
        this.selection = selection;
    }

    @Override
    public T toSelection() {
        ProxyFactory factoryForSelection = new ProxyFactory();
        factoryForSelection.setSuperclass(selection);

        Class classForSelection = factoryForSelection.createClass();
        try {
            Object proxy = classForSelection.newInstance();
            ((Proxy) proxy).setHandler(new MethodHandler() {
                public Object invoke(Object arg0, Method method, Method arg2, Object[] arg3) throws Throwable {
                    String name = method.getName();
                    if (name.equals("uri")) {
                        return ContentUris.withAppendedId((Uri) arg2.invoke(arg0, arg3), entryId);
                    }
                    return arg2.invoke(arg0, arg3);
                }
            });
            //noinspection unchecked
            return (T) proxy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
