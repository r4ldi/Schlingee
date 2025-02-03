import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PlayButton extends Actor {
    public PlayButton() {
        GreenfootImage image = new GreenfootImage("Play", 24, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}