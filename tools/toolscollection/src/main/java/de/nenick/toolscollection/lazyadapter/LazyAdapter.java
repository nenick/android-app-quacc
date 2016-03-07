package de.nenick.toolscollection.lazyadapter;

import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Ensure correct instance state restoration on configuration changes (e.g. rotation).
 * <p/>
 * First call inject before you start loading data for adapter or it may miss initial data event.
 * <p/>
 * This class sets the adapter once when data loading is ready.
 * Recycler view tries but can not restore instance state with an empty adapter (data source).
 */
public abstract class LazyAdapter {

    public static void inject(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        new RecyclerViewInjector(recyclerView, adapter);
    }

    public static void inject(AdapterView adapterView, Adapter adapter) {
        new AdapterViewInjector(adapterView, adapter);
    }

    private static class RecyclerViewInjector extends RecyclerView.AdapterDataObserver {

        private RecyclerView recyclerView;
        private RecyclerView.Adapter adapter;

        public RecyclerViewInjector(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
            this.recyclerView = recyclerView;
            this.adapter = adapter;

            // detect when adapter has first content and adapter can be added
            this.adapter.registerAdapterDataObserver(this);
        }

        @Override
        public void onChanged() {
            // adapter should have content and recycler view can restore his last state it
            recyclerView.setAdapter(adapter);

            // job is done ...
            adapter.unregisterAdapterDataObserver(this);
        }
    }

    private static class AdapterViewInjector extends DataSetObserver {
        private AdapterView adapterView;
        private Adapter adapter;

        public AdapterViewInjector(AdapterView adapterView, Adapter adapter) {
            this.adapterView = adapterView;
            this.adapter = adapter;

            // detect when adapter has first content and adapter can be added
            this.adapter.registerDataSetObserver(this);
        }

        @Override
        public void onChanged() {
            // adapter should have content and recycler view can restore his last state it
            adapterView.setAdapter(adapter);

            // job is done ...
            adapter.unregisterDataSetObserver(this);
        }
    }
}
