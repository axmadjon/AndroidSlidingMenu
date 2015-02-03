package uz.greenwhite.slidingmenu.support.v10.volley.volley;

import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Request;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Response;

public abstract class VolleyRequest<T> extends Request<T> {

    protected Response.Listener<T> successListener;
    private ErrorListenerWrapper errorWrapper;

    private VolleyRequest(int method, String url, ErrorListenerWrapper errorWrapper) {
        super(method, url, errorWrapper);
        this.errorWrapper = errorWrapper;
    }

    public VolleyRequest(int method, String url,
                         Response.Listener<T> successListener,
                         Response.ErrorListener errorListener) {
        this(method, url, new ErrorListenerWrapper(errorListener));
        this.successListener = successListener;
    }

    public VolleyRequest(int method, String url) {
        this(method, url, null, null);
    }

    public Response.Listener<T> getSuccessListener() {
        return successListener;
    }

    public void setSuccessListener(Response.Listener<T> successListener) {
        this.successListener = successListener;
    }

    public Response.ErrorListener getErrorListener() {
        return errorWrapper.errorListener;
    }

    public void setErrorListener(Response.ErrorListener errorListener) {
        this.errorWrapper.errorListener = errorListener;
    }
}
