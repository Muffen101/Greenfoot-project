import greenfoot.*;


/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
 * 
 * @author The teachers 
 * @version 1
 */
public class Ball extends Actor
{
    private static final int BALL_SIZE = 25;
    private static final int BOUNCE_DEVIANCE_MAX = 5;
    private static final int STARTING_ANGLE_WIDTH = 90;
    private static final int DELAY_TIME = 100;

    private int speed;
    private boolean hasBouncedHorizontally;
    private boolean hasBouncedVertically;
    private int delay;
    private GameManager gameManager;

    /**
     * Contructs the ball and sets it in motion!
     */
    public Ball()
    {
        createImage();
        init();
    }
    
    public void setGameManager(GameManager manager)
    {
        this.gameManager = manager;
    }

    /**
     * Creates and sets an image of a black ball to this actor.
     */
    private void createImage()
    {
        GreenfootImage ballImage = new GreenfootImage("Spongeball.png");
        ballImage.scale(40,40);
        //ballImage.setColor(Color.BLACK);
        //ballImage.fillOval(0, 0, BALL_SIZE, BALL_SIZE);
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0) //wait out the delay timer before moving
        {
            delay--;
        }
        else
        {
            move(speed);
            checkBounceOffWalls();
            checkBounceOffCeiling();
            checkBounceOffPaddle(); //checking for collision with any paddle object
            checkRestart();
        }
    }    

    /**
     * Returns true if the ball is touching one of the side walls.
     */
    private boolean isTouchingSides()
    {
        return (getX() <= BALL_SIZE/2 || getX() >= getWorld().getWidth() - BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the ceiling.
     */
    private boolean isTouchingCeiling()
    {
        return (getY() <= BALL_SIZE/2);
    }

    /**
     * Returns true if the ball is touching the floor.
     */
    private boolean isTouchingFloor()
    { 
        return (getY() >= getWorld().getHeight() - BALL_SIZE/2);
    }
    
    private boolean shouldPassThrough()
    {
        return getRotation() <= 180;
    }
    
    /**
     * Checks for collision with any Paddle classes.
     * Then reverts in a vertical direction after being in contact, and handles bounce/pass through logic.
     * 
     * Task 3 - ball bounces off the self moving paddle from below and it passes through from the top.
     */
    private void checkBounceOffPaddle() 
    {
        if (isTouching(MiddlePaddle.class)) //checking MiddlePaddle first because MiddlePaddle extends Paddle
        {
            if (getRotation() > 180 && !hasBouncedVertically) //if getRotation() > 180 then the ball moves upwards. If rotation is <= 180 then its moving downwards and ball passes through
            {
                revertVertically(); 
            }
        }
        else if (isTouching(Paddle.class)) //checks the players paddle and that the ball always bounces off the players paddle no matter what direction the ball comes from
        {
            if (!hasBouncedVertically)
            {
                revertVertically();
            }
        }
        else
        {
            hasBouncedVertically = false;
        }
    }

    /**
     * Check to see if the ball should bounce off one of the walls.
     * If touching one of the walls, the ball is bouncing off.
     */
    private void checkBounceOffWalls()
    {
        if (isTouchingSides())
        {
            if (! hasBouncedHorizontally)
            {
                revertHorizontally();
            }
        }
        else
        {
            hasBouncedHorizontally = false;
        }
    }

    /**
     * Check to see if the ball should bounce off the ceiling.
     * If touching the ceiling the ball is bouncing off.
     */
    private void checkBounceOffCeiling()
    {
        if (isTouchingCeiling())
        {
            if (! hasBouncedVertically)
            {
                revertVertically();
            }
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            if(isTouchingFloor())
            {
                Greenfoot.setWorld(new GameOverWorld());
                GreenfootSound gameOverSound = new GreenfootSound("spongebob-fail.mp3");
                gameOverSound.setVolume(10);
                gameOverSound.play();
            }
            else
            {
                init();
                setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2); //FOR LATER WITH LIFES AND SUCH!!!!!!!!!!
            }
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((180 - getRotation()+ randomness + 360) % 360);
        hasBouncedHorizontally = true;
        
        GreenfootSound contactSound = new GreenfootSound("bo-womp.mp3");
        contactSound.setVolume(10);
        contactSound.play();
    }

    /**
     * Bounces the bal back from a horizontal surface.
     */
    private void revertVertically()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true;
        
        GreenfootSound contactSound = new GreenfootSound("bo-womp.mp3");
        contactSound.setVolume(10);
        contactSound.play();
    }

    /**
     * Initialize the ball settings.
     */
    private void init()
    {
        speed = 2;
        delay = DELAY_TIME;
        hasBouncedHorizontally = false;
        hasBouncedVertically = false;
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2);
    }
    
    /*private void gameOver()
    { 
        if(isTouchingFloor())       REMEMBER!
        {
            Greenfoot.stop();
        }
    }*/

}
