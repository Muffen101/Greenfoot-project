import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the alternate or secret ending to lose the game where both the player and the bot lose.
 */
public class ExplodeWorld extends World
{
    private static final int WORLD_WIDTH = 500;
    private static final int WORLD_HEIGHT = 700;
    
    /**
     * Constructor for objects of class ExplodeWorld.
     */
    public ExplodeWorld()
    {    
        super(WORLD_WIDTH, WORLD_HEIGHT, 1);
        GreenfootImage boom = new GreenfootImage("Exploding.png");
        boom.scale(getWidth(), getHeight());
        setBackground(boom);
        playExplosionSound();
    }
    
    public void act()
    {
        showText("BOOM! BALL WENT TOO FAST!", WORLD_WIDTH / 2, 140);
        showText("PRESS ENTER TO PLAY AGAIN!", WORLD_WIDTH / 2, 100);
        
        String key = Greenfoot.getKey();
        if (key != null && key.equals("enter"))
        {
            Greenfoot.setWorld(new PingWorld(true));
        }
    }
    
    private void playExplosionSound()
    {
        GreenfootSound boom = new GreenfootSound("ExplosionSound.mp3");
        boom.setVolume(20);
        boom.play();
    }
}
