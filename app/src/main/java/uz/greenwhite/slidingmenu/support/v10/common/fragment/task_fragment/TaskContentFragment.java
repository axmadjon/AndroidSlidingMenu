package uz.greenwhite.slidingmenu.support.v10.common.fragment.task_fragment;

import uz.greenwhite.slidingmenu.support.v10.SupportApplication;
import uz.greenwhite.slidingmenu.support.v10.common.fragment.content_fragment.SupportFragment;
import uz.greenwhite.slidingmenu.support.v10.common.mold.SupportActivity;
import uz.greenwhite.slidingmenu.support.v10.json.JsonAdapter;
import uz.greenwhite.slidingmenu.support.v10.service.TaskManager;

public abstract class TaskContentFragment<R> extends SupportFragment {

    protected abstract TaskManager getRequest();

    protected abstract void reloadInCache(R result);

    @Override
    public void onStart() {
        super.onStart();
        ((SupportActivity) getActivity()).taskRequests(getRequest());
    }

    protected void reloadCache(R r, JsonAdapter<R> adapter) {
        R request = SupportApplication.requestUtil.getRequest(r, adapter);
        reloadInCache(request);
    }

}
