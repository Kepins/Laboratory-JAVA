package server.handler;

import message.Message;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket client;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ClientHandler(Socket clientSocket) {
        this.client = clientSocket;
        try {
            this.output = new ObjectOutputStream(client.getOutputStream());
            this.input = new ObjectInputStream(client.getInputStream());
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        try{

            //send ready to client
            output.writeObject("ready");
            output.flush();

            boolean gotN = false;
            Integer n=null;

            //wait for "n" from user
            while(!gotN){
                try {
                    Object o = input.readObject();
                    if (o instanceof Integer clientResponse) {
                        gotN = true;
                        n=clientResponse;
                        System.out.println("Got n: "+n+" from user");
                    }
                } catch (IOException | ClassNotFoundException ignore){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            }

            //send "ready for messages" to client
            output.writeObject("ready for messages");

            //wait for n messages
            while( n>0 ){
                try {
                    Object o = input.readObject();
                    if (o instanceof Message clientResponse) {
                        System.out.println("Message " + clientResponse.getNumber() + ": " + clientResponse.getContent());
                        n = n -1;
                    }
                } catch (IOException | ClassNotFoundException ignore){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            }

            //send "ready for messages" to client
            output.writeObject("finished");



        }catch (IOException ex){
            System.err.println(ex.getMessage());
        } finally {
            try{
                output.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
            try{
                input.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
