package uz.greenwhite.slidingmenu.support.v10.service;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONObject;
import uz.greenwhite.slidingmenu.support.v10.error.RequestException;
import uz.greenwhite.slidingmenu.support.v10.service.http_request.HttpRequest;
import uz.greenwhite.slidingmenu.support.v10.service.http_request.Request;
import uz.greenwhite.slidingmenu.support.v10.service.request.TaskRequest;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public abstract class TaskService<R> extends AsyncTask<TaskRequest<R>, Void, Void> implements Request {

    public abstract void onStart();

    public abstract void onStop();

    public abstract void onResult(long id, String result);

    protected final List<TaskRequest<R>> taskRequest;

    protected TaskService(List<TaskRequest<R>> taskRequest) {
        this.taskRequest = taskRequest;
    }

    @Override
    protected void onPreExecute() {
        onStart();
    }

    @Override
    protected Void doInBackground(TaskRequest<R>... params) {
        for (TaskRequest t : params) {
            try {
                HttpRequest.post(this, t.url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        onStop();
    }

    @Override
    public void send(OutputStream os) throws Exception {
        JSONArray arr = new JSONArray();
        for (TaskRequest t : taskRequest) {
            JSONObject obj = new JSONObject();
            obj.put("d", t.getBody());
            arr.put(obj);
        }
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF8"), true);
        writer.print(arr.toString());
        writer.flush();
    }

    @Override
    public void receive(InputStream is) throws Exception {
        String s = HttpRequest.makeString(is);
        JSONArray arr = new JSONArray(s);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            long id = obj.getLong("i");
            String result;
            if (obj.has("r")) {
                result = obj.getString("r");
            } else if (obj.has("e")) {
                result = obj.getString("e");
            } else {
                throw new RequestException("no result found");
            }
            onResult(id, result);
        }
    }

    public TaskRequest findTask(long id) {
        for (TaskRequest c : taskRequest) {
            if (c.id == id) {
                return c;
            }
        }
        return null;
    }
}
