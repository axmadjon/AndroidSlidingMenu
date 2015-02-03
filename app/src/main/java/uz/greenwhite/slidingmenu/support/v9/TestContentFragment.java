package uz.greenwhite.slidingmenu.support.v9;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import uz.greenwhite.slidingmenu.R;
import uz.greenwhite.slidingmenu.support.v9.common.Command;
import uz.greenwhite.slidingmenu.support.v9.common.IndexContentFragment;
import uz.greenwhite.slidingmenu.support.v9.common.action_bar.ContentActionBar;
import uz.greenwhite.slidingmenu.support.v9.common.action_bar.MenuInflate;
import uz.greenwhite.slidingmenu.support.v9.common.action_bar.MenuItem;

public class TestContentFragment extends IndexContentFragment {

    public static TestContentFragment newInstance() {
        return new TestContentFragment();
    }

    @Override
    protected int onCreateIndexView() {
        return R.layout.test_content;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getViewSetup().id(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContent(NewContentFragment.newInstance());
            }
        });

        ContentActionBar actionBar = getActionBar();
        actionBar.setTitle(R.string.app_name);
        actionBar.showDrawable();
    }

    @Override
    protected MenuInflate onActionBarMenu(MenuInflate menuInflate) {

        menuInflate.add(new MenuItem(R.drawable.ic_launcher, new Command() {
            @Override
            public void apply() {
                Toast.makeText(getActivity(), "Hello Menu", Toast.LENGTH_SHORT).show();
            }
        }));

        menuInflate.add(new MenuItem(R.drawable.ic_launcher, new Command() {
            @Override
            public void apply() {
                Toast.makeText(getActivity(), "Hello Menu2", Toast.LENGTH_SHORT).show();
            }
        }));

        return menuInflate;
    }

    @Override
    protected Command setOnHomeClick() {
        return new Command() {
            @Override
            public void apply() {
                showIndexContent();
            }
        };
    }
}
