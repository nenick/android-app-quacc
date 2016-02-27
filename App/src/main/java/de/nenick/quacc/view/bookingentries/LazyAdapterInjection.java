package de.nenick.quacc.view.bookingentries;

import android.support.v7.widget.RecyclerView;

/**
 * Ensure correct instance state restoration on configuration changes (e.g. rotation).
 * <p/>
 * Only set adapter when cursor loading is ready.
 * Recycler view tries but can not restore instance state with an empty adapter (data source).
 */
public class LazyAdapterInjection extends RecyclerView.AdapterDataObserver {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public static void inject(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        new LazyAdapterInjection(recyclerView, adapter);
    }

    public LazyAdapterInjection(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.adapter.registerAdapterDataObserver(this);
    }

    @Override
    public void onChanged() {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }
    }
}
