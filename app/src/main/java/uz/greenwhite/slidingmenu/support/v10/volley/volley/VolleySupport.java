package uz.greenwhite.slidingmenu.support.v10.volley.volley;

import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Response;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.VolleyError;

public interface VolleySupport {

    <T> void addToRequestQueue(VolleyRequest<T> req);

    void addToRequestQueue(VolleyGroup volleyGroup);

    void showVolleyError(VolleyError error);

    Response.ErrorListener createErrorListener();
}
