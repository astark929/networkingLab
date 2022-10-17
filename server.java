
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket server = new ServerSocket(1234);
        Socket socket = server.accept();

            System.out.println("client has connected");

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());


            VehicleRequest request = (VehicleRequest)input.readObject();

            System.out.println("Vehicle request received.");
            System.out.println(request.message);

            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());

            int cost = (int) ((Math.random()*10000)+1000);
            int miles = (int) ((Math.random()*10000)-100);

            VehicleResponse vehicleResponse = new VehicleResponse(request, miles, cost, 2, 2, new String[]{"Manual Trans", "Leather Seats","plastic leather","nylon", "polyester"});
            outStream.writeObject(vehicleResponse);

            outStream.flush();

            server.close();
    }
}