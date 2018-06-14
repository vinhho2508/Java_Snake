/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vinhho.snakeserver;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Color;
import java.util.Random;
public class Server {

  private int port;
  private List<User> clients;
  private ServerSocket server;
  List<Room> rooms=new ArrayList<Room>();
  public static void main(String[] args) throws IOException {
    new Server(12345).run();
  }
  public List<Room> getrooms()
  {
      return rooms;
  }
  public Server(int port) {
    this.port = port;
    this.clients = new ArrayList<User>();
  }

  public void run() throws IOException {
    server = new ServerSocket(port) {
      protected void finalize() throws IOException {
        this.close();
      }
    };
    System.out.println("Port 12345 is now open.");

    while (true) {
      // accepts a new client
      Socket client = server.accept();

      // get nickname of newUser
      String nickname = (new Scanner ( client.getInputStream() )).nextLine();
      nickname = nickname.replace(",", ""); //  ',' use for serialisation
      nickname = nickname.replace(" ", "_");
      System.out.println("New Client: \"" + nickname + "\"\n\t     Host:" + client.getInetAddress().getHostAddress());

      // create new User
      User newUser = new User(client, nickname);

      // add newUser message to list
      this.clients.add(newUser);

      // Welcome msg
      newUser.getOutStream().println(newUser.getNickname());

      // create a new thread for newUser incoming messages handling
      new Thread(new UserHandler(this, newUser)).start();
    }
  }

  // delete a user from the list
  public void removeUser(User user){
    this.clients.remove(user);
  }

  // send incoming msg to all Users
  public void broadcastMessages(String msg) {
    for (User client : this.clients) {
      client.getOutStream().println(msg);
    }
  }

  // send list of clients to all Users
  public void broadcastAllUsers(){
    for (User client : this.clients) {
      client.getOutStream().println(this.clients);
    }
  } 
  
  public void sendMessageToUser(String msg, User userSender){
    
     for (User client : this.clients) {
         if(client.getRoom()!=-1){
            if (client.getRoom()==userSender.getRoom())
                msg+=","+client.getNickname();
     }
     }
    for (User client : this.clients) {
      if (client.getRoom()==userSender.getRoom()) {
       // userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() +": " + msg);
        client.getOutStream().println(msg);
      }
    }
  }
  public void sendMessageToUser2(String msg, User userSender){
    
    
    for (User client : this.clients) {
      if (client.getRoom()==userSender.getRoom()) {
       // userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() +": " + msg);
        client.getOutStream().println(msg);
        System.out.println("send "+msg+" to "+client.getNickname());
      }
    }
  }
  public void sendMessageToUser3(String msg, User userSender){
    
    
    for (User client : this.clients) {
      if (client.getRoom()==userSender.getRoom() && !client.getNickname().equals(userSender.getNickname())) {
       // userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() +": " + msg);
        client.getOutStream().println(msg);
      }
    }
  }
 
}
class Room
{
    int RoomID=-1;
    int st=1;
    List<User> users=new ArrayList<User>();
    void setST(int i)
    {
        st=i;
    }
    int getST()
    {
        return this.st;
    }
    void setID(int i)
    {
        RoomID=i;
    }
    int getID()
    {
        return this.RoomID;
    }
    void addUser(User user)
    {
        if(user!=null)
            users.add(user);
        return;
    }
    List<User> getUsers()
    {
        return users;
    }
    String roomString()
    {
        String s="+,"+this.RoomID+","+this.st;
        for(User u:users)
        {
           s+=","+u.getNickname();
        }
        return s;
    }
}
class UserHandler implements Runnable {

  public Server server;
  public User user;

  public UserHandler(Server server, User user) {
    this.server = server;
    this.user = user;
    this.server.broadcastAllUsers();
  }

  public void run() {
    String message;

    // when there is a new message, broadcast to all
    Scanner sc = new Scanner(this.user.getInputStream());
    while (sc.hasNextLine()) {
      message = sc.nextLine();
        // update user list
      //server.broadcastMessages(message);
      System.out.println(message);
      if (message.charAt(0) == '-'){
          String room=message.substring(1,5);
          //System.out.println(room);
          for(Room r:server.getrooms())
          {
              if(room.equals(Integer.toString(r.RoomID)))
              {
                  r.setST(0);
                  String msg="#";
                  //msg+=r.roomString();
                  server.sendMessageToUser2(msg, user);
              }
          }
      }
      if(message.charAt(0)=='*')
      {
          server.sendMessageToUser2(message, user);
      }
      
       if (message.charAt(0) == '+'){
        if(message.contains(" ")){
        //  System.out.println("private msg : " + message);
            String msg=new String();
          //int firstSpace = message.indexOf(" ");
          String roomid= message.substring(2, 6);
         // System.out.println(roomid);
          List<Room> rooms=server.getrooms();
          System.out.println(rooms.size());
          for(Room r:rooms)
          {
             // System.out.println(r.getID());
              
                if(Integer.parseInt(roomid)==r.getID())
                {
                    if(r.getST()==0)
                    {
                        msg="-"+user.getNickname();
                        server.broadcastMessages(msg);
                    }
                    System.out.println(r.getUsers().size());
                    //msg="+,"+roomid;
                    r.addUser(user); 
                    user.setRoom(r.getID());
                    msg+=r.roomString();                
                    System.out.println(msg);

                    server.sendMessageToUser2(msg, user);
                }
          
          }
          //System.out.println(userPrivate);
          //server.sendMessageToUser(message.substring(firstSpace+1, message.length()), user);
        }
        
        else
        {
            Random rand = new Random();

            int  n = rand.nextInt(9999) + 1000;
            Room r=new Room();
            r.setID(n);
            r.setST(1);
            r.addUser(this.user);
            user.setRoom(n);
            server.rooms.add(r);
            String msg="+,"+n;
            server.sendMessageToUser(msg, user);
        
        }

      // Gestion du changement
      }
    }
    // end of Thread
    server.removeUser(user);
    this.server.broadcastAllUsers();
    sc.close();
  }
}

class User {
  private static int nbUser = 0;
  private int userId;
  private PrintStream streamOut;
  private InputStream streamIn;
  private String nickname;
  private Socket client;
  private int RoomID;

  // constructor
  public User(Socket client, String name) throws IOException {
    this.streamOut = new PrintStream(client.getOutputStream());
    this.streamIn = client.getInputStream();
    this.client = client;
    this.nickname = name;
    this.userId = nbUser;
    nbUser += 1;
  }
  public void setRoom(int a)
  {
      this.RoomID=a;
  }
  public int getRoom()
  {
      return this.RoomID;
  }
  // getteur
  public PrintStream getOutStream(){
    return this.streamOut;
  }

  public InputStream getInputStream(){
    return this.streamIn;
  }

  public String getNickname(){
    return this.nickname;
  }
  public String toString(){

    return this.getNickname();

  }
  // print user with his color
 }
