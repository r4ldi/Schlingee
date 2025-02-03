import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Bass extends Actor
{
    // Variable declarations
    String Taste;
    int speed = 3; // Speed of the note
    GreenfootSound bgSound;
    
    public void act() 
    {
        this.setLocation(this.getX(), (this.getY()+speed));
        if(this.isAtEdge()){ 
            // Stop sound based on current world
            if (getWorld() instanceof MyWorld){
                bgSound = ((MyWorld)getWorld()).getBgSound();
            } 
            else if (getWorld() instanceof Stage2) {
                bgSound = ((Stage2)getWorld()).getBgSound();
            }
            else {
                bgSound = ((MyWorldOver)getWorld()).getBgSound();
            }
            
            bgSound.stop();
            
            // Transition to game over screen
            Greenfoot.setWorld(new MyWorldOver());
        }
    }    
}