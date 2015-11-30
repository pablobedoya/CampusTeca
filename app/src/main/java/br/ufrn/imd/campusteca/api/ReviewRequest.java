package br.ufrn.imd.campusteca.api;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Pablo Gabriel on 23/11/2015.
 */
public class ReviewRequest extends AsyncTask<HashMap<String, String>, Void, List<Map<String, String>>> {
    private String url = "http://campusteca-pablobedoya.rhcloud.com";
    private String target;
    private String method;
    private List<String> keys;

    public ReviewRequest(String target, String method, List<String> keys) {
        this.target = target;
        this.method = method;
        this.keys = keys;
    }

    @Override
    protected List<Map<String, String>> doInBackground(HashMap<String, String>... params) {
        try {
            return get(url + target, params);
        } catch (IOException e) {
            Map<String, String> error = new HashMap<>();
            error.put("ERROR", "Unable to retrieve web page. URL may be invalid.");
            List<Map<String, String>> errorResult = new ArrayList<>();
            errorResult.add(error);
            return errorResult;
        }
    }

    private List<Map<String, String>> get(String urlReview, HashMap<String, String>... params) throws IOException {
        InputStream is = null;
        Map<String, String> resultObject;
        List<Map<String, String>> resultList = new ArrayList<>();

        try {
            URL url = new URL(urlReview);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            if (params != null && params.length > 0) {
                HashMap<String, String> map = params[0];
                Set<String> keys = map.keySet();
                for (String key : keys)
                    conn.addRequestProperty(key, map.get(key));
            }

            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            JSONArray jObj = new JSONArray();
            Object jsonResult = new JSONTokener(sb.toString()).nextValue();
            if (jsonResult instanceof JSONObject) {
                jObj.put(new JSONObject(sb.toString()));
            } else if (jsonResult instanceof JSONArray)
                jObj = new JSONArray(sb.toString());

            for (int i = 0; i < jObj.length(); ++i) {
                JSONObject json = jObj.getJSONObject(i);
                resultObject = new HashMap<>();
                for (String key : keys)
                    if (json.has(key))
                        resultObject.put(key, json.getString(key));

                resultList.add(resultObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return resultList;
    }
}
