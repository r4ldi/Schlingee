import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SelectStage extends World {
    public SelectStage() {    
        super(600, 400, 1); 
        prepare();
    }

    private void prepare() {
        GreenfootImage bg = new GreenfootImage("Stage5.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        LevelOne levelOne = new LevelOne();
        addObject(levelOne, getWidth() / 2, getHeight() / 2 - 75);
        
        Level2 level2 = new Level2();
        addObject(level2, getWidth() / 2, getHeight() / 2 - 50);
        
        Level3 level3 = new Level3();
        addObject(level3, getWidth() / 2, getHeight() / 2 - 25);
        
        Level4 level4 = new Level4();
        addObject(level4, getWidth() / 2, getHeight() / 2 - 0);
        
        Level5 level5 = new Level5();
        addObject(level5, getWidth() / 2, getHeight() / 2 - -25);
        
        
    }
}