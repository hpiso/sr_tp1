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

    private List<Integer> list = new ArrayList<Integer>();
    private int port;

    public Client(int port) {
        this.port = port;
    }

    /**
     * Execute la commande via les sockets
     *
     **/
    public void executeCommand() {

        Command command = new Command();
        command.setClassName("Calc");
        command.setFunctionName("add");
        this.list.add(2);
        this.list.add(3);
        command.setParams(this.list);

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

        Client client = new Client(port);
        client.executeCommand();
    }
}
