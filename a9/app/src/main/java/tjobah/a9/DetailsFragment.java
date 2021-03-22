package tjobah.a9;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    private final Context context;
    Coin coin;
    TextView chartLabel;
    private LineData data;
    LineChart lineChart;
    int color= 0;


    /**
     * Constructor for fragment class
     * @param context
     * @param coin
     */
    public DetailsFragment(Context context, Coin coin) {
        this.coin = coin;
        this.context = context;
        getHistoricalData();


        if(coin.getName().equals("bitcoin")){
            color = ContextCompat.getColor(context,R.color.bitcoin);
        } else if(coin.getName().equals("ethereum")){
            color = ContextCompat.getColor(context,R.color.ethereum);
        }
    }

    /**
     * Function that grabs data and styles the charts
     */
    private void getHistoricalData() {
        String url = String.format("https://api.coingecko.com/api/v3/coins/%s/market_chart?vs_currency=usd&days=90&interval=daily", coin.getName());
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try{
                    JSONArray prices = response.getJSONArray("prices");

                    ArrayList<Entry>yValues = new ArrayList<>();
                    for(int i = 0; i < prices.length();i++){
                        float val = (float)prices.getJSONArray(i).getDouble(1);
                        yValues.add(new Entry(i,val));
                    }
                    LineDataSet dataSet = new LineDataSet(yValues,"Daily Price");
                    dataSet.setColor(color);
                    dataSet.setLineWidth(3);
                    dataSet.setDrawCircles(false);
                    dataSet.setDrawValues(false);

                    data = new LineData(dataSet);
                    updateChart();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * function to update the chart and checks for empty sets
     */
    private void updateChart() {
        if(lineChart != null){
            lineChart.setData(data);
            lineChart.invalidate();
            lineChart.notifyDataSetChanged();
        }
    }


    /**
     * Creates the layout of the chart and inserts the instance
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    /**
     * Creates the chart and animates it
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        chartLabel = getView().findViewById(R.id.txtChartLabel);
        chartLabel.setText(coin.getName().toUpperCase());
        lineChart = getView().findViewById(R.id.lcprice);
        lineChart.animateY(100);
        lineChart.animateX(2000);
        updateChart();
    }
}