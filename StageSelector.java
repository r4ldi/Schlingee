import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StageSelector extends Actor {
    private Button stage4Button;

    public StageSelector() {
        GreenfootImage image = new GreenfootImage("Select Stage", 24, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(image);
        stage4Button = new Button("Stage 4");
        getWorld().addObject(stage4Button, 100, 100);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new SelectStage());
        }
        if (Greenfoot.mouseClicked(stage4Button)) {
            Greenfoot.setWorld(new Stage4());
        }
    }
}