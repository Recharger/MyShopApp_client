package comrecharger.github.myshopapp;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Category> objects;
    ListView categoryItem;

    CategoryAdapter(Context context, ArrayList<Category> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
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
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.category_item, parent, false);
        }

        Category c = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.name)).setText(c.name);
        ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(c.local_image);

        categoryItem = view.findViewById(R.id.category_item);
        categoryItem.setOnClickListener(myCheckChangeList);


        return view;
    }

    OnClickListener myCheckChangeList = new OnClickListener() {
        public void onClick(View v) {
            Toast toast = Toast.makeText(ctx,
                    "Пора покормить кота!", Toast.LENGTH_SHORT);
            toast.show();
        }
    };


    // товар по позиции
    Category getProduct(int position) {
        return ((Category) getItem(position));
    }
}