import org.json.simple.JSONObject;
import tkachuk.Phone;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) {

        try(ServerSocket server = new ServerSocket(8000, 17))
        {
            System.out.println("Server started!");

            while(true) {
                Phone phone = new Phone(server);
                new Thread(() -> {
                    String info = phone.getInfo();
                    JSONObject request = phone.readLine();
                    double number = (double) request.get("n");
                    JSONObject response = new JSONObject();
                    response.put("n", number*4);
                    response.put("info", info);
                    try { Thread.sleep((long) (Math.random()*4000)); } catch (InterruptedException e) {}
                    phone.writeLine(response);
                    System.out.println("Square side: " + request.get("n"));
                    try { phone.close(); } catch (IOException e) {}
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
