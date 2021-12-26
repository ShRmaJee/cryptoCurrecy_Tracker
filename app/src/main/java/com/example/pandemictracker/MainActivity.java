package com.example.pandemictracker;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.pandemictracker.databinding.ActivityMainBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText search_curr;
    private ArrayList <currencyModel> currencyModelArrayList;
    private viewAdapter viewAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_curr = findViewById(R.id.search_bar);
        currencyModelArrayList = new ArrayList<>();
        viewAdapter = new viewAdapter(currencyModelArrayList, this);
        recyclerView = findViewById(R.id.RVcurrencies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(viewAdapter);

        get_data();

        search_curr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    public void filter(String currency){
       ArrayList <currencyModel> filtered = new ArrayList<>();

       for(currencyModel i : currencyModelArrayList){
           if (i.getName().toLowerCase().contains(currency.toLowerCase())){
               filtered.add(i);
           }
           if(filtered.isEmpty()){
               Toast.makeText(MainActivity.this, "name not found!!", Toast.LENGTH_SHORT);
           }else{
               viewAdapter.filterList(filtered);
           }
       }
    }

    public void get_data(){
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=84db28b4-a635-437a-83e5-83aed6996004";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    JSONArray jsonArray = response.getJSONArray("data");

                    for(int i = 0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String symb = jsonObject.getString("symbol");
                        String max_supply = jsonObject.getString("max_supply");
                        String circulating_supply = jsonObject.getString("circulating_supply");
                        String total_supply = jsonObject.getString("total_supply");
                        JSONObject quote = jsonObject.getJSONObject("quote");
                        JSONObject usd = quote.getJSONObject("USD");
                        double price = usd.getDouble("price");
                        String volume = usd.getString("volume_24h");
                        String volume_change_24h = usd.getString("volume_change_24h");
                        String percent_change_7d = usd.getString("percent_change_7d");
                        String market_cap = usd.getString("market_cap");

                        currencyModelArrayList.add(new currencyModel(name, symb, price, max_supply, circulating_supply, total_supply, volume, market_cap, volume_change_24h, percent_change_7d));


                    }
                    viewAdapter.notifyDataSetChanged();
                }catch(JSONException e){
                    Toast.makeText(MainActivity.this, "Cannot fetch Data!!", Toast.LENGTH_LONG);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Unable to fetch data!!", Toast.LENGTH_SHORT);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}