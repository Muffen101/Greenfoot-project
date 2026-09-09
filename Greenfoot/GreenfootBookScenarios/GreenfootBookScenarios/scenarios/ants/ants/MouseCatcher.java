import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MouseCatcher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MouseCatcher extends Actor
{
    /**
     * Act - do whatever the MouseCatcher wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(getWorld())) 
        {
            int x = mouse.getX();
            int y = mouse.getY();        
            getWorld().addObject(new Food(), x, y);
            getWorld().showText("RTsdfsdfsdfsdf", 10 , 10);
        }
        
        
        
    }  
}
