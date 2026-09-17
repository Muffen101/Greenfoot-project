import greenfoot.*;

/**
 * Main menu screen before starting the game.
 * This main menu screen starts off by showing an image before pressing play.
 */
public class IntroWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    /**
     * Constructor for objects of class IntroWorld.
     */
    public IntroWorld()
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        setBackground("ChumBucketStartCover.jpg");
    }
    
    /**
     * Updates the animated Gif and waits for the player's input to start the game by pressing enter
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
