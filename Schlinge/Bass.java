import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Bass extends Actor
{
    // Variable declarations
    String Taste;
    int speed; // Speed of the note
    GreenfootSound bgSound;
    
    public void act() 
    {
        // Set the speed based on the current world
        if (getWorld() instanceof MyWorld) {
            speed = 1;  // Lowest speed for MyWorld
        } 
        else if (getWorld() instanceof Stage2) {
            speed = 2;  // Slightly higher speed for Stage2
        }
        else if (getWorld() instanceof Stage3) {
            speed = 4;  // Even higher speed for Stage3
        }
        else {
            speed = 0;  // No movement for MyWorldOver (game over screen)
        }
        
        this.setLocation(this.getX(), (this.getY() + speed));
        
        if (this.isAtEdge()) { 
            // Stop sound based on current world
            if (getWorld() instanceof MyWorld){
                bgSound = ((MyWorld)getWorld()).getBgSound();
            } 
            else if (getWorld() instanceof Stage2) {
                bgSound = ((Stage2)getWorld()).getBgSound();
            }
            else if (getWorld() instanceof Stage3) {
                bgSound = ((Stage3)getWorld()).getBgSound();
            }
            
            bgSound.stop();
            
            // Transition to game over screen
            Greenfoot.setWorld(new MyWorldOver());
        }
    }    
}
