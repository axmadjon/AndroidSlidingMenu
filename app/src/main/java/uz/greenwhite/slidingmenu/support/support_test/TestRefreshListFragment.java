package uz.greenwhite.slidingmenu.support.support_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import uz.greenwhite.slidingmenu.support.v10.collection.MyArray;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.refreash_fragment.SupportRefreshListFragment;
import uz.greenwhite.slidingmenu.support.v10.widget.UI;
import uz.greenwhite.slidingmenu.support.v10.widget.pulltorefresh.PullToRefreshBase;

public class TestRefreshListFragment extends SupportRefreshListFragment<String, TestRefreshListFragment.ViewHolder> {

    @Override
    protected void onContentCreate(@Nullable Bundle savedInstanceState) {
        MyArray<String> list = MyArray.from("Hello", "World", "Simple", "List", "Refresh");
        setAdapterItems(list);
    }

    @Override
    protected void onListItemClick(String item) {
        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        completeRefresh();

    }

    @Override
    protected void onRefreshing(PullToRefreshBase<ListView> refreshView) {
        Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int adapterGetLayoutResource() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public ViewHolder adapterMakeHolder(View view) {
        ViewHolder h = new ViewHolder();
        h.text = UI.id(view, android.R.id.text1);
        return h;
    }

    @Override
    public void adapterPopulate(ViewHolder holder, String item) {
        holder.text.setText(item);
    }

    static class ViewHolder {
        TextView text;
    }
}
