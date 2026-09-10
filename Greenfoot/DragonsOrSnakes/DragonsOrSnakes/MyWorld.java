import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class MyWorld extends World
{
    // Exercises:
    // 1) Write/copy code and make it work without errors and exceptions*
    // 2) Understand code and experiment with different loops*
        
    // Implement these coooool features:
    // 3) Wizard movement + shooting using the keyboard*
    // 4) Play sound when wizard shoots a fireball*
    // 5) Play sound when dragon dies*
    // 6) Spawn new dragons randomly*
    // 7) Scoreboard with amount of dragon kills*
    // 8) Wizard has health and can die...*
    // 9) Add a win condition (fx. 10 dragon kills wins)* 
    //10) Extend your dragon class so its constructor accepts a speed parameter etc.
    //11) Extend the game so a built-in timer increases the Dragon spawn-rate
    //12) Add a dragon boss class with a) increased speed and b) is able to kill the wizard by thouching it
    
    private Scoreboard scoreboard;
    private GameManager gameManager;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        scoreboard = new Scoreboard(); //creates the visual scoreboard actor
        addObject(scoreboard, 100, 30);
        
        gameManager = new GameManager(scoreboard); //initializes the GameManager for scoreboard
        /*for (int i = 0; i < 5; i++) {
            int newX = Greenfoot.getRandomNumber(250)+300;
            int newY = Greenfoot.getRandomNumber(350)+25;
            
            addObject(new Dragon(), newX, newY);
        }*/
        addObject(new Wizard(), 100, 200); //spawn a player
        spawnRandomDragons(5); //spawns dragons
        
    }
    
    public void gameWon()
    {
        removeObjects(getObjects(Dragon.class)); //clears the game from remaining dragons in the game when winning
        showText("YOU WIN!", getWidth() / 2, getHeight() / 2); //displays that youve won the game
        Greenfoot.stop(); //stops the game loop
    }
    
    /*public GameManager getGameManager()
    {
        return gameManager;
    }*/
    
    public void dragonKilled()
    {
        gameManager.onDragonKilled(); //updates the score through GameManager
        spawnRandomDragons(1); //spawning 1 random dragon as replacement
    }
    
    public void onWizardHealthChanged(int health)
    {
        gameManager.onHealthUpdated(health);
    }
    
    public void spawnRandomDragons(int numberToSpawn)
    {
        for (int i = 0; i < numberToSpawn; i++)
        {
            Dragon newDragon = new Dragon();
            int newX = Greenfoot.getRandomNumber((this.getWidth() / 2) - 50)+350; //so that the dragons spawn on the other half of the gameboard
            int newY = Greenfoot.getRandomNumber(this.getHeight()- 50)+ 25; //same here
            addObject(newDragon, newX, newY);
        }
    }
}
