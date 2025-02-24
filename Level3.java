import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level3 extends Actor {
    public Level3() {
        GreenfootImage image = new GreenfootImage("Level Three", 24, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Stage3());
        }
    }
}