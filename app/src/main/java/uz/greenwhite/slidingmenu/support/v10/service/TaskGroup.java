package uz.greenwhite.slidingmenu.support.v10.service;

import uz.greenwhite.slidingmenu.support.v10.service.request.TaskRequest;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskGroup {

    public abstract void onStart();

    public abstract void onStop();

    public List<TaskRequest> taskRequests;

    protected TaskGroup() {
        this.taskRequests = new ArrayList<TaskRequest>();
    }

    public void add(TaskRequest taskRequest) {
        this.taskRequests.add(taskRequest);
    }


}
