/**
 * Tracks bounce counts, updates the scoreboard and speeds up the ball every 5 bounces made on the players paddle.
 */
public class GameManager  
{
    private Scoreboard scoreboard;
    private boolean gameIsRunning = false;
    private int totalBounces = 0;
    private int bounceCounter = 0;
    private int currentSpeed = 2;
    private int countPoints = 0;

    /**
     * Constructor for objects of class GameManager
     */
    public GameManager(Scoreboard scoreboard)
    {
        this.scoreboard = scoreboard;
        this.gameIsRunning = true;
    }
    
    /**
     * Called when the ball hits the players paddle.
     * Increments score and updates ball speed every 5 bounces.
     */
    public int ballBounced()
    {
        if (!gameIsRunning) //don't modify scores if game is paused or ended
        {
            return currentSpeed;
        }
        totalBounces++; //adds to the main score
        bounceCounter++; //adds to the counted tracker towards the next level speed boost from 0 to 5
        
        if (scoreboard != null) //sends updated score to text display
        {
            scoreboard.setScore(totalBounces); 
        }
        
        if (bounceCounter == 5) //checks if player has completed the amount of bounces
        {
            currentSpeed++; //adds 1 in speed
            bounceCounter = 0; //reset counter to 0 so we count 5 more bounces
            
            if (scoreboard != null)
            {
                scoreboard.setLevel(currentSpeed - 1); //level 1 starts at speed 2, so level = speed - 1
            }
        }
        return currentSpeed; //return speed value back to Ball class
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
