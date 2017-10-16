package comrecharger.github.myshopapp;

/**
 * Created by ygn on 16.10.17.
 */

public class Category {

    public String name;
    public int local_image;
    public int remote_image;
    public String description;


    Category(String _name, int _local_image, int _remote_image, String _description) {
        name = _name;
        local_image = _local_image;
        remote_image = _remote_image;
        description = _description;
    }
}