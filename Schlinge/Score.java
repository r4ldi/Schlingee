import java.io.*;
import greenfoot.*;

public class Score extends Actor {
    //Variabeln deklaration
    private String name;
    private int score;
    private Handler handler;

    public Score(String name) {
        this.setName(name);
        this.handler = new Handler(name);
        try {
            this.score = this.handler.read();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void setName(String name) {
        this.name = name + ": ";
    }

    public void setScore(int score) {
        this.score = score;
        try {
            this.handler.write(score);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public int getScore() {
        return score;
    }

    public void updateScore() {
        setImage(new GreenfootImage(this.name + this.score, 16, Color.WHITE, new Color(0, 0, 0, 0)));
    }
}
