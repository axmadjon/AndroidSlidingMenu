package uz.greenwhite.slidingmenu.support.v10.volley.volley;

import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.NetworkResponse;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.ParseError;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.Response;
import uz.greenwhite.slidingmenu.support.v10.volley.lib.request.toolbox.HttpHeaderParser;

public abstract class GetRequest<T> extends VolleyRequest<T> {

    public GetRequest(String url) {
        super(Method.GET, url);
    }

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
    protected void deliverResponse(T s) {
        successListener.onResponse(s);
    }
}
