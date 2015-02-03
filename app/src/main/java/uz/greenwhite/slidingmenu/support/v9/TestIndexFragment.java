package uz.greenwhite.slidingmenu.support.v9;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import uz.greenwhite.slidingmenu.support.v9.collection.MyArray;
import uz.greenwhite.slidingmenu.support.v9.common.IndexFragment;
import uz.greenwhite.slidingmenu.support.v9.common.mold.MoldActivity;
import uz.greenwhite.slidingmenu.support.v9.view_setup.UI;

import java.util.Arrays;

public class TestIndexFragment extends IndexFragment<String, TestIndexFragment.ViewHolder> {

    public static void open(Context ctx) {
        MoldActivity.openIndex(ctx, TestIndexFragment.class, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(MyArray.from(Arrays.asList("He", "World", "He", "World", "He", "World", "He", "World", "He")));
    }

    @Override
    public void listClick(String item) {
        makeContent(item.length());
    }

    @Override
    protected void makeContent(int id) {
        replaceContent(TestContentFragment.newInstance());
    }

    @Override
    public int adapterGetLayoutResource() {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public ViewHolder adapterMakeHolder(View v) {
        ViewHolder h = new ViewHolder();
        h.name = UI.id(v, android.R.id.text1);
        return h;
    }

    @Override
    protected void adapterPopulate(ViewHolder holder, String item) {
        holder.name.setText(item);
    }

    class ViewHolder {
        TextView name;
    }
}
