import greenfoot.*;

/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 */
public class Paddle extends Actor
{
    protected int width; 
    protected int height; 
    protected int dx; 
    /**
     * Constructs a new paddle with the given dimensions.
     */
    public Paddle(int width, int height)
    {
        this.width = width;
        this.height = height;
        dx = 4;
        createImage();
    }

    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
    }    

    /**
     * Task 1 - use left and right keys/move left and right using keys.
     */
    private void move() //handles movement of the paddle, the method supports both arrow keys and A/D keys
    {
        if (Greenfoot.isKeyDown("Left") || Greenfoot.isKeyDown("A"))
        {
            setLocation(getX()- dx, getY());
        }
        if (Greenfoot.isKeyDown("Right") || Greenfoot.isKeyDown("D"))
        {
            setLocation(getX()+ dx, getY());
        }
    }
    
    /**
     * Creates and sets an image for the paddle, the image will have the same dimensions as the paddles width and height.
     */
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage("PLANKton.png");
        image.scale(150, 60); //determining the size of the image of the paddle
        setImage(image);
    }
}
