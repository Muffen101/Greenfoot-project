import greenfoot.*;  // imports Actor, World, Greenfoot, GreenfootImage

import java.util.Random;

/**
 * A world where wombats live.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class WombatWorld extends World
{
    /**
     * Create a new world with 8x8 cells and
     * with a cell size of 60x60 pixels
     */
    public WombatWorld() 
    {
        super(12, 12, 60);        
        setBackground("cell.jpg");
        setPaintOrder(Wombat.class, Leaf.class);
        
        populate();
        prepare();
        
        /**for(int i=0; i<10; i++) {
            System.out.println(Greenfoot.getRandomNumber(10));
        }**/
    }
    
    public void act()
    {
        if (Greenfoot.getRandomNumber(100) % 33 == 0)
        {
            randomLeaves(5);
            randomStones(1);
        }
    }
    /**
     * Populate the world with a fixed scenario of wombats and leaves.
     */    
    public void populate()
    {
        randomWombats(7);
        randomLeaves(45);
        randomStones(7);
        
        /*Wombat w1 = new Wombat();
        addObject(w1, 3, 3);

        Wombat w2 = new Wombat();
        addObject(w2, 1, 7);

        Leaf l1 = new Leaf();
        addObject(l1, 5, 3);

        Leaf l2 = new Leaf();
        addObject(l2, 0, 2);

        Leaf l3 = new Leaf();
        addObject(l3, 7, 5);

        Leaf l4 = new Leaf();
        addObject(l4, 2, 6);

        Leaf l5 = new Leaf();
        addObject(l5, 5, 0);

        Leaf l6 = new Leaf();
        addObject(l6, 4, 7);*/
    }
    
    public void randomWombats(int howMany)
    {
        for(int i=0; i<howMany; i++)
        {
            Wombat newWombat = new Wombat(false);
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            
            addObject(newWombat, x, y);
        }
    }
    

    /**
     * Place a number of leaves into the world at random places.
     * The number of leaves can be specified.
     */
    public void randomLeaves(int howMany)
    {
        for(int i=0; i<howMany; i++) {
            Leaf leaf = new Leaf();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            addObject(leaf, x, y);
        }
    }
    
    public void randomStones(int howMany)
    {
        for(int i=0; i<howMany; i++) {
            Stone stone = new Stone();
            int x = Greenfoot.getRandomNumber(getWidth());
            int y = Greenfoot.getRandomNumber(getHeight());
            
            addObject(stone, x, y);
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}