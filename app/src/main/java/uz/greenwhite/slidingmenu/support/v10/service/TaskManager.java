package uz.greenwhite.slidingmenu.support.v10.service;

import uz.greenwhite.slidingmenu.support.v10.service.request.TaskRequest;

import java.util.List;

public class TaskManager {

    public final TaskGroup taskGroup;

    public TaskManager(TaskRequest taskRequest) {
        this.taskGroup = new TaskGroup() {
            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {

            }
        };
        taskGroup.add(taskRequest);
    }

    public TaskManager(List<TaskRequest> taskRequests) {
        this.taskGroup = new TaskGroup() {
            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {

            }
        };
        for (TaskRequest t : taskRequests) {
            taskGroup.add(t);
        }
    }

    public TaskManager(TaskGroup taskGroup) {
        this.taskGroup = taskGroup;
    }
}
