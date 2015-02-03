package uz.greenwhite.slidingmenu.support.v10.volley.volley_request;

import android.util.Log;

import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.*;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import uz.greenwhite.slidingmenu.support.v10.volley.volley.VolleyRequest;

public abstract class PostRequest<E> extends VolleyRequest<E> {
    /**
     * Charset for request.
     */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    public PostRequest(String url, Response.Listener<E> successListener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, url, successListener, errorListener);
    }

    public PostRequest(String url) {
        this(url, null, null);
    }

    @Override
    protected Response<E> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.d("Aylana", json);
            E result = parserResult(json);
            if (result != null) {
                return Response.success(result,
                        HttpHeaderParser.parseCacheHeaders(response));
            }
            return Response.error(new ParseError());
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    private E parserResult(String json) throws Exception {
        JSONArray array = new JSONArray(json);
        if (array.length() != 0) {
            JSONObject obj = array.getJSONObject(0);
            if (obj.has("r")) {
                return parsResult(obj.getJSONObject("r"));
            }
        }
        return null;
    }

    protected abstract E parsResult(JSONObject obj) throws Exception;

    @Override
    protected void deliverResponse(E e) {
        successListener.onResponse(e);
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public abstract JSONObject populateBody() throws Exception;

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            JSONObject parameter = populateBody();
            return parameter.toString().getBytes(PROTOCOL_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
