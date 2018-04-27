package application;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	ServerSocket serverSocket;
    Socket connection = null;
    PrintWriter out;
    DataInputStream in;
    String message;
    Thread runningThread = null;
    Server(){}
    public void run()
    {
        try{
            //1. creating a server socket
        	serverSocket = new ServerSocket(2020);
            //2. Wait for connection
            System.out.println("Waiting for connection");
            connection = serverSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            //3. get Input and Output streams
            out = new PrintWriter(connection.getOutputStream());
            out.flush();
            in = new DataInputStream(connection.getInputStream());
            sendMessage("Connection successful");
            //4. The two parts communicate via the input and output streams
            do{
                try{
                    message = (String)in.readUTF();
                    System.out.println("client>" + message);
                    if (message.equals("bye"))
                        sendMessage("bye");
                }
                catch (IOException e) {
                	e.printStackTrace();
                }
            }while(!message.equals("bye"));
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        
        
    }
    void sendMessage(String msg)
    {
        out.write(msg);
		out.flush();
		System.out.println("server>" + msg);
    }
	
}
