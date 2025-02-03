import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StageSelector extends Actor {
    public StageSelector() {
        GreenfootImage image = new GreenfootImage("Select Stage", 24, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new SelectStage());
        }
    }
 }