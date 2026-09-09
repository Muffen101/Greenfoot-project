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
    
    public GameManager(Scoreboard scoreboard)
    {
        this.scoreboard = scoreboard;
        this.gameIsRunning = true;
    }
    
    public void onDragonKilled()
    {
        if (scoreboard != null)
        {
            scoreboard.addPoint(); //updates the scoreboard counter
        }
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
