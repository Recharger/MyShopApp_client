package comrecharger.github.myshopapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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


        mDatabaseHelper = new DatabaseHandler(getApplicationContext());
        //mDatabaseHelper.getWritableDatabase();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mDatabaseHelper.close();
    }


    private void onCategorySelect() {
        // создаем адаптер
        fillData();
        categoryAdapter = new CategoryAdapter(this, categories);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.category_list);
        lvMain.setAdapter(categoryAdapter);
    }

    void fillData() {
        for (int i = 1; i <= 20; i++) {
            categories.add(new Category("Category " + i, R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher, "Hello, guys"));
        }
    }

    private void onShopListSelect() {

    }


    private void onAboutSelect() {

    }


}

