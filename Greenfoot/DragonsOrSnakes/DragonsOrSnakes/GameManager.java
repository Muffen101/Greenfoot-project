import greenfoot.*;
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
    private static int WINNING_SCORE = 10;
    
    public GameManager(Scoreboard scoreboard) //constructor for the scoreboard to get from MyWorld
    {
        this.scoreboard = scoreboard;
        this.gameIsRunning = true;
    }
    
    public void onDragonKilled()
    {
        if (scoreboard != null)
        {
            scoreboard.addPoint(); //updates the scoreboard counter
            
            if (scoreboard.getScore() >= WINNING_SCORE) //checking if the score has reached 10, if yes then win
            {
                triggerWin();
            }
        }
    }
    
    private void triggerWin()
    {
        MyWorld world = scoreboard.getWorld() != null ? (MyWorld) scoreboard.getWorld() : null; //stops the game and informs MyWorld
        if (world != null)
        {
            world.gameWon();
        }
    }
    
    public void onHealthUpdated(int currentHealth)
    {
        if (scoreboard != null)
        {
            scoreboard.setHealth(currentHealth);
        }
    }
    
    public boolean isGameRunning() //checks the value if game is running and returns
    {
        return gameIsRunning;
    }
    
    public void setGameRunning(boolean running)
    {
        this.gameIsRunning = running;
    }
    
}
