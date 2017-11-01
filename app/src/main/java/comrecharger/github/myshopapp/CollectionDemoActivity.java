package comrecharger.github.myshopapp;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.List;


public class CollectionDemoActivity extends FragmentActivity {
    private static final String LOG_TAG = "CollectionActivity";
    // representing an object in the collection.
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager pager;
    List<DatabaseHandler.Product> products;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_collection_layout);

        products = (List<DatabaseHandler.Product>) ProductListActivity.getProductObjects();

        pager = findViewById(R.id.pager);
        mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager(), products);
        pager.setAdapter(mDemoCollectionPagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(LOG_TAG, "Items: ");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(LOG_TAG, "Items: ");

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(LOG_TAG, "Items: ");

            }
        });



        // Create a tab listener that is called when the user changes tabs.

    }
}