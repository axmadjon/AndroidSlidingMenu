package uz.greenwhite.slidingmenu.support.v9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.collection.MyArray;
import uz.greenwhite.slidingmenu.support.v9.common.Command;
import uz.greenwhite.slidingmenu.support.v9.common.ContentFragment;
import uz.greenwhite.slidingmenu.support.v9.common.PullToRefresh;
import uz.greenwhite.slidingmenu.support.v9.common.action_bar.ContentActionBar;
import uz.greenwhite.slidingmenu.support.v9.view_setup.UI;

import java.util.Arrays;

public class NewContentFragment extends ContentFragment<String, NewContentFragment.ViewHolder> {

    public static NewContentFragment newInstance() {
        return new NewContentFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ContentActionBar actionBar = getActionBar();
        actionBar.setLogo(R.drawable.ic_launcher);
        actionBar.setTitle(R.string.app_name);

        setListAdapter(MyArray.from(Arrays.asList("Axmadjon", "Xamidov", "Mirzaaxmedov", "Lasiz")));
    }

    @Override
    protected Command setOnHomeClick() {
        return new Command() {
            @Override
            public void apply() {
                getActivity().onBackPressed();
            }
        };
    }

    @Override
    public void listClick(String item) {
        Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
        onCompleteRefresh();
    }

    @Override
    protected PullToRefresh setOnRefreshListener() {
        return new PullToRefresh() {
            @Override
            public void setOnRefresh() {

            }
        };
    }

    //*****************************************************************************************************************

    @Override
    public int adapterGetLayoutResource() {
        return R.layout.test_content_item;
    }

    @Override
    public ViewHolder adapterMakeHolder(View v) {
        ViewHolder h = new ViewHolder();
        h.name = UI.id(v, R.id.tv_test);
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
