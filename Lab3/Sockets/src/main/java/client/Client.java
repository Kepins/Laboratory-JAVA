package client;

import message.Message;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP  = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args){
        Socket socket = null;
        BufferedReader keyboard = null;
        ObjectInputStream input = null;
        ObjectOutputStream out = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            input = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            boolean serverReady = false;

            // wait for "ready" from server
            while(!serverReady){
                try {
                    Object o = input.readObject();
                    if (o instanceof String serversResponse && serversResponse.equals("ready")) {
                        System.out.println("Server is ready");
                        serverReady = true;
                    }
                } catch (IOException ignore){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                } catch(ClassNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            //get n from the user
            System.out.println("n: ");
            Integer n = Integer.parseInt(keyboard.readLine());
            out.writeObject(n);

            serverReady = false;
            //wait for "read for messages from server"
            while(!serverReady){
                try {
                    Object o = input.readObject();
                    if (o instanceof String serversResponse && serversResponse.equals("ready for messages")) {
                        System.out.println("Server is ready for messages");
                        serverReady = true;
                    }
                } catch (IOException ignore){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                } catch(ClassNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            //send n messages to server
            for (int i=1;i<=n;i++){
                System.out.println("Message " + i + ": ");
                String content = keyboard.readLine();
                Message message = new Message();
                message.setNumber(i);
                message.setContent(content);
                out.writeObject(message);
            }
            serverReady = false;

            //wait for "finished"
            while(!serverReady){
                try {
                    Object o = input.readObject();
                    if (o instanceof String serversResponse && serversResponse.equals("finished")) {
                        System.out.println("Server finished");
                        serverReady = true;
                    }
                } catch (IOException ignore){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                } catch(ClassNotFoundException ex) {
                    System.err.println(ex.getMessage());
                }
            }


        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }finally{
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            if (keyboard != null){
                try{
                    keyboard.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            if (input != null){
                try{
                    input.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            if (out != null){
                try{
                    out.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }


}
