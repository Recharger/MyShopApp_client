package comrecharger.github.myshopapp;

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

    private TextView mTextMessage;
    private DatabaseHandler mDatabaseHelper;

    ArrayList<Category> categories = new ArrayList<Category>();
    CategoryAdapter categoryAdapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_categories:
                    mTextMessage.setText(R.string.title_categories);
                    onCategorySelect();
                    return true;
                case R.id.navigation_shop_list:
                    mTextMessage.setText(R.string.title_shop_list);
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



        List<DatabaseHandler.Category> categories = mDatabaseHelper.getAllCategories(); //this is the method to query

        mDatabaseHelper.close();
        categoryAdapter = new CategoryAdapter(this, categories);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + categories.toString(), Toast.LENGTH_SHORT);
        toast.show();
        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.category_list);
        lvMain.setAdapter(categoryAdapter);

        lvMain.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Item " + position, Toast.LENGTH_SHORT);
                toast.show();
            }

            ;
        });
    }

    void fillData(DatabaseHandler mDatabaseHelper) {


    }

    private void onShopListSelect() {

    }


    private void onAboutSelect() {

    }


}

