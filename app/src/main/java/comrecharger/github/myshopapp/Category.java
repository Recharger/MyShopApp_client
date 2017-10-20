package comrecharger.github.myshopapp;

/**
 * Created by ygn on 19.10.17.
 */

public class Category {

    String name;
    int local_icon;
    int remote_icon;
    String description;


    Category(String _name, int _local_icon, int _remote_icon, String _description) {
        name = _name;
        local_icon = _local_icon;
        remote_icon = _remote_icon;
        description = _description;
    }
}
