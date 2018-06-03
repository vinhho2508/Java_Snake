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

public class ConRan {
    int doDai = 3;
    int []x;
    int []y;
    
    public static int GO_UP = 1;
    public static int GO_DOWN = -1;
    public static int GO_LEFT = 2;
    public static int GO_RIGHT = -2;
    
    int vector = ConRan.GO_DOWN;
    
    long t1 = 0;
    long t2 = 0;
    
    int speed = 200;
    
    int maxLen =7;
    
    boolean udAfterChangeVt = true;
    
    public ConRan(){
        x = new int[200];
        y = new int[200];
        
        x[0]=5;
        y[0]=4;
        
        x[1]=5;
        y[1]=3;
        
        x[2]=5;
        y[2]=2;
    }
    public void resetGame(){
        x = new int[200];
        y = new int[200];
        
        x[0]=5;
        y[0]=4;
        
        x[1]=5;
        y[1]=3;
        
        x[2]=5;
        y[2]=2;
        
        doDai = 3;
        speed =200;
        vector = ConRan.GO_DOWN;
    }
    public void setVector(int v){
        if(vector != -v && udAfterChangeVt){
            vector = v;
            udAfterChangeVt = false;
        }
        
    }
    public boolean toaDoCoNamTrongThanRan(int x1, int y1){
        for(int i=0;i<doDai;i++)
            if(x[i]==x1&&y[i]==y1) return true;
        return false;
    }
    public Point layToaDoMoi(){
        Random r = new Random();
        int x;
        int y;
        do{
           x= r.nextInt(19);
           y = r.nextInt(19);
        }while(toaDoCoNamTrongThanRan(x,y));
        
        return new Point(x,y);
    }
    public int getCurrentSpeed(){
        int speed = 200;
        for(int i = 0;i<GameScreen.CurrentLevel;i++)
            speed*=0.8;
            return speed;
    }
    public void update(){
        
        if(doDai == maxLen) {
            
            GameScreen.CurrentLevel++;
            maxLen += 5;
            speed = getCurrentSpeed();
        }
        
        for(int i = 1;i<doDai;i++){
            if(x[0]==x[i]&&y[0]==y[i]){
                GameScreen.isPlaying=false;
                GameScreen.isGameOver=true;
                
                GameScreen.diem=0;
                GameScreen.CurrentLevel=1;
            }
        }
        
        if(System.currentTimeMillis()-t2>200){
            
            udAfterChangeVt = true;
            
            Data.HeadGoUp.update();
            Data.HeadGoDown.update();
            Data.HeadGoLeft.update();
            Data.HeadGoRight.update();
            
            t2 = System.currentTimeMillis();
        }
        
        
        if(System.currentTimeMillis()-t1>speed){
            
            
            
            
            if(GameScreen.bg[x[0]][y[0]]==2){
                doDai++;
                GameScreen.bg[x[0]][y[0]]=0;
                GameScreen.bg[layToaDoMoi().x][layToaDoMoi().y]=2;
                
                GameScreen.diem+=1;
            }
           
            for(int i=doDai-1;i>0;i--){
                x[i]=x[i-1];
                y[i]=y[i-1];
            }
            
            if(vector == ConRan.GO_UP) y[0]--;
            if(vector == ConRan.GO_DOWN) y[0]++;
            if(vector == ConRan.GO_LEFT) x[0]--;
            if(vector == ConRan.GO_RIGHT) x[0]++;

             
            
            if(x[0]<0) x[0]=19;
            if(x[0]>19) x[0]=0;
            if(y[0]<0) y[0]=19;
            if(y[0]>19) y[0]=0;

            t1 = System.currentTimeMillis();
        }
       
    }
    public void veRan(Graphics g){
        g.setColor(Color.red);
        for(int i=1;i<doDai;i++)
            g.drawImage(Data.imageBody, x[i]*20+GameScreen.padding, y[i]*20+GameScreen.padding, null);
        if(vector==ConRan.GO_UP) g.drawImage(Data.HeadGoUp.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan.GO_DOWN) g.drawImage(Data.HeadGoDown.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan.GO_RIGHT) g.drawImage(Data.HeadGoRight.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
        else if(vector==ConRan.GO_LEFT) g.drawImage(Data.HeadGoLeft.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
    }
}
