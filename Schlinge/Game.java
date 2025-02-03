import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Game extends Actor {
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
    private final int hitZoneTolerance = 50; // Increased tolerance for hitting the note

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
            Bass newBass = new Bass();
            a.add(newBass);
            getWorld().addObject(newBass, randomLane, 10);
            // Assign key and image based on lane
            switch (randomLane) {
                case 75:
                    newBass.Taste = "A";
                    newBass.setImage("Images/a_icon.png");
                    break;
                case 225:
                    newBass.Taste = "S";
                    newBass.setImage("Images/s_icon.png");
                    break;
                case 375:
                    newBass.Taste = "D";
                    newBass.setImage("Images/d_icon.png");
                    break;
                case 525:
                    newBass.Taste = "F";
                    newBass.setImage("Images/f_icon.png");
                    break;
            }
            z = speed;
        } else {
            z--;
        }

        // Handle key presses and check for misses
        handleKeyPress("a", 75);  // Lane 1 (A)
        handleKeyPress("s", 225); // Lane 2 (S)
        handleKeyPress("d", 375); // Lane 3 (D)
        handleKeyPress("f", 525); // Lane 4 (F)

        // Check for missed notes
        for (int i = 0; i < a.size(); i++) {
            Bass bass = a.get(i);
            if (bass.getY() > hitZoneY + hitZoneTolerance) {
                endGame(); // End the game if a note is missed
                return;
            }
        }
    }

    /**
     * Helper method to handle key presses and check for hits.
     */
    private void handleKeyPress(String key, int laneX) {
        if (Greenfoot.isKeyDown(key)) {
            for (int i = 0; i < a.size(); i++) {
                Bass bass = a.get(i);
                if (bass.getX() == laneX && Math.abs(bass.getY() - hitZoneY) <= hitZoneTolerance) {
                    // Correct key pressed and note is within the hit zone
                    getWorld().removeObject(bass);
                    a.remove(i);
                    Greenfoot.playSound(randomSoundHit());
                    currentScore.setScore(currentScore.getScore() + 10);
                    currentScore.updateScore();
                    break; // Exit the loop after removing the note
                }
            }
        }
    }
}