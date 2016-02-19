package de.nenick.quacc.database.testsupport.testdata;

import java.lang.reflect.Method;

public class ProxyListener implements java.lang.reflect.InvocationHandler {

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {

        Object result = null;

        try {

            // Prints the method being invoked

            System.out.print("begin method " + m.getName() + "(");

            for (int i = 0; i < args.length; i++) {
                if (i > 0) System.out.print(",");

                System.out.print(" " + args[i].toString());

            }

            System.out.println(" )");

            // if the method name equals some method's name then call your method

            if (m.getName().equals("someMethod")) {

                result = myMethod(args[0]);

            }

        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {
            System.out.println("end method " + m.getName());
        }
        return result;
    }

    Object myMethod(Object o) {

        return null;

    }
}
