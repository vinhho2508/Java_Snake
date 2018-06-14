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

public class ConRan2 extends Thread{
    int doDai2 = 3;
   int []x;
   int []y;
    
    
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;
    
    int vector = ConRan2.GO_UP;
    
    long t1 = 0;
    long t4 = 0;
    
    int speed2 = 200;
    
    int maxLen =7;
    
    boolean udAfterChangeVt = true;
    
   ConRan2(){
        x = new int[200];
        y = new int[200];
        
        x[0]=12;
        y[0]=10;
        
        x[1]=12;
        y[1]=11;
        
        x[2]=12;
        y[2]=12;
        /*x[0]=13;
        y[0]=14;
        
        x[1]=13;
        y[1]=15;
        
        x[2]=13;
        y[2]=16;*/
        
    }
   public void gietRan()
    {
        for(int i=0;i<doDai2;i++){
            x[i]=0;
        y[i]=0;
        }
    }
    public void resetGame2(){
        x = new int[200];
        y = new int[200];
        
        x[0]=12;
        y[0]=10;
        
        x[1]=12;
        y[1]=11;
        
        x[2]=12;
        y[2]=12;
        
        doDai2 = 3;
        speed2 =200;
        vector = ConRan2.GO_UP;
    }
    public void setVector2(int v){
        if(vector != -v && udAfterChangeVt){
            vector = v;
            udAfterChangeVt = false;
        }
        
    }
    public boolean toaDoCoNamTrongThanRan(int x1, int y1){
        for(int i=0;i<doDai2;i++)
            if(x[i]==x1&&y[i]==y1) return true;
        
        return false;
    }
   
    public int getCurrentSpeed2(){
        int speed2 = 200;
        for(int i = 0;i<GameScreen.CurrentLevel2;i++)
            speed2*=0.8;
            return speed2;
    }
    public void update2(){
        
        if(doDai2 == maxLen) {
            
            GameScreen.CurrentLevel2++;
            maxLen += 5;
            speed2 = getCurrentSpeed2();
        }
        
      
        if(System.currentTimeMillis()-t4>200){
            
            udAfterChangeVt = true;
            Data.HeadGoUp.update();
            Data.HeadGoDown.update();
            Data.HeadGoLeft.update();
            Data.HeadGoRight.update();
            
            t4 = System.currentTimeMillis();
        }
        
        
        if(System.currentTimeMillis()-t1>speed2){
        
            for(int i=doDai2-1;i>0;i--){
                x[i]=x[i-1];
                y[i]=y[i-1];
            }
            
            if(vector == ConRan2.GO_UP) y[0]--;
            if(vector == ConRan2.GO_DOWN) y[0]++;
            if(vector == ConRan2.GO_LEFT) x[0]--;
            if(vector == ConRan2.GO_RIGHT) x[0]++;
            
            if(x[0]<0) x[0]=31;
            if(x[0]>31) x[0]=0;
            if(y[0]<0) y[0]=31;
            if(y[0]>31) y[0]=0;

            t1 = System.currentTimeMillis();
        }
       
    }
    public void veRan2(Graphics g){
        g.setColor(Color.green);
        for(int i=1;i<doDai2;i++)
            g.fillRect(x[i]*20+10,y[i]*20+10, 18, 18);
            //g.drawImage(Data.imageBody, x[i]*20+GameScreen.padding, y[i]*20+GameScreen.padding, null);
        if(vector==ConRan2.GO_UP) g.drawImage(Data.HeadGoUp.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan2.GO_DOWN) g.drawImage(Data.HeadGoDown.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan2.GO_RIGHT) g.drawImage(Data.HeadGoRight.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan2.GO_LEFT) g.drawImage(Data.HeadGoLeft.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
    }
}

