package de.nenick.quacc.core.dagger;

import org.androidannotations.annotations.EBean;

import dagger.ObjectGraph;

@EBean
public class DaggerSupport {

    private static ObjectGraph graph;

    public static void init(Object... modules) {
        graph = ObjectGraph.create(modules);
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}
