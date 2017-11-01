package comrecharger.github.myshopapp;

import android.app.Activity;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by ygn on 29.10.17.
 */

public class ProductFragment extends Fragment {
    final String LOG_TAG = "Product fragment log";
    private View view;
    public static final String ARG_OBJECT = "object";
    private DatabaseHandler mDBHandler;
    private DateFormat dateFormat;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(LOG_TAG, "Fragment1 onAttach");
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Fragment1 onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fragment1 onCreateView");
        view = inflater.inflate(R.layout.product_fragment, null);

        ((TextView) view.findViewById(R.id.product_name)).setText(getArguments().getString("product_name"));
        ((TextView) view.findViewById(R.id.product_price)).setText(getArguments().getString("product_price"));
        ((TextView) view.findViewById(R.id.product_description)).setText(getArguments().getString("product_description"));
        ((ImageView) view.findViewById(R.id.product_icon)).setImageResource(Integer.parseInt(getArguments().getString("product_local_icon")));


        Button addToCard = view.findViewById(R.id.add_to_cart);
        addToCard.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, String.valueOf(getArguments().getInt("product_id")));
//                mDBHandler.addShopListItem(
//                    new DatabaseHandler.ShopListItem(getArguments().getInt("product_id"),
//                            getArguments().getInt("product_id"),
//                            1,
//                            String.valueOf(dateFormat.format(date))
//                            ));
            }
        });
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(LOG_TAG, "Fragment1 onActivityCreated");
    }

    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "Fragment1 onStart");
    }

}
