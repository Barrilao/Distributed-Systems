/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.server;

/**
 *
 * @author Barri
 */
public class Request {
    public int terminalId;
    public String function;
    public Object args;
    public Request(int terminalId, String function, Object args){
        this.terminalId = terminalId;
        this.function = function;
        this.args = args;
    }
}
