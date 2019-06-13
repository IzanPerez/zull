import java.util.HashMap;
import java.util.Set;
public class Item
{
    private double pesoItem;
    private String descripcionItem;
    private String id;
    private boolean sePuedeCojer;
    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcionItem, double peso , String idItem , boolean coger)
    {
        pesoItem = peso;
        this.descripcionItem = descripcionItem;
        id = idItem;
        sePuedeCojer = coger;
    }

    public String getInfoItem()
    {
        return descripcionItem + " tiene un peso de " + pesoItem + " Kg " + " ID: " + id;
    }

    public String getId(){
        return id;
    }
}
