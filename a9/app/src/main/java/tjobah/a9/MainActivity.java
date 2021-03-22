package tjobah.a9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import tjobah.a9.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    FragmentManager fragmentManager;
    private Fragment bitcoinFragment, ethereumFragment;
    private Coin bitcoin,ethereum;


    /**
     * creates the layouts and sets bitcoin to open up the chart on start
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        bitcoin = new Coin("bitcoin");
        ethereum = new Coin("ethereum");
        getCurrentValue(bitcoin);
        getCurrentValue(ethereum);
        bitcoinFragment = new DetailsFragment(this,bitcoin);
        ethereumFragment = new DetailsFragment(this,ethereum);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.setBitcoin(bitcoin);
        activityMainBinding.setEthereum(ethereum);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, bitcoinFragment);
        fragmentTransaction.commit();
    }

    /**
     * gets the values from coinGecko and saves the data
     * @param coin
     */
    private void getCurrentValue(Coin coin) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            String url = String.format("https://api.coingecko.com/api/v3/simple/price?ids=%s&vs_currencies=usd"
                    , coin.getName());

            AsyncHttpClient client = new AsyncHttpClient();

            @Override
            public void run() {
                client.get(url, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONObject json = response.getJSONObject(coin.getName());
                            Log.d("update",String.valueOf(json));
                            double tmpPrice = json.getDouble("usd");
                            coin.setCurValue(tmpPrice);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                handler.postDelayed(this,10000);
            }
        }, 500);
    }

    /**
     * changes the chart depending on what table was clicked
     * @param view
     */
    public void onTableRowClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(view.getId() == R.id.trBitcoin){
            fragmentTransaction.replace(R.id.flFragment, bitcoinFragment);
            fragmentTransaction.commit();
        } else if (view.getId() == R.id.trEthereum){
            fragmentTransaction.replace(R.id.flFragment, ethereumFragment);
            fragmentTransaction.commit();
        }
    }
}