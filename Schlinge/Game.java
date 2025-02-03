import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * 
 * 
 * Autoren: Florian Weder, Anujan Chandrawathanan, Jonathan Wiggli 
 * Version 1
 * 
 *
 */
public class Game extends Actor{
    
    
    // Variable declarations
    private boolean kDown, aDown, sDown, dDown, fDown; // Booleans for individual key inputs
    private ArrayList<Bass> a = new ArrayList<Bass>(); // ArrayList for the "key" objects
    int speed = 25; // Integer for block intervals
    int z = speed;  // Integer for block interval check
    Score currentScore; // currentScore display
    Score highScore; // highScore display
    GreenfootSound bgSound;
    private int[] lanes = {75, 225, 375, 525}; // Positions for the lanes
    private final int hitZoneY = 350; // Y-coordinate for the hit zone
    private final int hitZoneTolerance = 20; // Tolerance for hitting the note

    /**
     * The method randomPos defines one of four random positions on the x-axis.
     */
    public int randomPos(){
        return (Greenfoot.getRandomNumber(4)*150+75);
    }
    
    /**
     * The method endGame shows the game over screen and stops the program.
     */
    public void endGame(){
        MyWorldOver gameOverWorld = new MyWorldOver();
        Greenfoot.setWorld(gameOverWorld);
        if (getWorld() instanceof MyWorld){
                bgSound = ((MyWorld)getWorld()).getBgSound();
               
            }else{
                bgSound = ((MyWorldOver)getWorld()).getBgSound();
            }
            
        bgSound.stop();
    }
    
    // A variable from 1-8 is randomly generated so that a different sound is played each time
    public String randomSoundHit(){
        return ((Greenfoot.getRandomNumber(8) + 1 ) + ".mp3");
    }
    
    
    
    /**
     * The act method is executed at the start.
     */    
    public void act(){
        
        Greenfoot.setSpeed(50);
        
        if (getWorld() instanceof MyWorld){
            currentScore = ((MyWorld)getWorld()).getCurrentScore();
            highScore = ((MyWorld)getWorld()).getHighScore(); 
        }else{
            currentScore = ((MyWorldOver)getWorld()).getCurrentScore();
            highScore = ((MyWorldOver)getWorld()).getHighScore();
        }
        
        if(currentScore.getScore() > highScore.getScore()){
            highScore.setScore(currentScore.getScore());
        }
        
        if(z == 0){
            int randomLane = lanes[Greenfoot.getRandomNumber(lanes.length)];
            a.add(new Bass());
            int aLast = a.size() - 1;
            getWorld().addObject(a.get(aLast), randomLane, 10);

            switch (randomLane) {
                case 75:
                    a.get(aLast).Taste = "A";
                    a.get(aLast).setImage("Images/a_icon.png");
                    break;
                case 225:
                    a.get(aLast).Taste = "S";
                    a.get(aLast).setImage("Images/s_icon.png");
                    break;
                case 375:
                    a.get(aLast).Taste = "D";
                    a.get(aLast).setImage("Images/d_icon.png");
                    break;
                case 525:
                    a.get(aLast).Taste = "F";
                    a.get(aLast).setImage("Images/f_icon.png");
                    break;
            }
            z = speed;
        } else {
            z--;
        }

        // If "a" is pressed and the last object in the ArrayList a does not have the key "a", endGame() is executed
        if (a.size() > 0 && aDown != Greenfoot.isKeyDown("a")) {
            aDown = !aDown;
            if (Greenfoot.isKeyDown(a.get(0).Taste)) {
            } else if (Greenfoot.isKeyDown("a")) {
                endGame();
            }
        }

        // If "s" is pressed and the last object in the ArrayList a does not have the key "s", endGame() is executed
        if (a.size() > 0 && sDown != Greenfoot.isKeyDown("s")) {
            sDown = !sDown;
            if (Greenfoot.isKeyDown(a.get(0).Taste)) {
            } else if (Greenfoot.isKeyDown("s")) {
                endGame();
            }
        }

        // If "d" is pressed and the last object in the ArrayList a does not have the key "d", endGame() is executed
        if (a.size() > 0 && dDown != Greenfoot.isKeyDown("d")) {
            dDown = !dDown;
            if (Greenfoot.isKeyDown(a.get(0).Taste)) {
            } else if (Greenfoot.isKeyDown("d")) {
                endGame();
            }
        }

        // If "f" is pressed and the last object in the ArrayList a does not have the key "f", endGame() is executed
        if (a.size() > 0 && fDown != Greenfoot.isKeyDown("f")) {
            fDown = !fDown;
            if (Greenfoot.isKeyDown(a.get(0).Taste)) {
            } else if (Greenfoot.isKeyDown("f")) {
                endGame();
            }
        }

        // If the correct key is pressed, the last object is removed from the ArrayList a and from the world
        if (a.size() > 0 && kDown != Greenfoot.isKeyDown(a.get(0).Taste)) {
            kDown = !kDown;
            if (Greenfoot.isKeyDown(a.get(0).Taste) && Math.abs(a.get(0).getY() - hitZoneY) <= hitZoneTolerance) {
                getWorld().removeObject(a.get(0));
                Greenfoot.playSound(randomSoundHit());
                a.remove(0);
                currentScore.setScore(currentScore.getScore() + 10);
                currentScore.updateScore();
            }
        }
    }
}



