import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class NoteLine extends Actor {
    public NoteLine() {
        GreenfootImage image = new GreenfootImage(10, 400); // Width of the line, height of the world
        image.setColor(Color.WHITE);
        image.fillRect(0, 0, 10, 400); // Fill the rectangle with white color
        setImage(image);
    }
}