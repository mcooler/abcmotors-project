package onshirt.co.nz.abcmotors;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MainFragment extends Fragment {

    private String URLstring = "https://abcmotors.co.nz/android/api.php";
    ArrayList<DataModel> dataModelArrayList;
    private RvAdapter rvAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);


        recyclerView = view.findViewById(R.id.recycler);

        fetchingJSON();



        return view;
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
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);
    }

    private void setupRecycler(){
        rvAdapter = new RvAdapter(this.getActivity(), dataModelArrayList);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
    }

}
