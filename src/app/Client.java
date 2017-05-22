package app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    private int port;

    public Client(int port) {
        this.port = port;
    }

    /**
     * Execute la commande via les sockets
     *
     **/
    public void sendCommand() {

        Command command = new Command();
        command.setClassName("app.Calc");
        command.setMethodName("substraction");
        ArrayList<String> list = new ArrayList<String>();
        list.add("10");
        list.add("2");
        command.setParams(list);

        try {

            //Open socket
            Socket socket = new Socket(InetAddress.getLocalHost(), this.port);

            //send command
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            outToServer.writeObject(command);

            //Read response
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
            String response = inFromServer.readUTF();

            System.out.println("From server : " + response);

            //close socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Port number: ");
        int port = reader.nextInt();

        System.out.println("Choose from these choices");
        System.out.println("-------------------------");
        System.out.println("1 - Addition");
        System.out.println("2 - Multiply");
        System.out.println("3 - Substraction");
        System.out.println("4 - Divide");
        int selection = reader.nextInt();


        Client client = new Client(port);
        client.sendCommand();
    }
}
