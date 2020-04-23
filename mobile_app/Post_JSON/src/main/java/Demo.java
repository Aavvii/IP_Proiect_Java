import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Demo {
    public static void main(String[] args) throws IOException {

        String data = "data=Hello+World!";
        URL url = new URL("https://szmuschi.pythonanywhere.com/api");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.getOutputStream().write(data.getBytes("UTF-8"));
        con.getInputStream();

    }
}
