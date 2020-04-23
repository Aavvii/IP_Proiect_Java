package com.chillyfacts.com;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

//trimitere JSON request si citire JSON response
public class Post_JSON {

    public static void main(String[] args) {
        Post_JSON.Post_JSON();
    }

    public static void Post_JSON() {
        //String query_url = "https://reviewnator-api.herokuapp.com/api/v1/airports?fbclid=IwAR22ayjGkWMcRBcpFnUACY0YINMTARA1B2sD7Vv0lP2buFi8btJwZdv5hcA";
        String query_url = "https://szmuschi.pythonanywhere.com/api";
        String json = "{\n" + "\"userId\": 101,\r\n" +
                "    \"id\": 101,\r\n" +
                "    \"title\": \"Test Title\",\r\n" +
                "    \"body\": \"Test Body\"" + "\n}";

        try {
            URL url = new URL(query_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");

            System.out.println(result);

            System.out.println("result after Reading JSON Response");

            JSONObject myResponse = new JSONObject(result);
            System.out.println("ISBN "+myResponse.getString("ISBN"));
            System.out.println("author "+myResponse.getString("author"));
            System.out.println("title "+myResponse.getString("title"));

            in.close();
            conn.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
