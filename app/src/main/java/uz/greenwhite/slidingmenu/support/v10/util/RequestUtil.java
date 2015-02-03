package uz.greenwhite.slidingmenu.support.v10.util;

import android.content.Context;
import uz.greenwhite.slidingmenu.support.v10.db.Database;
import uz.greenwhite.slidingmenu.support.v10.db.TableRef;
import uz.greenwhite.slidingmenu.support.v10.json.JsonAdapter;
import uz.greenwhite.slidingmenu.support.v10.json.JsonOutput;

public final class RequestUtil {

    private final TableRef db;

    public RequestUtil(Context context) {
        this.db = new Database(context).ref;
    }

    public <R> R getRequest(R request, JsonAdapter<R> adapter) {
        return db.loadOne(request.getClass().getName(), "0", adapter);
    }

    public <R> void saveRequest(R request, JsonAdapter<R> adapter) {
        String json = JsonOutput.stringify(request, adapter);
        saveRequest(request.getClass().getName(), json);
    }

    public void saveRequest(String name, String json) {
        db.replace(name, "0", json);
    }
}

