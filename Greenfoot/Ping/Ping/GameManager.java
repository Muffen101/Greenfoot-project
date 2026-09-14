/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager  
{
    private Scoreboard scoreboard;
    private boolean gameIsRunning = false;
    private int totalBounces = 0;
    private int bounceCounter = 0;
    private int currentSpeed = 2;

    /**
     * Constructor for objects of class GameManager
     */
    public GameManager(Scoreboard scoreboard)
    {
        this.scoreboard = scoreboard;
        this.gameIsRunning = true;
    }
    
    public int ballBounced()
    {
        if (!gameIsRunning)
        {
            return currentSpeed;
        }
        totalBounces++; //adds 1 to total score
        bounceCounter++; //adds 1 to the counter from 1 to 10
        
        if (scoreboard != null)
        {
            scoreboard.setScore(totalBounces); //method in scoreboard!
        }
        
        if (bounceCounter == 10)
        {
            currentSpeed++; //adds 1 in speed
            bounceCounter = 0; //reset counter to 0 so we count 10 more bounces
            
            if (scoreboard != null)
            {
                scoreboard.setLevel(currentSpeed - 1); 
            }
        }
        return currentSpeed;
    }
    
    public int getSpeed()
    {
        return currentSpeed;
    }
    
    public int getTotalBounces()
    {
        return totalBounces;
    }
    
    public boolean isGameRunning()
    {
        return gameIsRunning;
    }
    
    public void setGameRunning(boolean running)
    {
        this.gameIsRunning = running;
    }

}
