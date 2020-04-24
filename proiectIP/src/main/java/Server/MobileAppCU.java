package Server;

import org.json.JSONObject;

import java.util.List;

public class MobileAppCU {
    public JSONObject receiveImageJson(){
        return null;
    }
    //primim imaginea in format json pentru a o trimite catre image processor -comunicare prin socket
     public void sendReviews(List<JSONObject> reviews){}
     //la final trimitem reviewurile catre mobile app dupa ce am comunicat cu celelalte unitati
}
