package uz.greenwhite.slidingmenu.support.v10.volley.volley;

import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Response;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.VolleyError;

import java.util.ArrayList;

public abstract class VolleyGroup {

    ArrayList<VolleyRequest<Object>> requests = new ArrayList<VolleyRequest<Object>>();

    public <T> void add(final VolleyRequest<T> request) {
        requests.add((VolleyRequest<Object>) request);
        final Response.Listener<T> successListener = request.getSuccessListener();
        request.setSuccessListener(new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                successListener.onResponse(response);
                onResult(request);
            }
        });
        final Response.ErrorListener errorListener = request.getErrorListener();
        request.setErrorListener(new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorListener.onErrorResponse(error);
                onResult(request);
            }
        });
    }

    private void onResult(VolleyRequest<?> request) {
        requests.remove(request);
        if (requests.size() == 0) {
            stop();
        }
    }

    protected void start() {
        onStart();
    }

    protected void stop() {
        onStop();
    }

    public abstract void onStart();

    public abstract void onStop();
}
