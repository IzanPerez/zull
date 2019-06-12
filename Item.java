import java.util.HashMap;
import java.util.Set;
public class Item
{
    private double pesoItem;
    private String descripcionItem;
    
    /**
     * Constructor for objects of class Item
     */
    public Item(String descripcionItem, double peso)
    {
        pesoItem = peso;
        this.descripcionItem = descripcionItem;
        
    }

    public double getPesoItem(){
        return pesoItem;
    }
    public String getInfoItem(){
        String info = "Item: " + descripcionItem + " tiene un peso de: " + pesoItem;
        return info; 
    }
    public String getDescripcionItem(){
        return descripcionItem;
    }

    

    
    
     
}
