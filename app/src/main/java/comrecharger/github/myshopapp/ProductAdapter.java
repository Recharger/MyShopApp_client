package comrecharger.github.myshopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ProductAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Product> objects;

    ProductAdapter(Context context, ArrayList<Product> products) {
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
        // используем созданные, но не используемые view
        Button btShopList;


        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.product_item, parent, false);
        }

        Product p = getProduct(position);

        ((TextView) view.findViewById(R.id.name)).setText(p.name);
        ((TextView) view.findViewById(R.id.price)).setText(p.price + "");
        ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.local_icon);

        btShopList = (Button) view.findViewById(R.id.shop_list__add);
        btShopList.setOnClickListener(onAddShopList);


        return view;
    }


    // товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    // обработчик для чекбоксов
    OnClickListener onAddShopList = new OnClickListener() {

        public void onClick(View v) {

        }
    };
}
