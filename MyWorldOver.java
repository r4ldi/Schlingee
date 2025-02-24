import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorldOver extends World {
    public MyWorldOver() {    
        super(600, 400, 1); 
        // Initialize the world
        GreenfootImage bg = new GreenfootImage("GameOver.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
    }
}