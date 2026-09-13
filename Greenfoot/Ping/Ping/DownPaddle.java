import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DownPaddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DownPaddle extends Paddle
{
    public DownPaddle(int width, int height) //needs to be public in order to be called in PingWorld to be added to the game
    {
        super(width, height); //calls on the Paddle superclass constructor
        createImage(); //changes image to the picked image
    }
    /**
     * Act - do whatever the DownPaddle wants to do. This method is called whenever
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
            setLocation(getX()-4, getY());
        }
        if (Greenfoot.isKeyDown("Right") || Greenfoot.isKeyDown("D"))
        {
            setLocation(getX()+4, getY());
        }
    }
    private void createImage()
    {
        GreenfootImage image = new GreenfootImage("woodplank.png");
        image.scale(100, 40); //determining the size of the image of the paddle
        setImage(image);
    }
}
