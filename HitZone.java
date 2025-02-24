import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HitZone extends Actor {
    public HitZone() {
        GreenfootImage image = new GreenfootImage(600, 10); // Width of the world, height of the hit zone
        image.setColor(Color.MAGENTA);
        image.fillRect(0, 0, 600, 10); // Fill the rectangle with red color
        setImage(image);
    }
}