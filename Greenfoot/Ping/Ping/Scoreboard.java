import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays score and current game level at the top of the screen.
 * Scoreboard manages lives, points, level display and win checks.
 */
public class Scoreboard extends Actor
{
    private int score = 0;
    private int level = 1;
    private int playerPoint = 0;
    private int lives = 3; //amount of times the player can lose lives
    private static int winScore = 5; //winning target to score
    
    /**
     * Updates and runs the score UI display
     */
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
     * Increments player points and returns true if win target was met.
     */
    public boolean addPoint()
    {
        playerPoint++;
        updateImage();
        return playerPoint >= winScore; //returns true if player has reached the winScore
    }
    
    /**
     * Deducts 1 life and returns false if the player has lost all of their lives.
     */
    public boolean loseLife()
    {
        lives--;
        updateImage();
        
        if (lives <= 0)
        {
            return false; //if the player has less or equal to 0 lives left, then game over
        }
        return true; //player still alive and plays with the rest of the lives left
    }
    
    public void setLevel(int newLevel)
    {
        this.level = newLevel;
        updateImage();
    }
    
    /**
     * Redraws the score text graphic on the screen whenever lives, points, bounces or level changes.
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
