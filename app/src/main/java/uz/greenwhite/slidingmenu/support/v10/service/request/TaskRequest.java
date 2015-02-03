package uz.greenwhite.slidingmenu.support.v10.service.request;

import org.json.JSONException;
import org.json.JSONObject;
import uz.greenwhite.slidingmenu.support.v10.service.Response;
import uz.greenwhite.slidingmenu.support.v10.util.SequenceGenerator;

public abstract class TaskRequest<R> {

    public static final int GET = 0;
    public static final int POST = 1;

    public final int id;
    public final String url;
    public final Response<R> response;
    public final int requestMethod;

    protected TaskRequest(String url, Response<R> response, int requestMethod) {
        this.id = (int) SequenceGenerator.next();
        this.url = url;
        this.response = response;
        this.requestMethod = requestMethod;
    }

    public String getBody() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("i", id);
        populateBody(obj);
        return obj.toString();
    }

    public void result(String json) {
        R r = parsResult(json);
        response.onSuccess(r);
    }

    public abstract R parsResult(String json);

    public abstract void populateBody(JSONObject obj);

}
