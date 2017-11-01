package comrecharger.github.myshopapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by ygn on 31.10.17.
 */

public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
    private List<DatabaseHandler.Product> products;
    private DatabaseHandler.Product product;

    public DemoCollectionPagerAdapter(FragmentManager fm, List<DatabaseHandler.Product> object) {
        super(fm);
        products = object;
    }

    @Override
    public Fragment getItem(int i) {


        product = products.get(i);
        Bundle bundle = new Bundle();
        bundle.putString("product_name", product.getName());
        bundle.putInt("product_id", product.getId());
        bundle.putString("product_price", String.valueOf(product.getPrice()));
        bundle.putString("product_description", product.getDescription());
        bundle.putString("product_local_icon", String.valueOf(product.getLocalIcon()));


        Fragment fragment = new ProductFragment();
        // Our object is just an integer :-P
        bundle.putInt(ProductFragment.ARG_OBJECT, i + 1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return products.size();
    }


    // id по позиции
    public long getItemId(int position) {
        return position;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}