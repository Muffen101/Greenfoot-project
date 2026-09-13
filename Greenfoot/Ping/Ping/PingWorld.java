import greenfoot.*;


/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * 
 * @author The teachers 
 * @version 1
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;

    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        if (gameStarted)
        {
            GreenfootImage background = getBackground();
            background.setColor(Color.BLACK);
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(new Ball(), WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new DownPaddle(100,20), 60, WORLD_HEIGHT - 50);
            
            int randomX = Greenfoot.getRandomNumber(WORLD_WIDTH);
            int randomY = 50 + Greenfoot.getRandomNumber(400);
            addObject(new Paddle(80,15), randomX, randomY); //adds self moving paddle at a random height between Y = 50 and 450
            
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld()); //if not started, go back to main screen
        }
    }

}
