import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    // Variables declaration
    StartScreen start = new StartScreen();
    Score currentScore = new Score("Currentscore");
    Score highScore = new Score("Highscore");
    GreenfootSound bgSound = new GreenfootSound("sounds/background_stage1.mp3"); // Background sound for Stage 1

    // Constructor
    public MyWorld()
    {    
        super(600, 400, 1); 
        prepare();
        bgSound.playLoop(); // Play background sound when the stage starts
    }

    // Method to provide background sound to other classes
    public GreenfootSound getBgSound(){
        return this.bgSound;
    }

    // Method to provide current score to other classes
    public Score getCurrentScore(){
        return this.currentScore;
    }

    // Method to provide high score to other classes
    public Score getHighScore(){
        return this.highScore;
    }

    // Act method
    public void act() {
        // Check if the score reaches 500 to transition to Stage2
        if (currentScore.getScore() >= 300) {
            bgSound.stop(); // Stop background sound before transitioning
            Greenfoot.setWorld(new Stage2());
        }

        // Check for game over condition
        if (isGameOver()) {
            bgSound.stop(); // Stop background sound before transitioning
            Greenfoot.setWorld(new MyWorldOver());
        }
    }

    // Method to check if the game is over
    private boolean isGameOver() {
        return false;
    }

    // Method to prepare the world
    private void prepare()
    {
        GreenfootImage bg = new GreenfootImage("Stage1.png");
        bg.scale(getWidth(), getHeight()); // Scale the image to fit the world size
        setBackground(bg);

        // Add note lines and hit zone indicator
        addObject(new NoteLine(), 75, 200);
        addObject(new NoteLine(), 225, 200);
        addObject(new NoteLine(), 375, 200);
        addObject(new NoteLine(), 525, 200);
        addObject(new HitZone(), 300, 350);

        currentScore.setScore(0); // Set initial score to a non-zero value for testing
    
        currentScore.updateScore();
        highScore.updateScore();
        
        addObject(start,300,200);
        addObject(currentScore,60, 10);
        addObject(highScore,540, 10);
    }
}