import java.util.ArrayList;
import java.util.Stack;
public class Player
{
    private Room currentRoom;
    private Stack<Room> pilaSalas;
    private ArrayList <Item> mochila;
    public Player(Room sala)
    {
        currentRoom = sala;
        pilaSalas = new Stack<Room>();
        mochila = new ArrayList<Item>();
    }

    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {

            System.out.println("donde quieres ir?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("No hay ninguna puerta!");
        }
        else {
            pilaSalas.push(currentRoom);
            // Try to leave current room.
            currentRoom = currentRoom.getExit(direction);
            look();
        }
    }

    public void back(){
        if(pilaSalas.empty()){
            System.out.println("No puedes volver atras, estas en la primera caverna");
        }else{
            currentRoom = pilaSalas.pop();
            look();
        }

    }

    public void look() {   
        System.out.println(currentRoom.getLongDescription());
    }

    public void eat() {    
        System.out.println("Acabas de comer y ya no tienes hambre");
    }

    public void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("ID incorrecto no vuelve a intentarlo");
            return;
        }
        String IDposidicon= command.getSecondWord();
        Item objetoACoger = currentRoom.getItem(IDposidicon);
        boolean aptoCoger = objetoACoger.getSePuedeCojer();
        if (objetoACoger != null && aptoCoger){
            System.out.println("Has cogido el siguiente objeto: \n" );
            System.out.println(objetoACoger.getInfoItem());
            
            mochila.add(objetoACoger);
            currentRoom.removeItem(objetoACoger);
        }else{
            System.out.println("No puedes coger el item");
        }
    }
    
    public void mochilaItems() 
    {
        if (mochila.size() > 0){
            System.out.println("Tu mochila contiene estos items");
            for (int x = 0; x < mochila.size(); x++){
                System.out.println(mochila.get(x).getInfoItem());
                
            }
        }
        else{
            System.out.println("Tu mochila no contiene nada");
        }
}
}