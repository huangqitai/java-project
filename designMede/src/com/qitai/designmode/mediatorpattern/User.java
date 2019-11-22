package com.qitai.designmode.mediatorpattern;

public class User {
    private String name;

    public User(String name){
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void sendMessage(String message,User receiveUser){
        MessageMediator.forwardMessage(this,message,receiveUser);
    }
    public void receive(User user,String message){
        System.out.println(user.getName()+"-->"+this.name+":"+message);
    }
}
