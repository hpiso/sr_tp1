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
    private Scanner reader;

    public Client(int port) {
        this.port = port;
        this.reader = new Scanner(System.in);
    }

    /**
     * Envoie la commande serializé au serveur via les sockets
     **/
    public void sendCommand(Command command) {

        try {

            //Open socket
            Socket socket = new Socket(InetAddress.getLocalHost(), this.port);

            //send command
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            outToServer.writeObject(command);

            //Read response
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
            String response = inFromServer.readUTF();

            System.out.println("\n***************************");
            System.out.println("Result from server : " + response);
            System.out.println("***************************\n");

            //close socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        initCommand();
    }

    /**
     * Initialise une commande à envoyer
     **/
    public void initCommand() {
        Command command = new Command();
        command.setClassName("app.Calc");

        ArrayList<String> list = new ArrayList<String>();
        System.out.println("First number: ");
        int firstNumber = reader.nextInt();
        list.add(Integer.toString(firstNumber));

        System.out.println("Choose from these choices");
        System.out.println("-------------------------");
        System.out.println("1 - Addition");
        System.out.println("2 - Multiply");
        System.out.println("3 - Substraction");
        System.out.println("4 - Divide");
        int calcType = reader.nextInt();

        switch (calcType) {
            case 1:
                command.setMethodName("add");
                break;
            case 2:
                command.setMethodName("multiply");
                break;
            case 3:
                command.setMethodName("substraction");
                break;
            case 4:
                command.setMethodName("divide");
                break;
        }

        System.out.println("Second number: ");
        int secondNumber = reader.nextInt();
        list.add(Integer.toString(secondNumber));
        command.setParams(list);

        sendCommand(command);
    }

    /**
     * Point d'entré du client
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Port number: ");
        int port = reader.nextInt();
        Client client = new Client(port);
        client.initCommand();
    }
}
