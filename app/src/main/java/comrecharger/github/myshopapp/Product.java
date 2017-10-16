package comrecharger.github.myshopapp;

/**
 * Created by ygn on 16.10.17.
 */

public class Product {

    String name;
    int local_icon;
    int remote_icon;
    String description;
    Float price;
    Boolean is_availible;

    Product(String _name, int _local_icon, int _remote_icon, String _description, Float _price, boolean _is_availible) {
        name = _name;
        local_icon = _local_icon;
        remote_icon = _remote_icon;
        description = _description;
        price = _price;
        is_availible = _is_availible;
    }
}