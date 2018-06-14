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

public class ConRan4 extends Thread{
    int doDai3 = 3;
    int []x;
    int []y;
    
    
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;
    
    int vector = ConRan3.GO_RIGHT;
    
    long t1 = 0;
    long t5 = 0;
    
    int speed3 = 200;
    
    int maxLen =7;
    
    boolean udAfterChangeVt = true;
    
    ConRan4(){
        x = new int[200];
        y = new int[200];
        
        x[0]=24;
        y[0]=5;
        
        x[1]=24;
        y[1]=6;
        
        x[2]=24;
        y[2]=7;
    }
    public void resetGame4(){
        x = new int[200];
        y = new int[200];
        
        x[0]=24;
        y[0]=5;
        
        x[1]=24;
        y[1]=6;
        
        x[2]=24;
        y[2]=7;;
        
        doDai3 = 3;
        speed3 =200;
        vector = ConRan4.GO_RIGHT;
    }
    public void setVector4(int v){
        if(vector != -v && udAfterChangeVt){
            vector = v;
            udAfterChangeVt = false;
        }
        
    }
    public boolean toaDoCoNamTrongThanRan(int x1, int y1){
        for(int i=0;i<doDai3;i++)
            if(x[i]==x1&&y[i]==y1) return true;
        
        return false;
    }
    public Point layToaDoMoi4(){
        Random r = new Random();
        int x;
        int y;
        do{
           x= r.nextInt(31);
           y = r.nextInt(31);
        }while(toaDoCoNamTrongThanRan(x,y));
        
        return new Point(x,y);
    }
    public int getCurrentSpeed3(){
        int speed3 = 200;
        for(int i = 0;i<GameScreen.CurrentLevel3;i++)
            speed3*=0.8;
            return speed3;
    }
    public void update4(){
        
        if(doDai3 == maxLen) {
            
            GameScreen.CurrentLevel3++;
            maxLen += 5;
            speed3 = getCurrentSpeed3();
        }
        
        for(int i = 1;i<doDai3;i++){
            if(x[0]==x[i]&&y[0]==y[i] ){
                GameScreen.isPlaying=false;
                GameScreen.isGameOver=true;
                
                GameScreen.diem1=0;
                GameScreen.CurrentLevel1=1;
     
                GameScreen.diem=0;
                GameScreen.CurrentLevel=1;
                
                GameScreen.diem2=0;
                GameScreen.CurrentLevel2=1;
                
                GameScreen.diem3=0;
                GameScreen.CurrentLevel3=1;
            }
        }
        
        if(System.currentTimeMillis()-t5>200){
            
            udAfterChangeVt = true;
            
            Data.HeadGoUp.update();
            Data.HeadGoDown.update();
            Data.HeadGoLeft.update();
            Data.HeadGoRight.update();
            
            t5 = System.currentTimeMillis();
        }
        
        
        if(System.currentTimeMillis()-t1>speed3){
            
            
            
            
            if(GameScreen.bg[x[0]][y[0]]==2){
                doDai3++;
                GameScreen.bg[x[0]][y[0]]=0;
                GameScreen.bg[layToaDoMoi4().x][layToaDoMoi4().y]=2;
                
                GameScreen.diem3+=1;
            }
           
            for(int i=doDai3-1;i>0;i--){
                x[i]=x[i-1];
                y[i]=y[i-1];
            }
            
            if(vector == ConRan3.GO_UP) y[0]--;
            if(vector == ConRan3.GO_DOWN) y[0]++;
            if(vector == ConRan3.GO_LEFT) x[0]--;
            if(vector == ConRan3.GO_RIGHT) x[0]++;

             
            
            if(x[0]<0) x[0]=31;
            if(x[0]>31) x[0]=0;
            if(y[0]<0) y[0]=31;
            if(y[0]>31) y[0]=0;

            t1 = System.currentTimeMillis();
        }
       
    }
   
}

