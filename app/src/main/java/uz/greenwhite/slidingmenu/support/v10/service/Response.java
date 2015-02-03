package uz.greenwhite.slidingmenu.support.v10.service;

public interface Response<R> {

    void onSuccess(R result);

    void onError();
}
