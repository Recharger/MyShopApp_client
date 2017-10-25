package comrecharger.github.myshopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
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
            view = lInflater.inflate(R.layout.category_item, parent, false);
        }

        DatabaseHandler.ShopListItem c = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
//        ((TextView) view.findViewById(R.id.category_name)).setText(c.getName());
//        ((ImageView) view.findViewById(R.id.category_icon)).setImageResource(c.getLocalIcon());
        return view;
    }

    View.OnClickListener myCheckChangeList = new View.OnClickListener() {
        public void onClick(View v) {
            Toast toast = Toast.makeText(ctx,
                    "Пора покормить кота!", Toast.LENGTH_SHORT);
            toast.show();
        }
    };


    public void onItemClick(AdapterView<?> adapter , View v, int position){
        Toast toast = Toast.makeText(ctx, position, Toast.LENGTH_SHORT);
        toast.show();
//                Intent intent = new Intent(Activity.this, destinationActivity.class);
//                //based on item add info to intent
//                startActivity(intent);
    }


    // товар по позиции
    DatabaseHandler.ShopListItem getProduct(int position) {
        return ((DatabaseHandler.ShopListItem) getItem(position));
    }
}
