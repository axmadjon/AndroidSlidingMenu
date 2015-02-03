package uz.greenwhite.slidingmenu.support.support_test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import uz.greenwhite.slidingmenu.support.v10.collection.MyArray;
import uz.greenwhite.slidingmenu.support.v10.common.Command;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportListFragment;
import uz.greenwhite.slidingmenu.support.v10.util.CharSequenceUtil;
import uz.greenwhite.slidingmenu.support.v10.widget.UI;

public class TestListFragment extends SupportListFragment<String, TestListFragment.ViewHolder> {

    @Override
    protected void onContentCreate(@Nullable Bundle savedInstanceState) {
        setSearchMenu(new SupportSearchListQuery() {
            @Override
            public boolean filter(String item, String text) {
                return CharSequenceUtil.containsIgnoreCase(item, text);
            }
        });

        onHomeClick(new Command() {
            @Override
            public void apply() {
                getActivity().onBackPressed();
            }
        });

        MyArray<String> list = MyArray.from(
                "Hello", "World", "To", "New", "List", "Content", "Fragment"
        );
        setAdapterItems(list);
    }

    @Override
    protected void onListItemClick(String item) {
        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        addContent(SupportFragment.newInstance(TestRefreshFragment.class));
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
