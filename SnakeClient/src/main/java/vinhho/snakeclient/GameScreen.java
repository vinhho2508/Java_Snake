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

/**
 *
 * @author phamn
 */
public class GameScreen extends JPanel implements Runnable{
    
    static int [][] bg = new int [20][20];
    
    static int padding = 10;
    static int WIDTH = 400;
    static int HEIGHT = 400;
    
    static boolean isPlaying = false;
    static boolean enableTextStartGame = true;
    
    ConRan ran;
    
    Thread thread;
    
    static int CurrentLevel = 1;
    static int diem = 0;
    
    static boolean isGameOver = false;
    public GameScreen(){
        
        ran = new ConRan();
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
            }
            repaint();
            try {
                sleep(20);
            } catch (InterruptedException ex) {}
        }
    }
    public void paintBg(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH+padding*2+200, HEIGHT+padding*2);
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++){
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
        veKhung(g);
        
        if(!isPlaying){
            if(enableTextStartGame){
                g.setColor(Color.white);
                g.setFont(g.getFont().deriveFont(18.0f));
                g.drawString("PRESS SPACE TO PLAY GAME!", 60, 200);
            }
        }
        if(isGameOver){
                g.setColor(Color.white);
                g.setFont(g.getFont().deriveFont(28.0f));
                g.drawString("GAME OVER!", 100, 250);
        }
        g.setColor(Color.white);
        g.setFont(g.getFont().deriveFont(28.0f));
        g.drawString("LEVEL: "+CurrentLevel, 450, 100);
        
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Diem: "+diem, 450, 150);
    }
}
