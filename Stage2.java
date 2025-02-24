import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Stage2 extends World
{
    // Variables declaration
    StartScreen start = new StartScreen();
    Score currentScore = new Score("Currentscore");
    Score highScore = new Score("Highscore");
    GreenfootSound bgSound = new GreenfootSound("background.mp3"); // Different background sound for Stage2

    // Constructor
    public Stage2()
    {    
        super(600, 400, 1); 
        prepare();
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
       
        // Check for game over condition
        if (isGameOver()) {
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
        GreenfootImage bg = new GreenfootImage("Stage2.png"); // Different background for Stage2
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