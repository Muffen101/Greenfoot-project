import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Game over screen shown when the ball drops past the player paddle.
 * 
 * 
 */
public class GameOverWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage gameOverCover = new GreenfootImage("over.png");
        gameOverCover.scale (getWidth(), getHeight());
            
        gameOverCover.setColor(Color.RED);
        gameOverCover.setFont(new Font("Arial", true, false, 30));
        gameOverCover.drawString("GAME OVER!!!", WORLD_WIDTH / 2 - 100, WORLD_HEIGHT - 450);
        gameOverCover.drawString("Press Enter To Restart", WORLD_WIDTH / 2 - 150, WORLD_HEIGHT - 400);
        
        setBackground(gameOverCover);
        
        /*background.setColor(Color.BLACK);
        background.drawString("GAME OVER!!!", WORLD_WIDTH / 2 - 100, WORLD_HEIGHT / 2);*/
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
}
