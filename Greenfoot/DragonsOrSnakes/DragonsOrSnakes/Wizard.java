import greenfoot.*;

import java.util.*;

/**
 * Write a description of class Wizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wizard extends Actor
{
    private static final int MAX_COOLDOWN = 50;
    private int cooldown = MAX_COOLDOWN;
    
    
    public void act() 
    {
            cooldown--;  
       
            move(); //handling the users movement input
            shoot(); //handles shooting
        
    }   
    
    private void move(){
        if (Greenfoot.isKeyDown("W")) {
            setLocation(getX(), getY() - 4);
        }
        if (Greenfoot.isKeyDown("S")) {
            setLocation(getX(), getY() + 4);
        }
        if (Greenfoot.isKeyDown("D")) {
            setLocation(getX() + 4, getY());
        }   
        if (Greenfoot.isKeyDown("A")) {
            setLocation(getX() - 4, getY());
        }
    }
    
    private void shoot()
    {
        Dragon d = getNearestDragon();
        if (Greenfoot.isKeyDown("Space")) 
        {
            if (d != null && cooldown <= 0)
            {
            GreenfootSound shootEffect = new GreenfootSound ("D&D Magic Fireball Sound Effect.mp3");
            shootEffect.setVolume(5);
            shootEffect.play();
            Fireball f = new Fireball(d);
        
            getWorld().addObject(f,this.getX(), this.getY());
            cooldown = MAX_COOLDOWN;
            }
        }
    }
    
    private Dragon getNearestDragon() {
        List<Dragon> dragons = getWorld().getObjects(Dragon.class);
        Dragon nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        
        // enhanced for loop
        for (Dragon d: dragons) {
            
            double currentDistance = distance(d);
            if (currentDistance < nearestDistance) {
                nearestDistance = currentDistance;
                nearest = d;
            }
        }
        
        return nearest;
    }
    
    private double distance(Actor a) {
        return Math.sqrt(
            Math.abs((getX()-a.getX())^2) +
            Math.abs((getY()-a.getY())^2));
    }
    
    
}
