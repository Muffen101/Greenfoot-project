import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiddlePaddle here.
 * 
 * Task 2 - added a self moving paddle that enters the world at a random position and moves horizontally. The paddle does respawn at a random height when removed.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiddlePaddle extends Paddle
{
    public MiddlePaddle(int width, int height) //needs to be public in order to be called in PingWorld to be added to the game
    {
        super(width, height); //calls on the Paddle superclass constructor
        this.dx = 1;
        createDefaultImage(); //changes image to the picked image
    }
    
    /**
     * Act - do whatever the DownPaddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX() + dx, getY());
        checkPaddleTouchWall();
    }
    
    private void checkPaddleTouchWall()
    {
        if (getX() + width / 2 >= getWorld().getWidth())
        {
            PingWorld world = (PingWorld) getWorld();
            world.removeObject(this);
            world.addObject(new MiddlePaddle(100,20), 0, Greenfoot.getRandomNumber(200) + 100);
        }
    }
    
    private void respawnOnSide(int xPosition)
    {
        int minY = 50;
        int maxY = getWorld().getHeight() - 250; //check on this later!!!!!!!!!!
        int randomY = minY + Greenfoot.getRandomNumber(maxY - minY);
        
        setLocation(xPosition, randomY);
    }
    
    private void createDefaultImage()
    {
        GreenfootImage image = new GreenfootImage("MoneyBill.png");
        image.scale(130, 70); //check gap between ball and hitbox later!!!!!!!!!
        //image.setColor(Color.BLACK);
        ///image.fill();
        setImage(image);
    }
}
