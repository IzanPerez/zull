import java.util.ArrayList;
import java.util.Stack;
public class Player
{
    private Room currentRoom;
    private Stack<Room> pilaSalas;
    private ArrayList <Item> mochila;
    private double pesoMochila;
    private static final double MAXIMO_PESO = 7.5;
    public Player(Room sala)
    {
        currentRoom = sala;
        pilaSalas = new Stack<Room>();
        mochila = new ArrayList<Item>();
        pesoMochila = 0;
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
        if (objetoACoger != null && aptoCoger && pesoMochila +  objetoACoger.getPesoItem() < MAXIMO_PESO ){
            System.out.println("Has cogido el siguiente objeto: \n" );
            System.out.println(objetoACoger.getInfoItem());
            pesoMochila += objetoACoger.getPesoItem();
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

 public void drop(Command command) 
    {
         if(!command.hasSecondWord()) {
            // if there is no second word, we don't know the item to take...
            System.out.println("No has podido dejar el objeto, indica el id correcto");
            return;
        }        
        String itemID = command.getSecondWord();
        Item itemADejar= null;            
        int contador = 0;
        while (mochila.size() > contador && itemADejar == null  ){
            if (mochila.get(contador).getId().equals(itemID)){
                itemADejar = mochila.get(contador);
            }
            contador++;
        }

        if (mochila.size() > 0 && itemADejar != null){
            System.out.println("Has soltado el  objeto \n");
            System.out.println(itemADejar.getInfoItem());
            mochila.remove(itemADejar);
            currentRoom.addItem(itemADejar);
        }
        else{
            System.out.println("No tienes objetos para soltar");
        }
    }
    
    public void cuerda(Command command){
         if(!command.hasSecondWord()) {
            System.out.println("No has indicado el ID del objeto");
            return;
        }
        String segundaPalabraUsar = command.getSecondWord();
        Item esUnCuerda = currentRoom.getItem(segundaPalabraUsar);
        if (segundaPalabraUsar.equals("cod3")){
            System.out.println("Magnifico has conseguido salir gracias a una cuerdaHuida(Refencia Pokemon)");
        }
    }
}