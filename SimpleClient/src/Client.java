import org.json.simple.JSONObject;
import tkachuk.Phone;

import java.io.IOException;
import java.net.UnknownHostException;


public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 17; i++) {
            int finalI = i;
            new Thread(() -> {
                try (Phone phone = new Phone("127.0.0.1", 8000)) {
                    JSONObject request = new JSONObject();
                    request.put("n", Math.random() * 300);
                    phone.writeLine(request);
                    JSONObject response = phone.readLine();
                    System.out.println("Client " + finalI + " " + response.get("info"));
                    System.out.println("Square perimeter: " + response.get("n"));
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }).start();
        }
    }
}
