import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The world screen of when the winning conditions are met, then run this screen.
 */
public class WinWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    public WinWorld()
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        GreenfootImage gameWinCover = new GreenfootImage("SweetVictory.jpg");
        gameWinCover.scale (getWidth(), getHeight());
        setBackground(gameWinCover);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
}
