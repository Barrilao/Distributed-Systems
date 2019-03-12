/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1hw;


import java.util.Collections;

/**
 *
 * @author Barri
 */

public class Computer extends Thread {
    Switch s;
    public long MAC;
    int msgNo = 0;
    
    public Computer(Switch s, long MAC) { 
        this.s = s;
        this.MAC = MAC;
    }

    public Message produce(long destinationMAC){
        String message = "nº"+msgNo+" creared by "+MAC;
        
        msgNo++;
        Message msg = new Message(message,MAC, destinationMAC);
        return msg;
    }
    
    public synchronized void run()  {
        System.out.println("Computer "+ MAC + " started");
        //get random destination computer:
        try{
            //sleep(20);   
            System.out.println("HELLO"+MAC);
            
            Collections.shuffle(s.ourList);
            
            //System.out.println(s.ourList.get(0));

            Message current = produce(1112);
            System.out.println("The message:" + current.content + "was sent by " + MAC);
            msgNo++;
            if (msgNo > 10)
                return;
            s.add(current);
        }
        catch(Exception e){}
    
    try {
        System.out.println("HELLO"+MAC);
        Message messageReceived = s.get();
        if(messageReceived.destinationMAC == MAC){
            System.out.println("The message: " + messageReceived.content + "was received by " + MAC);
            }
        }catch(Exception e){}
    }
}
