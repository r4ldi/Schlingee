import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorldOver extends World
{
    //Variabeln deklaration
    
    StartScreen start = new StartScreen();
    Score currentScore = new Score("Currentscore");
    Score highScore = new Score("Highscore");
    GreenfootSound bgSound = new GreenfootSound("background.mp3");
    
    //stehlt das Objekt anderen Klassen zu verfügung
    public GreenfootSound getBgSound(){
        return this.bgSound;
    }
    
    //stehlt das Objekt anderen Klassen zu verfügung
    public Score getCurrentScore(){
        return this.currentScore;
    }
    
    //stehlt das Objekt anderen Klassen zu verfügung
    public Score getHighScore(){
        return this.highScore;
    }
    
    // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
    public MyWorldOver()
    {    
        super(600, 400, 1);
        prepare();
    }
    
    //Objekte werden auf der Welt platziert
    private void prepare()
    {
        GreenfootImage bg = new GreenfootImage("Stage4.png");
        bg.scale(getWidth(), getHeight()); // Scale the image to fit the world size
        setBackground(bg);
        
        // Add note lines and hit zone indicator
        addObject(new NoteLine(), 75, 200);
        addObject(new NoteLine(), 225, 200);
        addObject(new NoteLine(), 375, 200);
        addObject(new NoteLine(), 525, 200);
        addObject(new HitZone(), 300, 350);

        start.setImage("images/Game_Over.png");        
        
        currentScore.updateScore();
        highScore.updateScore();
        
        addObject(start, 300, 200);
        addObject(currentScore, 60, 10);
        addObject(highScore, 540, 10);
    }

    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new MyWorld());
        }
        
        if (Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
