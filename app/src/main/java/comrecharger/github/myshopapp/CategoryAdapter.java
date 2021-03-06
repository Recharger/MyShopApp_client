package comrecharger.github.myshopapp;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private List<DatabaseHandler.Category> categories;

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

        DatabaseHandler.Category c = getProduct(position);

        ((TextView) view.findViewById(R.id.category_name)).setText(c.getName());
        ((ImageView) view.findViewById(R.id.category_icon)).setImageResource(c.getLocalIcon());
        return view;
    }


    // товар по позиции
    DatabaseHandler.Category getProduct(int position) {
        return ((DatabaseHandler.Category) getItem(position));
    }
}