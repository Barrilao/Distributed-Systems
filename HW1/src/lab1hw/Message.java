/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1hw;

/**
 *
 * @author Barri
 */
public class Message {
    public String content;
    public long sourceMAC;
    public long destinationMAC;

    public Message(String content, long sourceMAC, long destinationMAC) {
        this.content = content;
        this.sourceMAC = sourceMAC;
        this.destinationMAC = destinationMAC;
    }
}
