package de.nenick.toolscollection;

import android.support.v7.widget.RecyclerView;

/**
 * Ensure correct instance state restoration on configuration changes (e.g. rotation).
 * <p/>
 * First call inject before you start loading data for adapter or it may miss initial data event.
 * <p/>
 * This class sets the adapter once when data loading is ready.
 * Recycler view tries but can not restore instance state with an empty adapter (data source).
 */
public class LazyAdapter extends RecyclerView.AdapterDataObserver {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public static void inject(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        new LazyAdapter(recyclerView, adapter);
    }

    public LazyAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
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
