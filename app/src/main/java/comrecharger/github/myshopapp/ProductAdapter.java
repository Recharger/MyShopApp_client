package comrecharger.github.myshopapp;

import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private List<DatabaseHandler.Product> objects;
    private String LOG_TAG = "Product Handler";

    ProductAdapter(Context context, List<DatabaseHandler.Product> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.product_item, parent, false);
        }

        final DatabaseHandler.Product p = getProduct(position);

        ((TextView) view.findViewById(R.id.product_name)).setText(p.getName());
        ((TextView) view.findViewById(R.id.product_price)).setText(p.getPrice() + " BYN");
        ((ImageView) view.findViewById(R.id.product_ivImage)).setImageResource(p.getLocalIcon());
        view.setTag(p.getId());

        Button shop_list_add = view.findViewById(R.id.shop_list__add);
        shop_list_add.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Add to cart pressed: " + p.getId());
                try {
                    DatabaseHandler DBHandler = new DatabaseHandler(ctx);
                    DBHandler.getWritableDatabase();
                    DBHandler.addShopListItem(new DatabaseHandler.ShopListItem(
                            0,
                            p.getId(),
                            1
                    ));
                    Toast toast = Toast.makeText(ctx,
                            "Successfully added: " + p.getName(), Toast.LENGTH_LONG);
                    toast.show();
                } catch (Exception exception) {
                    Toast toast = Toast.makeText(ctx,
                            "Some error occurred when adding: " + exception.toString(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        return view;
    }


    // товар по позиции
    DatabaseHandler.Product getProduct(int position) {
        return ((DatabaseHandler.Product) getItem(position));
    }

}
