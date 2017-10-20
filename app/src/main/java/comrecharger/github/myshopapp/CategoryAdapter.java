package comrecharger.github.myshopapp;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<DatabaseHandler.Category> categories;
    ListView categoryItem;

    CategoryAdapter(Context context, List<DatabaseHandler.Category> categoriesList) {
        ctx = context;
        categories = categoriesList;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return categories.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return categories.get(position);
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

        Category c = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.name)).setText(c.name);
        ((ImageView) view.findViewById(R.id.icon)).setImageResource(c.local_icon);
        return view;
    }

    OnClickListener myCheckChangeList = new OnClickListener() {
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
    Category getProduct(int position) {
        return ((Category) getItem(position));
    }
}