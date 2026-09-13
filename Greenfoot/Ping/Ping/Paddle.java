import greenfoot.*;


/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor
{
    private int width; //change to protected?
    private int height; //change to protected?
    private int dx; //change to protected?

    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 2;
        createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setLocation(getX() + dx, getY()); //moves paddle back and forth horizontally
        changeDirection();
    }    

    private void changeDirection()
    {
        if(getX() + width/2 >= getWorld().getWidth() || getX() - width/2 <= 0)
        {
            dx = dx * -1;
        }
    }
    
    private void respawnOnSide(int xPosition)
    {
        int minY = 50; //keeps the random Y height between 50 and 450
        int maxY = getWorld().getHeight() - 250;
        int randomY = minY + Greenfoot.getRandomNumber(maxY - minY);
        
        setLocation(xPosition, randomY);
    }

    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage(width, height);
        image.setColor(Color.BLACK);
        image.fill();
        setImage(image);
    }
}
