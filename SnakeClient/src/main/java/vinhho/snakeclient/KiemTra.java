/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vinhho.snakeclient;

import java.awt.Point;
import java.util.Random;
import static vinhho.snakeclient.GameScreen.ktr;
import static vinhho.snakeclient.Snake.output;

/**
 *
 * @author DELL
 */
public class KiemTra extends Thread{
    static ConRan r;
    static ConRan1 r1;
    static ConRan2 r2;
    static ConRan3 r3;
    KiemTra(ConRan a,ConRan1 b,ConRan2 c,ConRan3 d)
    {
        r=a;
        r1=b;
        r2=c;
        r3=d;
    }
    void KiemTraVaCham()
    {
        for(int i=1;i<r.doDai;i++)
        {
            if(r1.x[0]==r.x[i] && r1.y[0]==r.y[i])
                GameScreen.ktr[1]=0;
            if(r2.x[0]==r.x[i] && r2.y[0]==r.y[i])
                GameScreen.ktr[2]=0;
            if(r3.x[0]==r.x[i] && r3.y[0]==r.y[i])
                GameScreen.ktr[3]=0;
            if(r.x[0]==r.x[i] && r.y[0]==r.y[i])
                GameScreen.ktr[0]=0;
            
         
        }
        for(int j=1;j<r1.doDai1;j++)
        {
            if(r1.x[0]==r1.x[j] && r1.y[0]==r1.y[j])
                GameScreen.ktr[1]=0;
            if(r2.x[0]==r1.x[j] && r2.y[0]==r1.y[j])
                GameScreen.ktr[2]=0;
            if(r3.x[0]==r1.x[j] && r3.y[0]==r1.y[j])
                GameScreen.ktr[3]=0;
            if(r.x[0]==r1.x[j] && r.y[0]==r1.y[j])
                GameScreen.ktr[0]=0;
            
            
        }
        for(int k=1;k<r2.doDai2;k++)
        {
            if(r1.x[0]==r2.x[k] && r1.y[0]==r2.y[k])
                GameScreen.ktr[1]=0;
            if(r2.x[0]==r2.x[k] && r2.y[0]==r2.y[k])
                GameScreen.ktr[2]=0;
            if(r3.x[0]==r2.x[k] && r3.y[0]==r2.y[k])
                GameScreen.ktr[3]=0;
            if(r.x[0]==r2.x[k] && r.y[0]==r2.y[k])
                GameScreen.ktr[0]=0;
            
        }
        for(int l=1;l<r3.doDai3;l++)
        {
            if(r1.x[0]==r3.x[l] && r1.y[0]==r3.y[l])
                GameScreen.ktr[1]=0;
            if(r2.x[0]==r3.x[l] && r2.y[0]==r3.y[l])
                GameScreen.ktr[2]=0;
            if(r3.x[0]==r3.x[l] && r3.y[0]==r3.y[l])
                GameScreen.ktr[3]=0;
            if(r.x[0]==r3.x[l] && r.y[0]==r3.y[l])
                GameScreen.ktr[0]=0;
       
    }
   }
    public static Point layToaDoMoi() throws InterruptedException{
        Random ra = new Random();
        int x;
        int y;
        do{
           x= ra.nextInt(31);
           y = ra.nextInt(31);
        }while(r.toaDoCoNamTrongThanRan(x,y) && r1.toaDoCoNamTrongThanRan1(x, y) && 
                r2.toaDoCoNamTrongThanRan(x, y)&& r3.toaDoCoNamTrongThanRan(x, y));
        KiemTra.sleep(100);
        return new Point(x,y);
    }
    void AnMoi()
    {
        if(GameScreen.bg[r.x[0]][r.y[0]]==2){
                r.doDai++;
                GameScreen.bg[r.x[0]][r.y[0]]=0;
                //GameScreen.bg[layToaDoMoi().x][layToaDoMoi().y]=2; 
                GameScreen.diem+=1;
                output.println("*,5,6,6,6,5,5");
            }
        if(GameScreen.bg[r1.x[0]][r1.y[0]]==2){
                r1.doDai1++;
                GameScreen.bg[r1.x[0]][r1.y[0]]=0;
                //GameScreen.bg[layToaDoMoi().x][layToaDoMoi().y]=2;     
                GameScreen.diem1+=1;
                output.println("*,6,5,6,6,5,5");
            }
        if(GameScreen.bg[r2.x[0]][r2.y[0]]==2){
                r2.doDai2++;
                GameScreen.bg[r2.x[0]][r2.y[0]]=0;
               // GameScreen.bg[layToaDoMoi().x][layToaDoMoi().y]=2;    
                GameScreen.diem2+=1;
                output.println("*,6,6,5,6,5,5");
            }
        if(GameScreen.bg[r3.x[0]][r3.y[0]]==2){
                r3.doDai3++;
                GameScreen.bg[r3.x[0]][r3.y[0]]=0;
                //GameScreen.bg[layToaDoMoi().x][layToaDoMoi().y]=2;    
                GameScreen.diem3+=1;
                output.println("*,6,6,6,5,5,5");
            }
    }
}
