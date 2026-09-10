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
    private int health = 100;
    
    public Scoreboard()
    {
        updateImage();
    }
    
    public void addPoint()
    {
        score++;
        updateImage();
    }
    
    public void setHealth(int newHealth)
    {
        this.health = Math.max(0, newHealth); //not allowing health to display below 0
        updateImage();
    }
    
    private void updateImage()
    {
        String text = "Dragon Kills: " + score + " | HP: " + health + "/100";
        GreenfootImage img = new GreenfootImage(text, 18, Color.WHITE, Color.BLACK);
        setImage(img);
    }
    
    public int getScore()
    {
        return score;
    }
}
