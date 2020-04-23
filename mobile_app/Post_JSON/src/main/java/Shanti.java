import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Shanti {

        public static void main(String[] args) throws IOException {
            POSTRequest();
            JSONObject obj = new JSONObject();
            obj.put("name", "foo");
            obj.put("num", new Integer(100));
            obj.put("balance", new Double(1000.21));
            obj.put("is_vip", new Boolean(true));

            System.out.print(obj+"\n");
            String data = obj.toString();
            System.out.println(data);
            SendJson send = new SendJson();
            //send.post("https://szmuschi.pythonanywhere.com/api", data);
            //post("https://szmuschi.pythonanywhere.com/api", data);
            send.send(data);
        }

        public static void POSTRequest() throws IOException {
            final String POST_PARAMS = "{\n" + "\"userId\": 101,\r\n" +
                    "    \"id\": 101,\r\n" +
                    "    \"title\": \"Test Title\",\r\n" +
                    "    \"body\": \"Test Body\"" + "\n}";

//        System.out.println(POST_PARAMS);
          //  URL obj = new URL("https://reviewnator-api.herokuapp.com/api/v1/airports?fbclid=IwAR22ayjGkWMcRBcpFnUACY0YINMTARA1B2sD7Vv0lP2buFi8btJwZdv5hcA");
            URL obj = new URL("https://szmuschi.pythonanywhere.com/api");
           // URL obj = new URL("https://reqres.in/api/users");
            HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
            postConnection.setRequestMethod("POST");
//        postConnection.setRequestProperty("userId", "a1bcdefgh");
            postConnection.setRequestProperty("content-typ", "image/jpeg");
           // postConnection.setRequestProperty("content-typ", "text/plain; charset=UTF-8");
            postConnection.setDoOutput(true);
            OutputStream os = postConnection.getOutputStream();
            os.write(POST_PARAMS.getBytes());
            os.flush();
            os.close();
            int responseCode = postConnection.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);
            System.out.println("POST Response Message : " + postConnection.getResponseMessage());
            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        postConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in .readLine()) != null) {
                    response.append(inputLine);
                } in .close();
                // print result
                System.out.println(response.toString());
            } else {
                System.out.println("POST NOT WORKED");
            }
        }
//
//        public static String post(String postUrl, String data) throws IOException {
//            URL url = new URL(postUrl);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("POST");
//            con.setDoOutput(true);
//            //this.sendData(con, data);
//            sendData(con, data);
//            //return this.read(con.getInputStream());
//            return read(con.getInputStream());
//        }

//        protected static void sendData(HttpURLConnection con, String data) throws IOException {
//            DataOutputStream wr = null;
//            try {
//                wr = new DataOutputStream(con.getOutputStream());
//                wr.writeBytes(data);
//                wr.flush();
//                wr.close();
//            } catch(IOException exception) {
//                throw exception;
//            } finally {
//                //this.closeQuietly(wr);
//                closeQuietly(wr);
//            }
//        }
//
//        private static String read(InputStream is) throws IOException {
//            BufferedReader in = null;
//            String inputLine;
//            StringBuilder body;
//            try {
//                in = new BufferedReader(new InputStreamReader(is));
//                body = new StringBuilder();
//                while ((inputLine = in.readLine()) != null) {
//                    body.append(inputLine);
//                }
//                in.close();
//                return body.toString();
//            } catch(IOException ioe) {
//                throw ioe;
//            } finally {
//                //this.closeQuietly(in);
//                closeQuietly(in);
//            }
//        }
//
//        protected static void closeQuietly(Closeable closeable) {
//            try {
//                if( closeable != null ) {
//                    closeable.close();
//                }
//            } catch(IOException ex) {
//
//            }
//        }

    }
