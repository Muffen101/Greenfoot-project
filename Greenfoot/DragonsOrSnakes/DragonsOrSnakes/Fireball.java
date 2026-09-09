import greenfoot.*;

import java.util.List;

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Actor
{
    private Dragon target;
    
    /**
     * Constructor for objects of class Fireball
     */
    public Fireball(Dragon dragonParam)
    {
        target = dragonParam;
    }
    
    public void act() {
        turnTowards(target.getX(), target.getY());
        move(4);
        
        List<Dragon> dragons = getIntersectingObjects(Dragon.class);
        if (dragons.size() > 0)
        {
            MyWorld world = (MyWorld) getWorld();
            
            GreenfootSound dieEffect = new GreenfootSound("An Enemy has been Slain (Nr. 1  Classic League of Legends Announcer) - Sound Effect for editing.mp3"); //Play sound effect of drying dragon
            dieEffect.setVolume(5);
            dieEffect.play();
            
            for (int i = dragons.size() - 1; i >= 0; i--)
            {
                world.dragonKilled(); //increase the score and spawn new dragon
                world.removeObject(dragons.get(i)); //removes the dead dragon
            }
            
            world.removeObject(this); //removes the fireball object once at the end
            return; //stops act
        }
        
        if (isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}
