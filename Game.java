import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Game extends Actor {
    // Variable declarations
    private boolean aDown, sDown, dDown, fDown; // Booleans for individual key inputs
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
    public int randomPos() {
        return (Greenfoot.getRandomNumber(4) * 150 + 75);
    }

    /**
     * The method endGame shows the game over screen and stops the program.
     */
    public void endGame() {
        MyWorldOver gameOverWorld = new MyWorldOver();
        Greenfoot.setWorld(gameOverWorld);
        
        // Stop the background music based on the current world
        if (getWorld() instanceof MyWorld) {
            bgSound = ((MyWorld) getWorld()).getBgSound();
        } else if (getWorld() instanceof Stage2) {
            bgSound = ((Stage2) getWorld()).getBgSound();
        } else if (getWorld() instanceof Stage3) {
            bgSound = ((Stage3) getWorld()).getBgSound();
        } else {
            bgSound = ((MyWorldOver) getWorld()).getBgSound();
        }
        
        bgSound.stop();
    }

    // A variable from 1-8 is randomly generated so that a different sound is played each time
    public String randomSoundHit() {
        return ((Greenfoot.getRandomNumber(8) + 1) + ".mp3");
    }

    /**
     * The act method is executed at the start.
     */
    public void act() {
        Greenfoot.setSpeed(50);

        // Retrieve scores and sound based on the current world
        if (getWorld() instanceof MyWorld) {
            currentScore = ((MyWorld) getWorld()).getCurrentScore();
            highScore = ((MyWorld) getWorld()).getHighScore();
        } else if (getWorld() instanceof Stage2) {
            currentScore = ((Stage2) getWorld()).getCurrentScore();
            highScore = ((Stage2) getWorld()).getHighScore();
        } else if (getWorld() instanceof Stage3) {
            currentScore = ((Stage3) getWorld()).getCurrentScore();
            highScore = ((Stage3) getWorld()).getHighScore();
        } else {
            currentScore = ((MyWorldOver) getWorld()).getCurrentScore();
            highScore = ((MyWorldOver) getWorld()).getHighScore();
        }

        // Update high score if necessary
        if (currentScore.getScore() > highScore.getScore()) {
            highScore.setScore(currentScore.getScore());
        }

        // Spawn beats at intervals
        if (z == 0) {
            int randomLane = lanes[Greenfoot.getRandomNumber(lanes.length)];
            a.add(new Bass());
            int aLast = a.size() - 1;
            getWorld().addObject(a.get(aLast), randomLane, 10);

            // Assign key and image based on lane
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

        // Handle key presses and check for misses
        handleKeyPress("a", aDown, 0);
        handleKeyPress("s", sDown, 1);
        handleKeyPress("d", dDown, 2);
        handleKeyPress("f", fDown, 3);

        // Check for correct key presses and remove notes if hit
        if (a.size() > 0) {
            Bass currentBass = a.get(0); // Get the first bass note
            int currentY = currentBass.getY(); // Get the Y-position of the note

            // If object crosses the hit zone line without being hit (game over)
            if (currentY > hitZoneY + hitZoneTolerance) {
                // If no correct key was pressed and it crossed the line, game over
                if (!Greenfoot.isKeyDown(currentBass.Taste)) {
                    endGame(); // Game over if object crosses the line without being hit
                }
                return; // Exit act method to prevent further checks
            }

            // If object is within the hit zone and key is pressed correctly
            else if (Math.abs(currentY - hitZoneY) <= hitZoneTolerance) {
                // If correct key is pressed, remove object and give score
                if (Greenfoot.isKeyDown(currentBass.Taste)) {
                    getWorld().removeObject(currentBass);
                    Greenfoot.playSound(randomSoundHit());
                    a.remove(0); // Remove the note from the list
                    currentScore.setScore(currentScore.getScore() + 10); // Update score
                    currentScore.updateScore();
                } 
                // If any wrong key is pressed while the note is still above the hit zone, just ignore it
                else if ((aDown || sDown || dDown || fDown)) {
                    // Just count it as missed, but don't end the game
                }
            }
        }
    }

    /**
     * Helper method to handle key presses and check for misses.
     */
    private void handleKeyPress(String key, boolean keyDown, int index) {
        if (a.size() > 0 && keyDown != Greenfoot.isKeyDown(key)) {
            keyDown = !keyDown;
            if (Greenfoot.isKeyDown(a.get(0).Taste)) {
                // Correct key pressed
            } else if (Greenfoot.isKeyDown(key)) {
                // Wrong key pressed
                // But only end game if the object is in the hit zone (not before)
                if (Math.abs(a.get(0).getY() - hitZoneY) <= hitZoneTolerance) {
                    endGame();
                }
            }
        }
    }
}
