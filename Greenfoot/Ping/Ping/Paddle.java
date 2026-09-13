import greenfoot.*;

/**
 * A paddle is an object that goes back and forth. Though it would be nice if balls would bounce of it.
 * 
 * @author The teachers 
 * @version 1
 */
public class Paddle extends Actor
{
    protected int width; //change to protected?
    protected int height; //change to protected?
    protected int dx; //change to protected?

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

    private void move() //handles movement of the paddle
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
        GreenfootImage image = new GreenfootImage("woodplank.png");
        image.scale(100, 40); //determining the size of the image of the paddle
        setImage(image);
    }
}
