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
    private int level = 1;
    
    public Scoreboard()
    {
        updateImage();
    }
    
    public void setScore(int newScore)
    {
        this.score = newScore;
        updateImage();
    }
    
    public void setLevel(int newLevel)
    {
        this.level = newLevel;
        updateImage();
    }
    
    private void updateImage()
    {
        String text = "Bounces: " + score + " | Level: " + level;
        GreenfootImage img = new GreenfootImage(text, 18, Color.WHITE, Color.BLACK);
        setImage(img);
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLevel()
    {
        return level;
    }
}
