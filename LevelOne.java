import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LevelOne extends Actor {
    public LevelOne() {
        GreenfootImage image = new GreenfootImage("Level One", 24, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}