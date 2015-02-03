package uz.greenwhite.slidingmenu.support.v10.common.fragment.volley_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Response;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.VolleyError;
import uz.greenwhite.slidingmenu.support.v10.volley.volley.VolleyGroup;
import uz.greenwhite.slidingmenu.support.v10.volley.volley.VolleyRequest;
import uz.greenwhite.slidingmenu.support.v10.volley.volley.VolleySingleton;
import uz.greenwhite.slidingmenu.support.v10.volley.volley.VolleySupport;

public class VolleyContentFragment extends Fragment implements VolleySupport {

    private Object TAG;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TAG = new Object();
    }

    @Override
    public void onStop() {
        super.onStop();
        VolleySingleton.instance.cancelPendingRequests(TAG);
    }

    @Override
    public <T> void addToRequestQueue(VolleyRequest<T> req) {
        VolleySingleton.instance.addToRequestQueue(req, TAG);
    }

    @Override
    public void addToRequestQueue(VolleyGroup volleyGroup) {
        VolleySingleton.instance.addToRequestQueue(volleyGroup, TAG);
    }

    @Override
    public void showVolleyError(VolleyError error) {
        VolleySingleton.showVolleyError(getActivity(), error);
    }

    @Override
    public Response.ErrorListener createErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showVolleyError(error);
            }
        };
    }
}
