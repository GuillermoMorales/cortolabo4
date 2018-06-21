/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import static java.lang.Thread.sleep;
import static java.lang.Thread.yield;
import javax.swing.JLabel;

/**
 *
 * @author LN710Q
 */
public class AnimalThread2 extends Thread{
    private String nombre;
    private int limite;
    private JLabel animal;
    private int x;
    private int y;

    public AnimalThread2() {
    }

    public AnimalThread2(String nombre, int x, int y, int limite, JLabel animal) {
        this.nombre = nombre;
        this.limite = limite;
        this.animal = animal;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void run(){
        for (int i=x; i>=this.limite; i-=10){
            System.out.println(this.nombre +" regresa");
            this.animal.setLocation(i,y);
            try{
                sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(this.nombre+ " ha regresado a la meta");
        
        yield();
    }
    
}
