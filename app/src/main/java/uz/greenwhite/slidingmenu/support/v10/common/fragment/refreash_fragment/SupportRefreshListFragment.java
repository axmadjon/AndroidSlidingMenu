package uz.greenwhite.slidingmenu.support.v10.common.fragment.refreash_fragment;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v10.collection.MyArray;
import uz.greenwhite.slidingmenu.support.v10.collection.MyPredicate;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.SupportListImpl;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.SupportSearchQuery;
import uz.greenwhite.slidingmenu.support.v10.widget.MyAdapter;
import uz.greenwhite.slidingmenu.support.v10.widget.ViewSetup;
import uz.greenwhite.slidingmenu.support.v10.widget.pulltorefresh.PullToRefreshBase;
import uz.greenwhite.slidingmenu.support.v10.widget.pulltorefresh.PullToRefreshListView;

public abstract class SupportRefreshListFragment<E, H> extends SupportFragment implements SupportListImpl<E, H> {

    protected abstract void onRefreshing(PullToRefreshBase<ListView> refreshView);

    private ViewSetup vsRoot;
    protected TextView cEmpty;
    protected ContentListAdapter adapter = null;
    private PullToRefreshListView refreshListView;

    @Override
    protected ViewSetup onCreateViewSetup(@Nullable LayoutInflater inflater, @Nullable ViewGroup container) {
        vsRoot = new ViewSetup(inflater, container, R.layout.support_refresh_list_content);
        refreshListView = vsRoot.id(R.id.lv_reflesh);
        cEmpty = vsRoot.textView(android.R.id.empty);
        return vsRoot;
    }

    @Override
    protected void onContentCreate(@Nullable Bundle savedInstanceState) {
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                onRefreshing(refreshView);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        refreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                E item = adapter.getItem(position);
                onListItemClick(item);
            }
        });
    }

    public MyArray<E> getListFilteredItems() {
        if (adapter != null) {
            return adapter.getFilteredItems();
        }
        return null;
    }

    public MyArray<E> getListItems() {
        if (adapter != null) {
            return adapter.getItems();
        }
        return null;
    }

    protected final <E> void setAdapterItems(MyArray<E> adapterItems) {
        if (adapter == null) {
            getVsRoot().id(R.id.listContainer).setVisibility(View.GONE);
            adapter = new ContentListAdapter(getActivity());
            adapter.registerDataSetObserver(new DataSetObserver() {

                private void populate() {
                    if (adapter.getCount() == 0) {
                        refreshListView.setVisibility(View.GONE);
                        cEmpty.setVisibility(View.VISIBLE);
                    } else {
                        refreshListView.setVisibility(View.VISIBLE);
                        cEmpty.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onChanged() {
                    super.onChanged();
                    populate();
                }

                @Override
                public void onInvalidated() {
                    super.onInvalidated();
                    populate();
                }
            });
            refreshListView.setAdapter(adapter);
            getVsRoot().id(R.id.listContainer).setVisibility(View.VISIBLE);
            getVsRoot().id(R.id.progressContainer).setVisibility(View.GONE);
        }
        adapter.setItems((MyArray) adapterItems);
    }

    public void setLoadingText(CharSequence loadingText) {
        vsRoot.textView(R.id.loadingText).setText(loadingText);
    }

    public void setEmptyText(CharSequence emptyText) {
        cEmpty.setText(emptyText);
    }

    protected void completeRefresh() {
        refreshListView.onRefreshComplete();
    }

    protected void onListItemClick(E item) {
    }

    public class ContentListAdapter extends MyAdapter<E, H> {

        public ContentListAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutResource() {
            return adapterGetLayoutResource();
        }

        @Override
        public H makeHolder(View view) {
            return adapterMakeHolder(view);
        }

        @Override
        public void populate(H holder, E item) {
            adapterPopulate(holder, item);
        }
    }

    public abstract class SupportSearchListQuery extends SupportSearchQuery {

        @Override
        public void onQueryText(final String s) {
            if (adapter != null) {
                adapter.predicateSearch = new MyPredicate<E>() {
                    @Override
                    public boolean apply(E e) {
                        return filter(e, s);
                    }
                };
                adapter.filter();
            }
        }

        public abstract boolean filter(E item, String text);
    }
}
