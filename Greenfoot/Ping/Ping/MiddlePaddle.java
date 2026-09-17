import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MiddlePaddle here.
 * 
 * Added a self moving paddle that enters the world at a random position and moves horizontally. The paddle does respawn at a random height when removed.
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
        setLocation(getX() + dx, getY()); //this method makes the paddle automatically move right
        checkPaddleTouchWall();
    }
    
    private void checkPaddleTouchWall() //once reached the right edge of the map, remove this one and spawn a new one back on the left side
    {
        if (getX() + width / 2 >= getWorld().getWidth())
        {
            PingWorld world = (PingWorld) getWorld();
            world.removeObject(this); //removing the instance from the world
            
            //randomizer for width of the self moving paddle, which gives the game different sizes of moving paddles where the width = 100 is a minimum width
            int randomWidth = 100 + Greenfoot.getRandomNumber(70);
            world.addObject(new MiddlePaddle(randomWidth, 70), 0, Greenfoot.getRandomNumber(200) + 100); //spawns the new paddle at x=0 and random Y position
        }
    }
    
    /**
     * Helper method to recalculate a safe random vertical spawn coordinate.
     */
    private void respawnOnSide(int xPosition) //
    {
        int minY = 50; //avoids spawning too close to the top ceiling
        int maxY = getWorld().getHeight() - 250; //keeps distance from the main players paddle
        
        int randomY = minY + Greenfoot.getRandomNumber(maxY - minY); //generate a random position inside the bounds
        
        setLocation(xPosition, randomY); 
    }
    
    private void createDefaultImage()
    {
        GreenfootImage image = new GreenfootImage("MoneyBill.png");
        image.scale(width, height); //check gap between ball and hitbox later!!!!!!!!!
        //image.setColor(Color.BLACK);
        ///image.fill();
        setImage(image);
    }
}
