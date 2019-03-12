/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1hw;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Barri
 */
public class Switch {
    public static ArrayList<Message> msgQueue = new ArrayList<>();
    public final int MAX_SIZE = 20;
    public List<Long> ourList = new ArrayList();
    
    public Switch() {}

    public void addComputerToSwitchMap(Computer comp){
        ourList.add(comp.MAC);
    }
    
    public synchronized void add(Message m) {

        while (msgQueue.size() >= MAX_SIZE) {
            try {
                sleep(1000);

            } catch (InterruptedException e) {
            }
        }
        
        msgQueue.add(m);

        //notifyAll();
        System.out.println("Message was added");

    }

    public synchronized Message get() throws InterruptedException {
        if(msgQueue.size() != 0)
            return msgQueue.remove(0);
        return null;
    }
}
