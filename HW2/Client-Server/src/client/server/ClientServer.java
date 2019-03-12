/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;
import java.util.concurrent.*;
/**
 *
 * @author Barri
 */
public class ClientServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BlockingQueue<Request> requests = new ArrayBlockingQueue<Request>(10);
        Server server = new Server(requests);
        Terminal t1 = new Terminal(1, requests, new ArrayBlockingQueue<Response>(10),server);
        Terminal t2 = new Terminal(2, requests, new ArrayBlockingQueue<Response>(10),server);
        Terminal t3 = new Terminal(3, requests, new ArrayBlockingQueue<Response>(10),server);
        new Thread(server).start();
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
    
}
