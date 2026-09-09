import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

import java.util.List;
import java.util.ArrayList;

/**
 * Wombat. A Wombat moves forward until it can't do so anymore, at
 * which point it turns left. If a wombat finds a leaf, it eats it.
 * 
 * @author Michael Kolling
 * @version 1.0.1
 */
public class Wombat extends Actor
{
    private static final int EAST = 0;
    private static final int WEST = 1;
    private static final int NORTH = 2;
    private static final int SOUTH = 3;

    private int direction;
    private int leavesEaten; //instance variable
    private int stepsTaken;
    private boolean isChild = false;
    private int pregnancy = -1;
    private int age = 0;

    public Wombat(boolean isChildParam) //constructor
    {
        leavesEaten = 0;
        stepsTaken = 0;
        int randomDirection = Greenfoot.getRandomNumber(4); //randomly faces a direction at start
        setDirection(randomDirection);
        this.isChild = isChildParam;
        
        if (isChild)
        {
            GreenfootImage image = getImage();
            image.scale(25,25);
            setImage(image);
        }
    }

    /**
     * Do whatever the wombat likes to to just now.
     */
    public void act()
    {
        if(isChild)
            age++;
        
        if(age == 10)
        {
            isChild = false;
            setImage(new GreenfootImage("wombat.png"));
        }
        
        if(foundLeaf()) 
        {
            eatLeaf();
        }
        else if (foundStone() && leavesEaten >= 5) 
        {
            eatStone();
        }
        else if(canMove()) 
        {
            turnTowardsLeaf();
            if (canMove()) 
            {
                move();
                handleStepsTaken();
            }
            else 
            {
                setDirection(Greenfoot.getRandomNumber(4));
            }
        }
        else
        {
            while (!canMove())
            {
                setDirection(Greenfoot.getRandomNumber(4));
            }
            move();
            handleStepsTaken(); //tæller også steps fra muren
        }
        if (foundWombat())
        {
            pregnancy = 0;
        }
    }
    
    /**
     * Handle steps taken
     */
    private void handleStepsTaken() //method signature
    {
            //stepsTaken = stepsTaken + 1; longer ver.
            if (stepsTaken >= 50) 
            {
                if(isChild && pregnancy != -1 && pregnancy < 9)
                pregnancy++;
                else if (pregnancy == 9)
                {
                    Wombat child = new Wombat(true);
                    getWorld().addObject(child, getX(), getY());
                    pregnancy = -1;
                }
                stepsTaken++; //shorthand notation
                
                //remove wombat
                getWorld().removeObject(this);
            
            }
            
    }
    
    public boolean foundWombat() 
    {
        Wombat wombat = (Wombat) getOneObjectAtOffset(0, 0, Wombat.class);
        
        if(wombat != null && !wombat.isChild())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Check whether there is a leaf in the same cell as we are.
     */
    public boolean foundLeaf()
    {
        Actor leaf = getOneObjectAtOffset(0, 0, Leaf.class);
        if(leaf != null) 
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    /**
     * Eat a leaf.
     */
    public void eatLeaf()
    {
        Actor leaf = getOneObjectAtOffset(0, 0, Leaf.class);
        if(leaf != null) {
            // eat the leaf...
            getWorld().removeObject(leaf);
            leavesEaten = leavesEaten + 1;
            stepsTaken -= 25;
            if (stepsTaken < 0) 
            {
                stepsTaken = 0;
            }
        }
    }
    
    public boolean foundStone()
    {
        Actor stone = getOneObjectAtOffset(0, 0, Stone.class);
        return stone != null;
    }
    
    public void eatStone()
    {
        Actor stone = getOneObjectAtOffset(0, 0, Stone.class);
        if (stone !=null)
        {
            if (leavesEaten >=5) 
            {
                getWorld().removeObject(stone);
                leavesEaten -= 5;
            }
        }
    }
    /**
     * Move one cell forward in the current direction.
     */
    public void move()

    {
        if (!canMove()) {
            return;
        }
        switch(direction) {
            case SOUTH :
                setLocation(getX(), getY() + 1);
                break;
            case EAST :
                setLocation(getX() + 1, getY());
                break;
            case NORTH :
                setLocation(getX(), getY() - 1);
                break;
            case WEST :
                setLocation(getX() - 1, getY());
                break;
        }
    }

    /**
     * Test if we can move forward. Return true if we can, false otherwise.
     */
    public boolean canMove()
    {
        World myWorld = getWorld();
        if (myWorld == null) return false; //hjælper mod fejl hvis den fjernes midt game
        
        int x = getX();
        int y = getY();
        switch(direction) {
            case SOUTH : //Ned
                y++;
                break;
            case EAST : //Højre
                x++;
                break;
            case NORTH : //Op
                y--;
                break;
            case WEST : //Venstre
                x--;
                break;
        }
        // test for outside border
        if (x >= myWorld.getWidth() || y >= myWorld.getHeight()) {
            return false;
        }
        else if (x < 0 || y < 0) {
            return false;
        }
        return true;
    }
    
    public void turnTowardsLeaf()
    {
        List<Leaf> leaves = getWorld().getObjects(Leaf.class); //henter liste over alle leaf i spillet
        
        if (!leaves.isEmpty())
        {
            Leaf targetLeaf = leaves.get(0); //tager nærmeste leaf
            int targetX = targetLeaf.getX();
            int targetY = targetLeaf.getY();
            
            if (targetX > getX()) //ændre wombattens placering og finder det nærmeste leaf
            {
                setDirection(EAST);
            }
            else if (targetX < getX())
            {
                setDirection(WEST);
            }
            else if (targetY > getY())
            {
                setDirection(SOUTH);
            }
            else if (targetY < getY())
            {
                setDirection(NORTH);
            }
        }
    }

    /**
     * Turns towards the left.
     */
    public void turnLeft()
    {
        switch(direction) {
            case SOUTH :
                setDirection(EAST);
                break;
            case EAST :
                setDirection(NORTH);
                break;
            case NORTH :
                setDirection(WEST);
                break;
            case WEST :
                setDirection(SOUTH);
                break;
        }
    }

    /**
     * Sets the direction we're facing. The 'direction' parameter must
     * be in the  range [0..3].
     */
    public void setDirection(int direction)
    {
        if ((direction >= 0) && (direction <= 3)) {
            this.direction = direction;
        }
        switch(direction) {
            case SOUTH :
                setRotation(90);
                break;
            case EAST :
                setRotation(0);
                break;
            case NORTH :
                setRotation(270);
                break;
            case WEST :
                setRotation(180);
                break;
            default :
                break;
        }
    }

    /**
     * Tell how many leaves we have eaten.
     */
    public int getLeavesEaten()
    {
        return leavesEaten;
    }
    
    public boolean isChild()
    {
        return isChild;
    }
}