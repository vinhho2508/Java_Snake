/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinhho.snakeclient;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static vinhho.snakeclient.Snake.input;
import static vinhho.snakeclient.Snake.output;


public class RanAnMoiJava extends JFrame{

    GameScreen game;
    
    
    public RanAnMoiJava(){
        
        setSize(870,685);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        game = new GameScreen();
        add(game);
        this.addKeyListener(new handler());
        Read r=new Read();
        r.start();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        RanAnMoiJava f = new RanAnMoiJava();
    }
    private class handler implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            
            if(e.getKeyCode()==KeyEvent.VK_SPACE){
                GameScreen.isPlaying=!GameScreen.isPlaying;
                if(GameScreen.isGameOver) {
                    GameScreen.isGameOver=false;
                    game.ran.resetGame();
                    game.ran1.resetGame1();
                    game.ran2.resetGame2();
                    game.ran3.resetGame3();
                 
                   
                }
            }
            if(Snake.player==0)
            {
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    game.ran.setVector(ConRan.GO_UP);
                       output.println("*,1,6,6,6,5,5");
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    game.ran.setVector(ConRan.GO_DOWN);
                    output.println("*,2,6,6,6,5,5");
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    game.ran.setVector(ConRan.GO_LEFT);
                    output.println("*,3,6,6,6,5,5");
                    
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    game.ran.setVector(ConRan.GO_RIGHT);
                    output.println("*,4,6,6,6,5,5");
                }
            }
            if(Snake.player==1)
            {
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    game.ran1.setVector1(ConRan1.GO_UP);
                    output.println("*,6,1,6,6,5,5");

                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    game.ran1.setVector1(ConRan1.GO_DOWN);
                    output.println("*,6,2,6,6,5,5");
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    game.ran1.setVector1(ConRan1.GO_LEFT);
                    output.println("*,6,3,6,6,5,5");
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    game.ran1.setVector1(ConRan1.GO_RIGHT);
                    output.println("*,6,4,6,6,5,5");
                }
            }
            if(Snake.player==2)
            {
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    game.ran2.setVector2(ConRan2.GO_UP);
                    output.println("*,6,6,1,6,5,5");

                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    game.ran2.setVector2(ConRan2.GO_DOWN);
                    output.println("*,6,6,2,6,5,5");
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    game.ran2.setVector2(ConRan2.GO_LEFT);
                    output.println("*,6,6,3,6,5,5");
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    game.ran2.setVector2(ConRan2.GO_RIGHT);
                    output.println("*,6,6,4,6,5,5");
                }
            }
            if(Snake.player==3)
            {
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    game.ran3.setVector3(ConRan3.GO_UP);
                    output.println("*,6,6,6,1,5,5");

                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    game.ran3.setVector3(ConRan3.GO_DOWN);
                     output.println("*,6,6,6,2,5,5");
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    game.ran3.setVector3(ConRan3.GO_LEFT);
                     output.println("*,6,6,6,3,5,5");
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    game.ran3.setVector3(ConRan3.GO_RIGHT);
                     output.println("*,6,6,6,4,5,5");
                }
            }
           
        }

        @Override
        public void keyReleased(KeyEvent e) {}
        
    }
class Read extends Thread {
    public void run() {
      String message;
      while(!Thread.currentThread().isInterrupted()){
        try {
          message = input.readLine();
          //System.out.println("message receive: "+message);
          if(message != null){
            
            if(message.charAt(0)=='*')
            {
                message = message.substring(2, message.length()-1);
                String[] a=message.split(",");
                int p1,p2,p3,p4;
                int x,y=10;
                p1=Integer.parseInt(a[0]);
                p2=Integer.parseInt(a[1]);
                p3=Integer.parseInt(a[2]);
                p4=Integer.parseInt(a[3]);
                //x=Integer.parseInt(a[4]);
                //y=Integer.parseInt(a[5]);
                Random rand = new Random();

                x = rand.nextInt(20) + 2;
                y = rand.nextInt(20) + 2;
                System.out.println(p1+ " "+ p2+" "+p3+" "+p4+" "+x+" "+y);
                if(p1==0)
                {
                    GameScreen.ran.gietRan();
                    if(Snake.player==0)
                    {
                        
                        GameScreen.isGameOver=true;
                    }
                }
                if(p2==0)
                {
                    GameScreen.ran1.gietRan();
                    if(Snake.player==1)
                    {
                        
                        GameScreen.isGameOver=true;
                    }
                }
                if(p3==0)
                {
                    GameScreen.ran2.gietRan();
                    if(Snake.player==2)
                    {
                        
                        GameScreen.isGameOver=true;
                    }
                }
                if(p4==0)
                {
                    GameScreen.ran3.gietRan();
                    if(Snake.player==3)
                    {
                        
                        GameScreen.isGameOver=true;
                    }
                }
                if(p1==1)
                {
                    game.ran.setVector(ConRan.GO_UP);
                }
                if(p1==2)
                {
                    game.ran.setVector(ConRan.GO_DOWN);
                }
                if(p1==3)
                {
                    game.ran.setVector(ConRan.GO_LEFT);
                }
                if(p1==4)
                {
                    game.ran.setVector(ConRan.GO_RIGHT);
                }
                if(p2==1)
                {
                    game.ran1.setVector1(ConRan.GO_UP);
                }
                if(p2==2)
                {
                    game.ran1.setVector1(ConRan.GO_DOWN);
                }
                if(p2==3)
                {
                    game.ran1.setVector1(ConRan.GO_LEFT);
                }
                if(p2==4)
                {
                    game.ran1.setVector1(ConRan.GO_RIGHT);
                }
                if(p3==1)
                {
                    game.ran2.setVector2(ConRan.GO_UP);
                }
                if(p3==2)
                {
                    game.ran2.setVector2(ConRan.GO_DOWN);
                }
                if(p3==3)
                {
                    game.ran2.setVector2(ConRan.GO_LEFT);
                }
                if(p3==4)
                {
                    game.ran2.setVector2(ConRan.GO_RIGHT);
                }
                if(p4==1)
                {
                    game.ran3.setVector3(ConRan.GO_UP);
                }
                if(p4==2)
                {
                    game.ran3.setVector3(ConRan.GO_DOWN);
                }
                if(p4==3)
                {
                    game.ran3.setVector3(ConRan.GO_LEFT);
                }
                if(p4==4)
                {
                    game.ran3.setVector3(ConRan.GO_RIGHT);
                }
                if(p1==5)
                {
                    KiemTra.r.doDai++;
                    GameScreen.bg[KiemTra.r.x[0]][KiemTra.r.y[0]]=0;
                    GameScreen.bg[x][y]=2; 
                    GameScreen.diem+=1;
                    if(Snake.player==0)
                    {
                        x=KiemTra.layToaDoMoi().x;
                        y=KiemTra.layToaDoMoi().y;
                        output.println("*,6,6,6,6,"+x+","+y);
                    }
                }
                if(p2==5)
                {
                    KiemTra.r1.doDai1++;
                    GameScreen.bg[KiemTra.r1.x[0]][KiemTra.r1.y[0]]=0;
                    GameScreen.bg[x][y]=2; 
                    GameScreen.diem1+=1;
                    if(Snake.player==0)
                    {
                        x=KiemTra.layToaDoMoi().x;
                        y=KiemTra.layToaDoMoi().y;
                        output.println("*,6,6,6,6,"+x+","+y);
                    }
                }
                if(p3==5)
                {
                    KiemTra.r2.doDai2++;
                    GameScreen.bg[KiemTra.r2.x[0]][KiemTra.r2.y[0]]=0;
                    GameScreen.bg[x][y]=2; 
                    GameScreen.diem2+=1;
                    if(Snake.player==0)
                    {
                        x=KiemTra.layToaDoMoi().x;
                        y=KiemTra.layToaDoMoi().y;
                        output.println("*,6,6,6,6,"+x+","+y);
                    }
                }
                if(p4==5)
                {
                    KiemTra.r3.doDai3++;
                    GameScreen.bg[KiemTra.r3.x[0]][KiemTra.r3.y[0]]=0;
                    GameScreen.bg[x][y]=2; 
                    GameScreen.diem3+=1;
                    if(Snake.player==0)
                    {
                        x=KiemTra.layToaDoMoi().x;
                        y=KiemTra.layToaDoMoi().y;
                        output.println("*,6,6,6,6,"+x+","+y);
                    }
                }
            }
          }
        }
        catch (IOException ex) {
          System.err.println("Failed to parse incoming message");
        }
        
      }
      
        
      
    }
  }
}
