package Server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

//        thread
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
//        server
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.createContext("/test", new MyHttpHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Merge");

    }
}
