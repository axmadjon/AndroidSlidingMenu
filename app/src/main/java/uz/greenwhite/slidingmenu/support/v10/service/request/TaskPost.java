package uz.greenwhite.slidingmenu.support.v10.service.request;

import uz.greenwhite.slidingmenu.support.v10.service.Response;

public abstract class TaskPost<R> extends TaskRequest<R> {

    protected TaskPost(String url, Response response) {
        super(url, response, POST);
    }
}
