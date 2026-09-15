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
        setImage(ballImage);
    }

    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (delay > 0) //give the player a short pause before the ball starts moving
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
    
    private boolean shouldPassThrough() //checks to see if the ball is viable to pass through the paddle at the specific angle
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
            if (getRotation() > 180 && !hasBouncedVertically) //moving upward so bounce off the bottom of the middlepaddle
            {
                revertVertically(false); 
            }
        }
        else if (isTouching(Paddle.class)) //checks the players paddle and that the ball always bounces off the players paddle no matter what direction the ball comes from
        {
            if (!hasBouncedVertically) //bounce off player paddle and score points
            {
                revertVertically(true);
            }
        }
        else
        {
            hasBouncedVertically = false; //reset bounce lock once we leave the paddle
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
                revertVertically(false);
            }
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     * 
     */
    private void checkRestart()
    {
        if (isTouchingFloor())
        {
            GreenfootSound gameOverSound = new GreenfootSound("spongebob-fail.mp3");
            gameOverSound.setVolume(100);
            gameOverSound.play();
            
            Greenfoot.setWorld(new GameOverWorld());
            /*else
            {
                init();
                setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2); //FOR LATER WITH LIFES AND SUCH!!!!!!!!!!
            }*/ //remove this? unneccesary??
        }
    }

    /**
     * Bounces the ball back from a vertical surface.
     * Reverts horizontal direction (left/right wall hits).
     * Calculates the return angle and plays a bounce sound.
     */
    private void revertHorizontally()
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2; //picks a small random number between -2 and +2 degrees
        setRotation((180 - getRotation()+ randomness + 360) % 360); //mirror the rotation horizontal angle (180-current) and adds a small random variance so the ball doesn't get stuck in an endless angle loop
        hasBouncedHorizontally = true; //locks horizontal bounce until the ball exits the side edge
        
        GreenfootSound contactSound = new GreenfootSound("bo-womp.mp3");
        contactSound.setVolume(100); //adjusted the volume to the tester because it was too loud
        contactSound.play();
        
        /*PingWorld world = (PingWorld) getWorld();
        this.speed = world.ballBounced();*/
    }

    /**
     * Bounces the bal lback from a horizontal surface.
     */
    private void revertVertically(boolean countScore)
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true; //lock vertical bounce so collision isn't triggered twice on the same bounce
        
        GreenfootSound contactSound = new GreenfootSound("bo-womp.mp3");
        contactSound.setVolume(100); //adjusted the volume to the tester because it was too loud
        contactSound.play();
        
        if (countScore) //notifies the world to update the score and ball speed if hitting the players/main paddle
        {
            PingWorld world = (PingWorld) getWorld();
            this.speed = world.ballBounced(); //request updated ball speed from GameManager
        }
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
        setRotation(Greenfoot.getRandomNumber(STARTING_ANGLE_WIDTH)+STARTING_ANGLE_WIDTH/2); //launches the ball downwards at a random angled direction
    }

}
