import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainMenu extends World {
    public MainMenu() {    
        super(600, 400, 1); 
        prepare();
    }

    private void prepare() {
        GreenfootImage bg = new GreenfootImage("Stage2.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        PlayButton playButton = new PlayButton();
        addObject(playButton, getWidth() / 2, getHeight() / 2 - 50);

        StageSelector stageSelector = new StageSelector();
        addObject(stageSelector, getWidth() / 2, getHeight() / 2 + 50);
    }
}