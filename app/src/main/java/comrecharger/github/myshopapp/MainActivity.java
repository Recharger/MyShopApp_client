package comrecharger.github.myshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "category_id";
    private TextView mTextMessage;
    private DatabaseHandler mDatabaseHelper;
    final String LOG_TAG = "myLogs";
    public static int current_category;
    private static ArrayList<DatabaseHandler.Product> recent = new ArrayList<DatabaseHandler.Product>();

    CategoryAdapter categoryAdapter;
    ShopListAdapter shopListAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_categories:
                    mTextMessage.setText("");
                    onCategorySelect();
                    return true;
                case R.id.navigation_shop_list:
                    onShopListSelect();
                    return true;
                case R.id.navigation_about:
                    mTextMessage.setText(R.string.title_about);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        onCategorySelect();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mDatabaseHelper.close();
    }


    private void onCategorySelect() {
        // создаем адаптер
        mDatabaseHelper = new DatabaseHandler(this);
        mDatabaseHelper.getReadableDatabase();
        mTextMessage.setText("");


        List<DatabaseHandler.Category> categories = mDatabaseHelper.getAllCategories(); //this is the method to query

        mDatabaseHelper.close();
        categoryAdapter = new CategoryAdapter(this, categories);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.category_list);
        lvMain.setAdapter(categoryAdapter);

        lvMain.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = "
                        + id);
                Intent intent = new Intent(getApplicationContext(), ProductListActivity.class);
                current_category = position;
                startActivity(intent);
            }

            ;
        });
    }

    void fillData(DatabaseHandler mDatabaseHelper) {


    }

    private void onShopListSelect() {
        mDatabaseHelper = new DatabaseHandler(this);
        mDatabaseHelper.getReadableDatabase();

        List<DatabaseHandler.ShopListItem> shopListItems = mDatabaseHelper.getShopListItems(); //this is the method to query

        Log.d(LOG_TAG, "Shop list filling: " + shopListItems);

        mDatabaseHelper.close();
        shopListAdapter = new ShopListAdapter(this, shopListItems);


        ListView lvMain = (ListView) findViewById(R.id.category_list);
        lvMain.setAdapter(shopListAdapter);

    }

    public static int getCurrentCategory() {
        return current_category;
    }

    public static void addRecentProduct(DatabaseHandler.Product product) {
        recent.add(product);
    }

    public static ArrayList<DatabaseHandler.Product> getRecentProducts() {
        return recent;
    }

    private void onAboutSelect() {

    }


}

