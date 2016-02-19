package de.nenick.quacc.database.testsupport.testdata;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.provider.base.AbstractSelection;

public class GenericQueryAllSpecification<T extends AbstractSelection> extends QuerySpecification<T> {
    private final Class<T> selection;

    public GenericQueryAllSpecification(Class<T> selection) {
        this.selection = selection;
    }

    @Override
    public T toSelection() {
        try {
            return selection.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
