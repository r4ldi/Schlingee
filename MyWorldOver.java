import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorldOver extends World {
    // Variable declarations
    StartScreen start = new StartScreen();
    Score currentScore = new Score("Currentscore");
    Score highScore = new Score("Highscore");
    GreenfootSound bgSound = new GreenfootSound("background.mp3"); 

    public MyWorldOver() {    
        super(600, 400, 1);
        prepare();
    }

    public GreenfootSound getBgSound() {
        return this.bgSound;
    }

    public Score getCurrentScore() {
        return this.currentScore;
    }

    public Score getHighScore() {
        return this.highScore;
    }

    private void prepare() {
        // Fix: Use the correct background image
        GreenfootImage bg = new GreenfootImage("Stage4.png"); // Changed from Stage4.png
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        start.setImage("images/Game_Over.png");        
        
        // Remove unnecessary gameplay objects
        // (NoteLines and HitZone don't belong in a game over screen)
        addObject(start, 300, 200);
        addObject(currentScore, 60, 10);
        addObject(highScore, 540, 10);
    }

    public void act() {
        handleInput();
    }

    private void handleInput() {
        if (Greenfoot.isKeyDown("space")) {
            // Restart from the appropriate world
            if (currentScore.getScore() >= 500) {
                Greenfoot.setWorld(new Stage2());
            } else {
                Greenfoot.setWorld(new MyWorld());
            }
        }
        
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new MainMenu());
        }
    }
}