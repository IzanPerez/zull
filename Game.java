import java.util.HashMap;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Item item;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }
    
    
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room caverna, oscura, pasadizo, trampa, salida,nido, iluminada , foso, cofre;

        // create the rooms
        caverna = new Room("estas en una caverna,en ella hay una antorcha que te ilumina un poco la sala", null);
        oscura = new Room("estas en una caverna totalmente a oscuras, todas las direcciones parece igual de buenas", null);
        pasadizo = new Room("atraviesa un pasadizo estreche, no ves nada solo rocas y pasadizos",null);
        trampa = new Room("caes en una trampa de pinchos que te atrviesan todo el cuerop.Has Muerto",null);
        salida = new Room("has encontrada la salida!",null);
        nido = new Room("te encuentras con un nido de murcielagos,parece que estan muy dormidos",null);
        iluminada = new Room("estas en una caverna en la que se aprecia algo de luz natural",null);
        foso = new Room("estas en una caverna en la que se aprecia un foso muy hondo",null);
        cofre = new Room("encuentras un cofre de madera que tiene una cerradura muy oxidada",null);
        // initialise room exits
        caverna.setExits( "north",oscura);
        caverna.setExits("east",pasadizo);
        oscura.setExits("north",nido);
        oscura.setExits( "south",caverna);
        pasadizo.setExits("north",iluminada);
        pasadizo.setExits("west",iluminada);
        pasadizo.setExits("southeast",foso);        
        nido.setExits("south" ,caverna);
        iluminada.setExits("north",salida);
        iluminada.setExits("east",trampa);
        iluminada.setExits("south",pasadizo);
        iluminada.setExits("northesast",cofre);
        
        currentRoom = caverna;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {  
            look();
        }
        else if (commandWord.equals("eat")) {   
            eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Estas perdido. Estas solo. Tienes");
        System.out.println("que conseguir salir de la caverna.");
        System.out.println();
        System.out.println("Los comandos habilitados para utilizar:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Donde quieres ir?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println();

    }

    private void look() {   
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void eat() {    
        System.out.println("Acabas de comer y ya no tienes hambre");
    }
}

