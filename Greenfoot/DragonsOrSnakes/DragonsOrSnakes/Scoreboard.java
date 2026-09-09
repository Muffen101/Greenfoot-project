import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scoreboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scoreboard extends Actor
{
    private int score = 0;
    
    public Scoreboard()
    {
        updateImage();
    }
    
    public void addPoint()
    {
        score++;
        updateImage();
    }
    
    private void updateImage()
    {
        GreenfootImage img = new GreenfootImage("Dragon Kills: " + score, 24, Color.WHITE, Color.BLACK);
        setImage(img);
    }
    
    public int getScore()
    {
        return score;
    }
}
