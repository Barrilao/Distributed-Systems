/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.*;
import java.net.*;

/**
 *
 * @author Barri
 */
public class RPCServer {

    DatagramSocket ds;
    DatagramPacket dp;
    String str, methodName, result;
    int val1, val2, val3;

    RPCServer() {
        try {
            ds = new DatagramSocket(1200);
            byte b[] = new byte[4096];
            while (true) {

                dp = new DatagramPacket(b, b.length);
                ds.receive(dp);
                str = new String(dp.getData(), 0, dp.getLength());
                if (str.equalsIgnoreCase("q")) {
                    System.exit(1);
                } else {
                    StringTokenizer st = new StringTokenizer(str, " ");
                    int i = 0;
                    while (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        methodName = token;
                        val1 = Integer.parseInt(st.nextToken());
                        val2 = Integer.parseInt(st.nextToken());
                        val3 = Integer.parseInt(st.nextToken());
                    }
                }
                System.out.println(str);
                InetAddress ia = InetAddress.getLocalHost();
                if (methodName.equalsIgnoreCase("add")) {
                    result = "" + add(val1, val2);
                }
                if (methodName.equalsIgnoreCase("sub")) {
                    result = "" + sub(val1, val2);
                }
                if (methodName.equalsIgnoreCase("mul")) {
                    result = "" + mul(val1, val2);
                }
                if (methodName.equalsIgnoreCase("div")) {
                    result = "" + div(val1, val2);
                }
                if (methodName.equalsIgnoreCase("lcm")) {
                    result = "" + lcm(val1, val2, val3);
                }
                if (methodName.equalsIgnoreCase("gcd")) {
                    result = "" + gcd(val1, val2, val3);
                }
                byte b1[] = result.getBytes();
                DatagramSocket ds1 = new DatagramSocket();
                DatagramPacket dp1 = new DatagramPacket(b1, b1.length,
                        InetAddress.getLocalHost(), 1300);
                System.out.println("result : " + result + "\n");
                ds1.send(dp1);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public int add(int val1, int val2) {
        return val1 + val2;
    }
    
    public int sub(int val1, int val2) {
        return val1 - val2;
    }
    
    public int mul(int val1, int val2) {
        return val1 * val2;
    }
    
    public double div(int val1, int val2) {
        return val1 / val2;
    }
    
    public int lcm(int numA, int numB, int numC){
        int numMax = numA;
   

         if ( numB > numMax )
             numMax = numB;
  
         if ( numC > numMax )
             numMax = numC;
   
   
   
        int i = numMax;
         while ((i % numA != 0) || (i % numB != 0) || (i % numC != 0))
             i++;
  
        return i;
    }
    
    int gcd(int a, int b, int c) {
        int mayor = a;

        if (b > mayor) {

            mayor = b;
            b = a;

        }

        if (c > mayor) {

            mayor = c;
            a = mayor;

        }

        int comun = mcd(mayor, b, c);
        return comun;
    }
    
    int mcd(int z, int u, int v) {
        
        int t, maximo=0;
        while (u > 0) {
            if (u < v) {
                t = u;
                u = v;
                v = t;

                if (0 == z % v) {
                    maximo = v;
                }
            }

            u = u - v;

        }

        return maximo;

    }
    
    public static void main(String[] args) {
        new RPCServer();
    }

}
