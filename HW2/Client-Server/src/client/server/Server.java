/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;
import java.util.AbstractMap.SimpleEntry;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Barri
 */
public class Server implements Runnable {
    protected TreeMap<Integer, Boolean> images;
    protected BlockingQueue<Request> requests;
    protected TreeMap<Integer, BlockingQueue<Response>> responses;
    public Server(BlockingQueue<Request> requests) {
        this.requests = requests;
        this.responses = new TreeMap<Integer, BlockingQueue<Response>>();
        // initialize random data for images
        images = new TreeMap<Integer, Boolean>();
        for (int i = 0; i < 20; i++) {
            images.put(i, (i < 5 ? true : false)); // 0 - 4 downloaded
        }
    }
    public void connect(int terminalId, BlockingQueue<Response> responseQueue) {
        this.responses.put(terminalId, responseQueue);
    }
    @Override
    public void run() {
        try {
            while (true) {
                Request req = requests.take();
                switch (req.function) {
                    case "getImageStatus":
                        responses.get(req.terminalId).put(new Response(req.function, this.getImageStatus()));
                        break;
                    case "download":
                        int imgId = (int) req.args;
                        SimpleEntry<Integer, Boolean> entry = new SimpleEntry<Integer, Boolean>(imgId, download(imgId));
                        responses.get(req.terminalId).put(new Response(req.function, entry));
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // returns a copy of images
    // here its OK to be not synchronized as its a read only thing
    public TreeMap<Integer, Boolean> getImageStatus() {
        TreeMap<Integer, Boolean> imagesCopy = new TreeMap<Integer,Boolean>();
        imagesCopy.putAll(images);
        return imagesCopy;
    }
    // download() is "synchronized" thus only 1 thread can modify it at a time
    // a check is done before the actual downloading (modification of images)
    // thus ensuring the img is not downloaded by any other in the process
    public synchronized boolean download(int img) {
        if (images.get(img) == false) {
            images.put(img, true);
            return true;
        }
        return false;
    }
    
}
