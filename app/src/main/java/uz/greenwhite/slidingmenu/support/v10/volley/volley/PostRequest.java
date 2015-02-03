package uz.greenwhite.slidingmenu.support.v10.volley.volley;

import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.AuthFailureError;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.NetworkResponse;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.ParseError;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Response;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.toolbox.HttpHeaderParser;

public abstract class PostRequest<T> extends VolleyRequest<T> {

    /** Charset for request. */
    private static final String PROTOCOL_CHARSET = "utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    public PostRequest(String url) {
        super(Method.POST, url);
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return getBodyString().getBytes(PROTOCOL_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract String getBodyString() throws Exception;

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String st = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    parseResult(st),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    protected abstract T parseResult(String st) throws Exception;

    @Override
    protected void deliverResponse(T response) {
        successListener.onResponse(response);
    }

}
