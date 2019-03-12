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
public class Lab1HW {

  
    public static void main(String[] args) {
        //System.out.println("App started");
        Switch s = new Switch();
        Computer comp1 = new Computer(s,1111);
        Computer comp2 = new Computer(s,1112);
        Computer comp3 = new Computer(s,1113);
        Computer comp4 = new Computer(s,1114);
        Computer comp5 = new Computer(s,1115);
        
        s.addComputerToSwitchMap(comp1);
        s.addComputerToSwitchMap(comp2);
        s.addComputerToSwitchMap(comp3);
        s.addComputerToSwitchMap(comp4);
        s.addComputerToSwitchMap(comp5);
        
        comp1.start();
        comp2.start();
        comp3.start();
        comp4.start();
        comp5.start();
    }
    
}
