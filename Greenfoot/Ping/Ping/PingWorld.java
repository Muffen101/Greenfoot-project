import greenfoot.*;
/**
 * The Ping World is where Balls and Paddles meet to play pong.
 * Main game world setup for objects, manager and scoreboard.
 */
public class PingWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    private Scoreboard scoreboard;
    private GameManager gameManager;
    private GreenfootSound BGM = new GreenfootSound("The Krusty Krab Theme.mp3"); //adding BGM (background music) to the game
    /**
     * Constructor for objects of class PingWorld.
     */
    public PingWorld(boolean gameStarted)
    {
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); 
        GreenfootImage bg = new GreenfootImage("ChumbBucketGameBG.png");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);
        BGM.setVolume(10);
        
        if (gameStarted)
        {
            scoreboard = new Scoreboard(); //adds scoreboard top left
            addObject(scoreboard, 150, 30);
            
            gameManager = new GameManager(scoreboard); //initializes GameManager with scoreboard
            
            Ball ball = new Ball();
            ball.setGameManager(gameManager);
    
            // Create a new world with WORLD_WIDTHxWORLD_HEIGHT cells with a cell size of 1x1 pixels.
            addObject(ball, WORLD_WIDTH/2, WORLD_HEIGHT/2);
            addObject(new Paddle(100,20), 60, WORLD_HEIGHT - 50);
            
            //spawn middle self moving paddle at a random Y position between 50 and 450
            int randomX = Greenfoot.getRandomNumber(WORLD_WIDTH);
            int randomY = 50 + Greenfoot.getRandomNumber(400);
            addObject(new MiddlePaddle(80,15), randomX, randomY); //adds self moving paddle at a random height between Y = 50 and 450
            
            addObject(new TopPaddle(100, 20), 60, WORLD_HEIGHT - 650);
            
            if (BGM != null && !BGM.isPlaying())
            {
                BGM.playLoop();
            }
        }
        else
        {
            Greenfoot.setWorld(new IntroWorld()); //if not started, go back to main screen
        }
    }
    
    public void stopped()
    {
        if (BGM != null && BGM.isPlaying())
        {
            BGM.stop();
        }
    }
    
    public void stopMusic()
    {
        if (BGM != null)
        {
            BGM.stop();
        }
    }
    
    public int ballBounced()
    {
        if (gameManager != null)
        {
            return gameManager.ballBounced();
        }
        
        return 2; //returning default speed to 2
    }
    
    public Scoreboard getScoreboard()
    {
        return scoreboard;
    }
    
    public GameManager getGameManager()
    {
        return gameManager;
    }
}
