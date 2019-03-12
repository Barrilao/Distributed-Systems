/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.*;
import java.util.concurrent.*;

/**
 *
 * @author Barri
 */
public class Terminal implements Runnable {
    protected Server server;
    protected int terminalId;
    protected BlockingQueue<Request> requests;
    protected BlockingQueue<Response> responses;
    public Terminal(int terminalId, BlockingQueue<Request> requests,
    BlockingQueue<Response> responses, Server server) {
        this.terminalId = terminalId;
        this.requests = requests;
        this.responses = responses;
        this.server = server;
        this.server.connect(this.terminalId, this.responses);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void run() {
        try {
            // "simulation" - send requests to server
            requests.put(new Request(this.terminalId, "getImageStatus", null));
            requests.put(new Request(this.terminalId, "download", 10));
            requests.put(new Request(this.terminalId, "getImageStatus", null));
            requests.put(new Request(this.terminalId, "download", 11));
            requests.put(new Request(this.terminalId, "getImageStatus", null));
            requests.put(new Request(this.terminalId, "download", 12));
            requests.put(new Request(this.terminalId, "getImageStatus", null));
            while (true) {
                Response res = responses.take();
                switch (res.function) {
                    case "getImageStatus":
                    TreeMap<Integer, Boolean> images = (TreeMap<Integer, Boolean>) res.data;
                    //System.out.println("getImageStatus");
                    for (Entry<Integer, Boolean> image : images.entrySet()) {
                        System.out.println(" - " + image.getKey() + " \t " +(image.getValue() ? "Posted" : "Not published"));
                    }
                    break;
                    case "download":
                    SimpleEntry<Integer, Boolean> data = (SimpleEntry<Integer, Boolean>) res.data;
                    System.out.println(terminalId + ": Request Image " + data.getKey() + " " + (data.getValue() ? "Successful" : "Unsucessful"));
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
