package tjobah.a7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import jforsythe.Message;
import jforsythe.MessageType;

import java.io.*;
import java.net.Socket;


public class Controller {
    @FXML
    TextField txtInput;
    @FXML
    TextArea txtOutput;
    @FXML
    TextArea txtMembers;

    private String name;
    private Socket socket;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;

    /**
     * connects the client to the server
     * @throws IOException
     */
    public void initialize() throws IOException {
        TextInputDialog nameInput = new TextInputDialog("What is your name");
        nameInput.setHeaderText("Welecome to CMPS3390 Chat");
        nameInput.showAndWait();
        name = nameInput.getResult();

        socket = new Socket("odin.cs.csub.edu", 3390);
        outputStream = socket.getOutputStream();
        outputStream.flush();
        objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.flush();


        ServerListener serverListener= new ServerListener(this.socket, this);
        serverListener.start();

        Message tmp = new Message(MessageType.CONNECT, name, "hi");
        objectOutputStream.writeObject(tmp);
        objectOutputStream.flush();

    }

    /**
     * takes input from user and pushes to other users
     * @param actionEvent
     * @throws IOException
     */
    public void onInputAction(ActionEvent actionEvent) throws IOException {
        Message tmp = new Message(MessageType.MESSAGE,name,txtInput.getText());
        txtInput.clear();
        objectOutputStream.writeObject(tmp);
        objectOutputStream.flush();
    }

    /**
     * exits out of the server cleanly
     * @throws IOException
     */
    public void Exit() throws IOException {
        objectOutputStream.close();
        outputStream.close();
        socket.close();
    }

    public void addMessage(String msg) {
        txtOutput.appendText(msg);
        txtMembers.appendText(name);
    }
}
