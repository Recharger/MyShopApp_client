package comrecharger.github.myshopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ygn on 31.10.17.
 */

public class RecentAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private List<DatabaseHandler.Product> objects;

    RecentAdapter(Context context, List<DatabaseHandler.Product> products) {
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

        DatabaseHandler.Product p = getProduct(position);

        ((TextView) view.findViewById(R.id.product_name)).setText(p.getName());
        ((TextView) view.findViewById(R.id.product_price)).setText(p.getPrice() + " BYN");
        ((ImageView) view.findViewById(R.id.product_ivImage)).setImageResource(p.getLocalIcon());
        view.setTag(p.getId());
        return view;
    }


    // товар по позиции
    DatabaseHandler.Product getProduct(int position) {
        return ((DatabaseHandler.Product) getItem(position));
    }
}
