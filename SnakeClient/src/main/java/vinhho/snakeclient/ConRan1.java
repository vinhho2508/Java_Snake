/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinhho.snakeclient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class ConRan1 extends Thread
{
    int doDai1 = 3;
    int []x;
    int []y;
    ConRan ran;
    
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;
    
    int vector = ConRan1.GO_LEFT;
    
    long t1 = 0;
    long t3 = 0;
    
    int speed1 = 200;
    
    int maxLen =7;
    
    boolean udAfterChangeVt = true;
    
   ConRan1(){
        x = new int[200];
        y = new int[200];
        
        x[0]=14;
        y[0]=14;
        
        x[1]=15;
        y[1]=14;
        
        x[2]=16;
        y[2]=14;
    }
   public void gietRan()
    {
        for(int i=0;i<doDai1;i++){
            x[i]=0;
        y[i]=0;
        }
    }
    public void resetGame1(){
        x = new int[200];
        y = new int[200];
        
        x[0]=14;
        y[0]=14;
        
        x[1]=15;
        y[1]=14;
        
        x[2]=16;
        y[2]=14;
        
        doDai1 = 3;
        speed1 =200;
        vector = ConRan1.GO_LEFT;
    }
    public void setVector1(int v){
        if(vector != -v && udAfterChangeVt){
            vector = v;
            udAfterChangeVt = false;
        }
        
    }
    public boolean toaDoCoNamTrongThanRan1(int x1, int y1){
        for(int i=0;i<doDai1;i++)
            if(x[i]==x1&&y[i]==y1) return true;
        
        return false;
    }
    
    public int getCurrentSpeed1(){
        int speed1 = 200;
        for(int i = 0;i<GameScreen.CurrentLevel1;i++)
            speed1*=0.8;
            return speed1;
    }
    public void update1(){
        
        if(doDai1 == maxLen) {
            
            GameScreen.CurrentLevel1++;
            maxLen += 5;
            speed1 = getCurrentSpeed1();
        }
       
        
        if(System.currentTimeMillis()-t3>200){
            
            udAfterChangeVt = true;
            
            Data.HeadGoUp.update();
            Data.HeadGoDown.update();
            Data.HeadGoLeft.update();
            Data.HeadGoRight.update();
            
            t3 = System.currentTimeMillis();
        }
        
        
        if(System.currentTimeMillis()-t1>speed1){
            
            
            
            
          
           
            for(int i=doDai1-1;i>0;i--){
                x[i]=x[i-1];
                y[i]=y[i-1];
            }
            
            if(vector == ConRan1.GO_UP) y[0]--;
            if(vector == ConRan1.GO_DOWN) y[0]++;
            if(vector == ConRan1.GO_LEFT) x[0]--;
            if(vector == ConRan1.GO_RIGHT) x[0]++;

             
            
            if(x[0]<0) x[0]=31;
            if(x[0]>31) x[0]=0;
            if(y[0]<0) y[0]=31;
            if(y[0]>31) y[0]=0;

            t1 = System.currentTimeMillis();
        }
       
    }
    public void veRan1(Graphics g){
        g.setColor(Color.white);
        for(int i=1;i<doDai1;i++)
            g.fillRect(x[i]*20+10,y[i]*20+10, 18, 18);
            //g.drawImage(Data.imageBody, x[i]*20+GameScreen.padding, y[i]*20+GameScreen.padding, null);
        if(vector==ConRan1.GO_UP) g.drawImage(Data.HeadGoUp.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan1.GO_DOWN) g.drawImage(Data.HeadGoDown.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan1.GO_RIGHT) g.drawImage(Data.HeadGoRight.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan1.GO_LEFT) g.drawImage(Data.HeadGoLeft.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
    }
}

