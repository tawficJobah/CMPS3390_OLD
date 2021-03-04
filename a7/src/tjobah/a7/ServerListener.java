package tjobah.a7;

import jforsythe.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerListener extends Thread{

    private Socket socket;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    Controller controller;

    /**
     * constructor for the listener
     * @param socket
     * @param controller
     * @throws IOException
     */
    public ServerListener(Socket socket, Controller controller) throws IOException{
        this.socket = socket;
        this.controller = controller;
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

    }


    /**
     * gets objects from server
     */
    @Override
    public void run() {
        try {
            while(true){
                Message tmp = (Message) objectInputStream.readObject();
                controller.addMessage(String.format("%s: %s%n", tmp.getName(), tmp.getMessage()));
            }

        } catch(ClassNotFoundException | IOException e) {
            System.err.println("Disconeccted from server");
        } finally {
            try {
                objectInputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
