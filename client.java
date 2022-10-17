
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Socket s = new Socket("localhost", 1234);

        ObjectOutputStream outStream = new ObjectOutputStream(s.getOutputStream());

        VehicleRequest vehicleRequest = null;

            Scanner scan = new Scanner(System.in);

            System.out.println(
                    "press ' 1' for a honda \n" +
                            "press ' 2 ' for a tesla \n" +
                            "press ' 3 ' for a BMW \n" +
                            "press ' 4 ' for a audi \n" +
                            "press ' 5 ' for a volkswagen"
            );

            int userChoice = scan.nextInt();

            if (userChoice == 1) {
                vehicleRequest = new VehicleRequest(2022, "honda", "Civic Type R");
            }
            if (userChoice == 2) {
                vehicleRequest = new VehicleRequest(2022, "tesla", "S");
            }
            if (userChoice == 3) {
                vehicleRequest = new VehicleRequest(2022, "BMW", "Isetta");

            }
            if (userChoice == 4) {
                vehicleRequest = new VehicleRequest(2022, "audi", "hatchback");
            }
            if (userChoice == 5) {
                vehicleRequest = new VehicleRequest(2022, "volkswagen", "jetta GL");
            }
            else {
                vehicleRequest = new VehicleRequest(2023, "Toyota", "Supra");
            }
            outStream.writeObject(vehicleRequest);
            outStream.flush();

                ObjectInputStream inStream = new ObjectInputStream(s.getInputStream());

                VehicleResponse receiveResponse = (VehicleResponse) inStream.readObject();
                System.out.println("Vehicle response received.");
                System.out.println(receiveResponse.message);

                outStream.close();
                s.close();
            }

    }