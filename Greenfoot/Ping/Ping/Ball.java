import greenfoot.*;
/**
 * A Ball is a thing that bounces of walls and paddles (or at least i should).
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
    private Scoreboard scoreboard;

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
        if (manager != null)
        {
            this.speed = manager.getSpeed(); 
        }
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
            checkRestart(); //handles win, loss and life tracking transition to the specific worlds
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
    
    /**
     * Checks for collision with any type of Paddle classes.
     * Then reverts in a vertical direction after being in contact, and handles bounce/pass through logic.
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
        /**
         * Checks the players paddle and that the ball always bounces off the players paddle no matter what direction the ball comes from.
         * And adjusted the hitbox for the player paddle if the Y position of the ball is greater than 617.
         */
        else if (isTouching(Paddle.class) && (getY() > 617))
        {
            if (!hasBouncedVertically) //bounce off player paddle and score points
            {
                revertVertically(true);
            }
        }
        else if (isTouching(TopPaddle.class) && (getY() < 83)) //checks hitbox here as well just like it is done with paddle.class
        {
            if (!hasBouncedVertically)
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
            if (!hasBouncedVertically)
            {
                revertVertically(false); //only physical deflection, point tracking handled by checkRestart()
            }
        }
    }

    /**
     * Check to see if the ball should be restarted.
     * If touching the floor the ball is restarted in initial position and speed.
     * Also checks for life deduction and win/loss game.
     */
    private void checkRestart()
    {
        if (isTouchingFloor()) //if the ball passes the players paddle
        {
            PingWorld world = (PingWorld) getWorld();
            
            if (world != null)
            {
                Scoreboard scoreboard = world.getScoreboard();
                
                if (scoreboard != null)
                {
                    boolean stillAlive = scoreboard.loseLife(); //deduct 1 life from scoreboard
                    
                    if (stillAlive)
                    {
                        failSound();
                        init(); //resets the ball's location and parameters for the next attempt
                        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                    }
                    else
                    {
                        world.stopMusic(); //this stops the music before swapping worlds
                        failSound();
                        Greenfoot.setWorld(new GameOverWorld()); //else if you have no lives left, then switch to the GameOverWorld() screen
                    }
                }
            }
        }
        else if (isTouchingCeiling()) //if the ball is scored by the player
        {  
            scoreSound();
            PingWorld world =(PingWorld) getWorld();
            
            if (world != null && world.getScoreboard() != null)
            {
                boolean hasWon = world.getScoreboard().addPoint(); //add a point to scoreboard and checks if win conditions are met
                
                if (hasWon)
                {
                    world.stopMusic();
                    Greenfoot.setWorld(new WinWorld()); //changes to winWorld() screen if conditions are met
                    winSound();
                }
                else
                {
                    init(); //if none of these are met, then reset ball for next round
                    setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2); //if conditions are not met, then reset ball in center for the next round
                }
            }
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
        hitSound();
    }

    /**
     * Bounces the ball back from a horizontal surface.
     * Checks for the specific conditions of losing, winning or alternate ending, and resets ball in center for next round.
     */
    private void revertVertically(boolean countScore)
    {
        int randomness = Greenfoot.getRandomNumber(BOUNCE_DEVIANCE_MAX)- BOUNCE_DEVIANCE_MAX / 2;
        setRotation((360 - getRotation()+ randomness + 360) % 360);
        hasBouncedVertically = true; //lock vertical bounce so collision isn't triggered twice on the same bounce
        hitSound();
        
        if (countScore) //notifies the world to update the score and ball speed if hitting the players/main paddle
        {
            PingWorld world = (PingWorld) getWorld();
            this.speed = world.ballBounced(); //request updated ball speed from GameManager
            
            int MAX_SPEED = 13;
            if (this.speed >= MAX_SPEED) //if the ball gets too fast, it explodes
            {
                if (world != null)
                {
                    world.stopMusic();
                }
               Greenfoot.setWorld(new ExplodeWorld()); 
            }
        }
    }
    
    private void hitSound()
    {
        GreenfootSound contactSound = new GreenfootSound("bo-womp.mp3");
        contactSound.setVolume(10); //adjusted the volume to the tester because it was too loud
        contactSound.play();
    }
    
    /**
     * Plays sound effects upon losing a point/life or the game
     */
    private void failSound()
    {
        GreenfootSound gameOverVoice = new GreenfootSound("KrabsMoney.mp3");
        GreenfootSound gameOverSound = new GreenfootSound("spongebob-fail.mp3");
        gameOverVoice.setVolume(20);
        gameOverSound.setVolume(10);
        gameOverVoice.play();
        gameOverSound.play();
    }
    
    /**
     * Plays sound effect upon scoring a point against the opponent
     */
    private void scoreSound()
    {
        GreenfootSound pointSound = new GreenfootSound("kaching.mp3");
        pointSound.setVolume(15);
        pointSound.play();
    }
    
    /**
     * Plays sound effect upon winning the game and have scored the required amount to win
     */
    private void winSound()
    {
        GreenfootSound victorySound = new GreenfootSound("VictorySound.mp3");
        victorySound.setVolume(15);
        victorySound.play();
    }
    
    public Scoreboard getScoreboard()
    {
        return scoreboard;
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
