



import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Item item;
    private HashMap<String, Room> exits;
    private HashMap<String, Item> roomItem;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, Item item) 
    {
        this.description = description;
        this.item = item;
        exits = new HashMap<>();
        roomItem = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direccion, Room nextRoom ) 
    {
       exits.put(direccion,nextRoom);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    public Room getExit (String direccion){
        
        return exits.get(direccion);
    }
    public String getExitString(){
       Set<String> nombreDireciones = exits.keySet();
       String exitDescription = "Salida ";
       for(String direccion : nombreDireciones){
           exitDescription += direccion + " ";
       }
       return exitDescription;
    }
    
    public String getLongDescription(){
        
        return "Tu estas" + description + " " + getLongDescriptionItem() +".\n" +getExitString();
    }
    
    public Item getRoomItemns (String room){
        return roomItem.get(room);
    }
    public String getItemsString(){
       Set<String> nombreHabitaciones = roomItem.keySet();
       String exitDescription = " ";
       for(String item : nombreHabitaciones){
           exitDescription += item + " ";
       }
       return exitDescription;
    }
    
    public void setRoomItems(String room, Item item ) 
    {
        roomItem.put(room,item);
    }
    
    public String getLongDescriptionItem(){
        
        return "Tu encuentras un" + getItemsString() + ".\n tiene un pedo de "  + " Kg" ;
    }
}
