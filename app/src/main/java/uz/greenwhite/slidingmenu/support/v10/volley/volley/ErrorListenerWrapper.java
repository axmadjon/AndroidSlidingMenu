package uz.greenwhite.slidingmenu.support.v10.volley.volley;

import android.util.Log;

import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Response;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.VolleyError;

class ErrorListenerWrapper implements Response.ErrorListener {

    public Response.ErrorListener errorListener;

    public ErrorListenerWrapper(Response.ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        try {
            errorListener.onErrorResponse(error);
        } catch (Exception ex) {
            Log.e("Volley", "Error:" + ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }
}
