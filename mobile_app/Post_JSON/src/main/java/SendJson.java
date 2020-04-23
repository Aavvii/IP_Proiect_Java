import com.jsunsoft.http.*;
import org.apache.http.entity.ContentType;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SendJson {
    private static final HttpRequest httpRequest =
            HttpRequestBuilder.create(ClientBuilder.create().build())
                    .addContentType(ContentType.APPLICATION_JSON)
                    .build();
    public void send(String jsonData){
        httpRequest.target("https://szmuschi.pythonanywhere.com/api").get(jsonData, new TypeReference<List<String>>() {})
                .ifSuccess(this::whenSuccess) //call whenSuccess method if request is success
                .otherwise(this::whenNotSuccess); //call whenNotSuccess method if request is success
    }
    private void whenSuccess(ResponseHandler<List<String>> responseHandler){
        //When predicate of filter returns true, calls whenHasContent else calls whenHasNotContent
        responseHandler.filter(ResponseHandler::hasContent) //if request has content will be executed ifPassed consumer else otherwise consumer
                .ifPassed(this::whenHasContent)  //call hasContent method if request body is present
                .otherwise(this::whenHasNotContent);
    }

    private void whenNotSuccess(ResponseHandler<List<String>> responseHandler){
        //For demo. You can handle what you want
        System.err.println("Error code: " + responseHandler.getStatusCode() + ", error message: " + responseHandler.getErrorText());
    }

    private void whenHasContent(ResponseHandler<List<String>> responseHandler){
        //For demo.
        List<String> responseBody = responseHandler.get();
        System.out.println(responseBody);
    }

    private void whenHasNotContent(ResponseHandler<List<String>> responseHandler){
        //For demo.
        System.out.println("Response is success but body is missing. Response code: " + responseHandler.getStatusCode());
    }

    public String post(String postUrl, String data) throws IOException {
        URL url = new URL(postUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        this.sendData(con, data);
        return this.read(con.getInputStream());
    }
    protected void sendData(HttpURLConnection con, String data) throws IOException {
        DataOutputStream wr = null;
        try {
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        } catch(IOException exception) {
            throw exception;
        } finally {
            this.closeQuietly(wr);
        }
    }

    private String read(InputStream is) throws IOException {
        BufferedReader in = null;
        String inputLine;
        StringBuilder body;
        try {
            in = new BufferedReader(new InputStreamReader(is));
            body = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();
            return body.toString();
        } catch(IOException ioe) {
            throw ioe;
        } finally {
            this.closeQuietly(in);
        }
    }

    protected void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {

        }
    }
}
