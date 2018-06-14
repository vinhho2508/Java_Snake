/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinhho.snakeclient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import static java.lang.Thread.sleep;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import static vinhho.snakeclient.Snake.output;


public class GameScreen extends JPanel implements Runnable{
    
    static int [][] bg = new int [32][32];
    
    static int padding = 10;
    static int WIDTH = 630;
    static int HEIGHT = 625;
    static int[] ktr={1,1,1,1};
    
    
    static boolean isPlaying = true;
    static boolean enableTextStartGame = true;
    
    //Thread ran,ran1,ran2,ran3,ran4;
    static ConRan ran;
    static ConRan1 ran1;
    static ConRan2 ran2;
    static ConRan3 ran3;
    ConRan4 ran4;
    KiemTra kt;
    
    Thread thread;
    
    static int CurrentLevel = 1;
    static int CurrentLevel1 = 1;
    static int CurrentLevel2 = 1;
    static int CurrentLevel3 = 1;
    static int diem = 0;
    static int diem1= 0; 
    static int diem2= 0;
    static int diem3= 0; 
    
    static boolean isGameOver = false;
    public GameScreen(){
       
        ran = new ConRan();
        ran1 = new ConRan1();
        ran2 = new ConRan2();
        ran3 = new ConRan3();
        ran4 = new ConRan4();
        
        ran.start();
        ran1.start();
        ran2.start();
        ran3.start();
        ran4.start();
        kt=new KiemTra(ran,ran1,ran2,ran3);
        Data.loadImage();
        Data.loadAllAnim();
        
        
        
        bg[10][10]=2;
        
        thread = new Thread(this);
        thread.start();   
    }
    public void run(){
        long t = 0;
        long t2 = 0;
        while(true){
            
            if(System.currentTimeMillis()-t2>500){
                enableTextStartGame=!enableTextStartGame;
                t2 = System.currentTimeMillis();
            }
            
            if(isPlaying){
                if(System.currentTimeMillis()-t>200){
                    Data.Worm.update();
                    t=System.currentTimeMillis();
                }
                
                ran.update();
                ran1.update1();
                ran2.update2();
                ran3.update3();
                ran4.update4();
                kt.AnMoi();
                kt.KiemTraVaCham();
                
                if(ktr[0]==0){
                  // output.println("*,0,6,6,6,5,5");
                    ran.gietRan();
                    ktr[0]=1;
                    if(Snake.player==0)
                        isGameOver=true;
                    ran.interrupt();
                }
                if(ktr[1]==0)
                {
                   //output.println("*,6,0,6,6,5,5");
                    ran1.gietRan();
                    ktr[1]=1;
                    if(Snake.player==1)
                        isGameOver=true;
                    ran1.interrupt();
                }
                if(ktr[2]==0)
                {
                  //  output.println("*,6,6,0,6,5,5");
                    ran2.gietRan();
                    ktr[2]=1;
                    if(Snake.player==2)
                        isGameOver=true;
                    ran2.interrupt();
                }
                if(ktr[3]==0)
                {
                  // output.println("*,6,6,6,0,5,5");
                    ran3.gietRan();
                    ktr[3]=1;
                    if(Snake.player==3)
                        isGameOver=true;
                    ran3.interrupt();
                }
            }
            repaint();
            /*try {
                sleep(20);
            } catch (InterruptedException ex) {}*/
        }
    }
    public void paintBg(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH+padding*2+200, HEIGHT+padding*2);
        for(int i=0;i<32;i++)
            for(int j=0;j<32;j++){
                //g.fillRect(i*20+1, j*20+1, 18, 18);
                if(bg[i][j]==2){
                   g.drawImage(Data.Worm.getCurrentImage(), i*20-7+padding, j*20-7+padding, null);
                }
            }
        
    }
    private void veKhung(Graphics g){
        g.setColor(Color.orange);
        g.drawRect(0, 0, WIDTH+padding*2, HEIGHT+padding*2);
        g.drawRect(1, 1, WIDTH+padding*2-2, HEIGHT+padding*2-2);
        g.drawRect(2, 2, WIDTH+padding*2-4, HEIGHT+padding*2-4);
        
        g.drawRect(0, 0, WIDTH+padding*2+200, HEIGHT+padding*2);
        g.drawRect(1, 1, WIDTH+padding*2-2+200, HEIGHT+padding*2-2);
        g.drawRect(2, 2, WIDTH+padding*2-4+200, HEIGHT+padding*2-4);
    }
    public void paint(Graphics g){
        paintBg(g);
        ran.veRan(g);
        ran1.veRan1(g);
        ran2.veRan2(g);
        ran3.veRan3(g);
        veKhung(g);
        
        if(!isPlaying){
            if(enableTextStartGame){
                g.setColor(Color.white);
                g.setFont(g.getFont().deriveFont(25.0f));
                g.drawString("PRESS SPACE TO PLAY GAME!", 175,300);
            }
        }
        if(isGameOver){
                g.setColor(Color.white);
                g.setFont(g.getFont().deriveFont(28.0f));
                g.drawString("GAME OVER!", 200, 400);
        }
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(23.0f));
        g.drawString("LEVELr1: "+CurrentLevel, 700, 50);
        
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Diem: "+diem, 700, 75);
        
        
        g.setFont(g.getFont().deriveFont(23.0f));
        g.drawString("LEVELr2: "+CurrentLevel1, 700, 125);
        
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Diem: "+diem1, 700, 150);
        
        
        g.setFont(g.getFont().deriveFont(23.0f));
        g.drawString("LEVELr3: "+CurrentLevel2, 700, 200);
        
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Diem: "+diem2, 700, 225);
        
        
        g.setFont(g.getFont().deriveFont(23.0f));
        g.drawString("LEVELr4: "+CurrentLevel3, 700, 275);
        
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Diem: "+diem3, 700, 300);
    }
}
