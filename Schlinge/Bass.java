import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bass extends Actor {
    // Variable declarations
    String Taste;
    int speed; // Speed of the note (now dynamically set)
    GreenfootSound bgSound;

    public Bass() {
        // Default speed for Stage 1
        this.speed = 1;
    }

    public void act() {
        // Check if the current world is Stage2 and adjust speed
        if (getWorld() instanceof Stage2) {
            this.speed = 2; // Faster speed for Stage 2
        }

        // Move the note downward
        this.setLocation(this.getX(), this.getY() + speed);

        // End the game if the note reaches the edge
        if (this.isAtEdge()) {
            // Stop sound based on the current world
            if (getWorld() instanceof MyWorld) {
                bgSound = ((MyWorld) getWorld()).getBgSound();
            } else if (getWorld() instanceof Stage2) {
                bgSound = ((Stage2) getWorld()).getBgSound();
            } else {
                bgSound = ((MyWorldOver) getWorld()).getBgSound();
            }
            bgSound.stop();

            // Transition to game over screen
            Greenfoot.setWorld(new MyWorldOver());
        }
    }
}