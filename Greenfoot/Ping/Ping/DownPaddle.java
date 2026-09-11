import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DownPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DownPaddle extends Paddle
{
    private int width;
    private int height;
    private int dx;
    
    /**
     * Act - do whatever the DownPaddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
    }
    
    public DownPaddle(int width, int height)
    {
        super(width, height);
        this.width = width;
        this.height = height;
        dx = 1;
        createImage();
    }
    
    private void tryChangeDirection()
    {
        //Check to see if we are touching the outer boundaries of the world:
        // IF we are touching the right boundary OR we are touching the left boundary:
        if(getX() + width/2 >= getWorld().getWidth() || getX() - width/2 <= 0)
        {
            //Change our 'x' direction to the inverted direction:
            dx = dx * -1;
        }
    }
    
    private void move()
    {
        if (Greenfoot.isKeyDown("Left"))
        {
            setLocation(getX()-5, getY());
        }
        if (Greenfoot.isKeyDown("Right"))
        {
            setLocation(getX()+5, getY());
        }
    }
    
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage("woodplank.png");
        image.scale(100, 20);
        //image.fill();
        setImage(image);
    }
}
