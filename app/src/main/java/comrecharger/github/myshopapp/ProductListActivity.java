package comrecharger.github.myshopapp;

import android.app.Fragment;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private DatabaseHandler mDatabaseHelper;
    private String LOG_TAG = "Product list";
    private Intent intent;
    private static List<DatabaseHandler.Product> products;
    private TextView mTextMessage;
    private ProductAdapter productAdapter;
    private RecentAdapter recentAdapter;
    private int category_id;
    private ProductFragment fragment;
    private FragmentTransaction fTrans;
    private Context ctx;
    private FragmentTransaction fragmentTransaction;
    private ArrayList<DatabaseHandler.Product> recent;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_categories:
                    onProductSelect();
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
        setContentView(R.layout.activity_product_list);
        ctx = getApplicationContext();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        onProductSelect();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onProductSelect();
        findViewById(R.id.recent_products).setVisibility(View.VISIBLE);
        findViewById(R.id.recent_textview).setVisibility(View.VISIBLE);
        Log.d(LOG_TAG, "Product_list_activity: onResume()");
    }

    private void onProductSelect() {
        mDatabaseHelper = new DatabaseHandler(ctx);
        mDatabaseHelper.getReadableDatabase();


        category_id = MainActivity.getCurrentCategory();
        products = mDatabaseHelper.getProductsByCategory(category_id);

        recent = MainActivity.getRecentProducts();

        mDatabaseHelper.close();

        Log.d("DB logging, products: ", products.toString());

        productAdapter = new ProductAdapter(ctx, products);


        recentAdapter = new RecentAdapter(ctx, recent);

        Log.d(LOG_TAG, recent.toString());

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.product_list);
        lvMain.setAdapter(productAdapter);



        ListView lvRecent = (ListView) findViewById(R.id.recent_products);
        lvRecent.setAdapter(recentAdapter);


        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = "
                        + id);
//
//                mDatabaseHelper.getReadableDatabase();
//
//                DatabaseHandler.Product product = mDatabaseHelper.getProductById(Integer.parseInt(arg1.getTag().toString()));
//
//                mDatabaseHelper.close();
////
////                Log.d(LOG_TAG, product.getName() + String.valueOf(product.getPrice())
////                                                + product.getDescription()
////                                                + String.valueOf(product.getLocalIcon()));
//
//                Bundle bundle = new Bundle();
//                bundle.putString("product_name", product.getName());
//                bundle.putString("product_price", String.valueOf(product.getPrice()));
//                bundle.putString("product_description", product.getDescription());
//                bundle.putString("product_local_icon", String.valueOf(product.getLocalIcon()));
//
//                fragment.setArguments(bundle);
//                fragmentTransaction.replace(android.R.id.content, fragment);
//                fragmentTransaction.commit();
                MainActivity.addRecentProduct(mDatabaseHelper.getProductById(Integer.parseInt(arg1.getTag().toString())));
                Intent intent = new Intent(getApplicationContext(), CollectionDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onShopListSelect() {
    }

    private void onCategorySelect() {
    }

    public static List<DatabaseHandler.Product> getProductObjects() {
        return products;
    }
}
