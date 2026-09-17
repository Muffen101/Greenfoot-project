import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays score and current game level at the top of the screen
 * 
 */
public class Scoreboard extends Actor
{
    private int score = 0;
    private int level = 1;
    private int playerPoint = 0;
    private int lives = 3; 
    private static int winScore = 5;
    
    public Scoreboard()
    {
        updateImage();
    }
    
    public void setScore(int newScore)
    {
        this.score = newScore;
        updateImage();
    }
    
    /**
     * Increments player points and checks if win target was met.
     */
    public boolean addPoint()
    {
        playerPoint++;
        updateImage();
        return playerPoint >= winScore; //returns true if player has reached the winScore
    }
    
    public boolean loseLife()
    {
        lives--;
        updateImage();
        
        if (lives <= 0)
        {
            return false; //game over
        }
        return true; //player still alive
    }
    
    public void setLevel(int newLevel)
    {
        this.level = newLevel;
        updateImage();
    }
    
    /**
     * Redraws the score text graphic on the screen whenever score or level changes.
     */
    private void updateImage()
    {
        String text = "Lives: " + lives + " | Point: " + playerPoint + " | Bounces: " + score + " | Level: " + level; //format string output
        GreenfootImage img = new GreenfootImage(text, 18, Color.WHITE, Color.BLACK); //this creates the layout for the image/text, by creating an image out of text
        setImage(img); //applies the image to this
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
