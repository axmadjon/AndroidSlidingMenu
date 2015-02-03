package uz.greenwhite.slidingmenu.support.v10.volley.volley;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import uz.greenwhite.slidingmenu.support.v10.SupportApplication;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.DefaultRetryPolicy;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.RequestQueue;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Response;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.RetryPolicy;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.VolleyError;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.toolbox.ImageLoader;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.toolbox.Volley;

import java.net.ConnectException;

public class VolleySingleton {

    public static final Object TAG = new Object();
    public static final VolleySingleton instance = new VolleySingleton();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static void init(Context context) {
        instance.mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        instance.mImageLoader = new ImageLoader(instance.mRequestQueue, new LruBitmapCache());
    }

    private static final RetryPolicy RETRY_POLICY = new DefaultRetryPolicy(50000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    public <T> void addToRequestQueue(VolleyRequest<T> req, Object tag) {
        if (tag == null) {
            throw new NullPointerException();
        }
        req.setTag(tag);
        req.setRetryPolicy(RETRY_POLICY);
        mRequestQueue.add(req);
    }

    public <T> void addToRequestQueue(VolleyRequest<T> req) {
        addToRequestQueue(req, TAG);
    }

    public void addToRequestQueue(VolleyGroup volleyGroup, Object tag) {
        volleyGroup.start();
        for (VolleyRequest<?> request : volleyGroup.requests) {
            addToRequestQueue(request, tag);
        }
    }

    public void addToRequestQueue(VolleyGroup volleyGroup) {
        addToRequestQueue(volleyGroup, TAG);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public static void loadImage(ImageView view, String url, int defaultImageResId, int errorImageResId) {
        ImageLoader imageLoader = instance.mImageLoader;
        imageLoader.get(url, ImageLoader.getImageListener(view, defaultImageResId, errorImageResId));
    }

    public static void loadImage(ImageView view, String url, int defaultImageResId) {
        loadImage(view, url, defaultImageResId, defaultImageResId);
    }

    public static final Response.ErrorListener LOG_LISTENER = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Log.e("GWSLIB Volley", volleyError.getMessage(), volleyError.getCause());
        }
    };

    public static void showVolleyError(Activity activity, VolleyError error) {
        String message = SupportApplication.DEBUG ? error.getMessage() : null;
        if (error.getCause() instanceof ConnectException) {
            message = "Невозможно подключиться к интернету";
        } else {
            Log.e("GWSLIB Volley", "Fatal", error.getCause());
            message = "Произошла ошибка в системе повторите попытку еще раз" +
                    (message != null ? "\n" + message : "");
        }
        // TODO AppMsg.makeText(activity, message, AppMsg.STYLE_ALERT).show();
    }
}
