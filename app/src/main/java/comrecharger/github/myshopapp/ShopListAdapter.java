package comrecharger.github.myshopapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ygn on 25.10.17.
 */

public class ShopListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<DatabaseHandler.ShopListItem> shopList;
    ListView shopListItem;

    private String LOG_TAG = "Shop list adapter";

    ShopListAdapter(Context context, List<DatabaseHandler.ShopListItem> shopListInput) {
        ctx = context;
        shopList = shopListInput;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return shopList.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return shopList.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.shop_list_item, parent, false);
        }

        final DatabaseHandler.ShopListItem c = getProduct(position);
        DatabaseHandler DB = new DatabaseHandler(ctx);
        DB.getReadableDatabase();
        DB.close();
        Log.d(LOG_TAG, "List item product id: " + c.getProductId());
        DatabaseHandler.Product p = DB.getProductById(c.getProductId());
        Log.d(LOG_TAG, "List item product name: " + p.getName());


        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.product_name)).setText(p.getName());
        ((TextView) view.findViewById(R.id.product_price)).setText(p.getPrice() + " BYN");
        ((ImageView) view.findViewById(R.id.product_ivImage)).setImageResource(p.getLocalIcon());
        ((TextView) view.findViewById(R.id.number_of_item)).setText(c.getNumberOfItems() + "");

        Button increment = view.findViewById(R.id.shop_list__increment);
        Button decrement = view.findViewById(R.id.shop_list__decrement);
        final Button remove = view.findViewById(R.id.shop_list__remove);

        final View finalView = view;
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Increment");
                DatabaseHandler DB = new DatabaseHandler(ctx);
                DB.getWritableDatabase();
                DB.updateShopListItem(new DatabaseHandler.ShopListItem(
                        c.getId(),
                        c.getProductId(),
                        c.getNumberOfItems() + 1
                ));
                c.setNumberOfItems(c.getNumberOfItems() + 1);
                ((TextView) finalView.findViewById(R.id.number_of_item)).setText(c.getNumberOfItems() + "");
                DB.close();

            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Decrement");
                if (c.getNumberOfItems() == 1) {
                    finalView = removeItem(c, finalView);
                    return;
                }
                DatabaseHandler DB = new DatabaseHandler(ctx);
                DB.getWritableDatabase();
                DB.updateShopListItem(new DatabaseHandler.ShopListItem(
                        c.getId(),
                        c.getProductId(),
                        c.getNumberOfItems() - 1
                ));
                c.setNumberOfItems(c.getNumberOfItems() - 1);
                ((TextView) finalView.findViewById(R.id.number_of_item)).setText(c.getNumberOfItems() + "");
                DB.close();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(c, finalView);
            }
        });

        return finalView;
    }


    private View removeItem(DatabaseHandler.ShopListItem c, View v) {
        try {
            Log.d(LOG_TAG, "Remove");
            DatabaseHandler DB = new DatabaseHandler(ctx);
            DB.getWritableDatabase();
            DB.deleteShopListItem(new DatabaseHandler.ShopListItem(
                    c.getId(),
                    c.getProductId(),
                    c.getNumberOfItems()
            ));
            DB.close();
            v.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            Toast toast = Toast.makeText(ctx,
                    "Some error occurred when remove: " + e.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    };



    // товар по позиции
    DatabaseHandler.ShopListItem getProduct(int position) {
        return ((DatabaseHandler.ShopListItem) getItem(position));
    }
}
