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
    private int health = 100; 
    
    public void act() 
    {
            cooldown--;  
       
            move(); //handling the users movement input
            shoot(); //handles shooting
            checkDragonCollision(); //checks continously if theres contact with dragon
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
    
    private void checkDragonCollision()
    {
        Dragon dragon = (Dragon) getOneIntersectingObject(Dragon.class);
        
        if (dragon != null && dragon.getWorld() != null) //checking and makes sure dragon exists and currently in game
        {
            takeDamage(25); //dragon does 25HP per hit
            getWorld().removeObject(dragon);
        }
    }
    
    private void takeDamage(int amount)
    {
        health -= amount;
        
        MyWorld world = (MyWorld) getWorld(); //telling MyWorld to update the UI
        if (world != null)
        {
            world.onWizardHealthChanged(health);
        }
        
        if(health <= 0)
        {
            die();
        }
    }
    
    private void die()
    {
        MyWorld world = (MyWorld) getWorld();
        if (world != null)
        {
            getWorld().showText("GAME OVER!", getWorld().getWidth() / 2, getWorld().getHeight() / 2); //displays the game over message to the player
            getWorld().removeObject(this); //removes the player from the game
        }
    }
    
    public int getHealth()
    {
        return this.health;
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
