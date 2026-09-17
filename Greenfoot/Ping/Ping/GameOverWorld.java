import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game over screen shown when the ball drops past the player paddle.
 * This runs when the player has lost the game by meeting the losing requirements.
 */
public class GameOverWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    /**
     * The game over screen shown when the player loses the game
     */
    public GameOverWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage gameOverCover = new GreenfootImage("MrSpangsberg.jpg");
        gameOverCover.scale (getWidth(), getHeight());
        setBackground(gameOverCover);
    }
    
    /**
     * Player can press enter to come back to PingWorld which lets you retry or restart the game
     */
    public void act() 
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
}
