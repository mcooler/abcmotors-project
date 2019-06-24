package onshirt.co.nz.abcmotors;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String URLstring = "https://abcmotors.co.nz/android/api.php";
    ArrayList<DataModel> dataModelArrayList;
    private RvAdapter rvAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        fetchingJSON();

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        final MainFragment mainFragment = new MainFragment();
        final LoanFragment loanFragment = new LoanFragment();
        final LocationFragment locationFragment = new LocationFragment();
        final LoginFragment loginFragment = new LoginFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id==R.id.navigation_home){
                    showFragment(mainFragment);
                    return true;
                }else if (id == R.id.loan){
                    showFragment(loanFragment);
                    return true;
                }else if (id == R.id.location){
                    showFragment(locationFragment);
                    return true;
                }else if (id == R.id.login){
                    showFragment(loginFragment);
                    return true;
                }

                return false;
            }
        });
        navigationView.setSelectedItemId(R.id.bottom_nav);

    }
    private void fetchingJSON() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //JSONObject obj = new JSONObject(response);
                            JSONArray dataArray = new JSONArray(response);
                            if (dataArray.length() > 0) {

                                dataModelArrayList = new ArrayList<>();

                                for (int i = 0; i < dataArray.length(); i++) {
                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setProduct_id(dataobj.getString("product_id"));
                                    playerModel.setModel(dataobj.getString("model"));
                                    playerModel.setPrice(dataobj.getString("price"));
                                    playerModel.setImage(dataobj.getString("image"));

                                    dataModelArrayList.add(playerModel);
                                }


                            }
                            setupRecycler();


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setupRecycler(){
        rvAdapter = new RvAdapter(this,dataModelArrayList);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false));
    }

    private void showFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.framelayout, fragment);
//        fragmentTransaction.commit();
//        avoide overlapping
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.framelayout);
        frameLayout.removeAllViews();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
//        if (!fragment.isAdded()){
//            fragmentTransaction.hide(fragment).add(R.id.frame, fragment);
//        }else{
//            fragmentTransaction.hide(fragment).show(fragment);
//        }
//        fragmentTransaction.commit();

    }
}
