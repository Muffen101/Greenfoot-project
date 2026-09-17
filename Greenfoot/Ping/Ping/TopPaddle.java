import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Computer controlled paddle positioned at the top ceiling.
 * This paddle tracks the ball's movement when moving towards it.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TopPaddle extends Paddle //update
{
    private static int paddleSpeed = 1;
    private Ball ball;
    
    public TopPaddle(int width, int height)
    {
        super(width, height);
        createImage();
    }
    
    public void act()
    {
        if (ball == null && getWorld() != null)
        {
            if (!getWorld().getObjects(Ball.class).isEmpty())
            {
                ball = (Ball) getWorld().getObjects(Ball.class).get(0);
            }
        }
        
        if (ball != null && getWorld() != null)
        {
            trackBall();
        }
    }
    
    private void trackBall()
    {
        boolean isBallComingUp = ball.getRotation() > 180; //checks to see if the ball is moving up toward the ceiling
        
        if (isBallComingUp)
        {
            int targetX = ball.getX();
            
            if (getX() < targetX - paddleSpeed) //slides left or right toward the ball's position on the X position
            {
                setLocation(getX() + paddleSpeed, getY());
            }
            else if (getX() > targetX + paddleSpeed)
            {
                setLocation(getX() - paddleSpeed, getY());
            }
        }
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage("SquidwardPlank.png");
        image.scale(150, 60); //determining the size of the image of the paddle
        setImage(image);
    }
}
