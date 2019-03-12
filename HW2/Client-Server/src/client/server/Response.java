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
public class Response {
    public String function;
    public Object data;
    public Response(String function, Object data) {
        this.function = function;
        this.data = data;
    }
}
