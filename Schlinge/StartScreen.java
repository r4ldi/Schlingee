import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StartScreen extends Actor {
    // Variable declarations
    Score currentScore; // currentScore display
    Score highScore; // highScore display
    GreenfootSound bgSound;

    public void act() {
        Greenfoot.setSpeed(50);
        if (Greenfoot.isKeyDown("space")) {
            // Retrieve scores and sound based on the current world
            if (getWorld() instanceof MyWorld) {
                currentScore = ((MyWorld) getWorld()).getCurrentScore();
                highScore = ((MyWorld) getWorld()).getHighScore();
                bgSound = ((MyWorld) getWorld()).getBgSound();
            } 
            else if (getWorld() instanceof Stage2) {
                currentScore = ((Stage2) getWorld()).getCurrentScore();
                highScore = ((Stage2) getWorld()).getHighScore();
                bgSound = ((Stage2) getWorld()).getBgSound();
            } 
            else if (getWorld() instanceof Stage3) {
                currentScore = ((Stage3) getWorld()).getCurrentScore();
                highScore = ((Stage3) getWorld()).getHighScore();
                bgSound = ((Stage3) getWorld()).getBgSound();
            } 
            else {
                // Handle MyWorldOver or other worlds if needed
                currentScore = ((MyWorldOver) getWorld()).getCurrentScore();
                highScore = ((MyWorldOver) getWorld()).getHighScore();
                bgSound = ((MyWorldOver) getWorld()).getBgSound();
            }

            // Reset scores and start the game
            currentScore.setScore(0);
            currentScore.updateScore();
            Game game = new Game();

            // Play background sound
            bgSound.playLoop();

            // Add game elements to the world
            game.getImage().setTransparency(0);
            getWorld().addObject(game, 590, 7);
            getWorld().addObject(new HitZone(), 300, 350); // Add the hit zone indicator
            getWorld().removeObject(this); // Remove the StartScreen
        }
    }
}