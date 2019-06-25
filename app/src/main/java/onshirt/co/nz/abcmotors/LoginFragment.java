package onshirt.co.nz.abcmotors;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment {
    Button btn_login;
    EditText et_username, et_password;
    RequestQueue requestQueue;

//    public LoginFragment(MainActivity activity) {
//        mActivity = activity;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, null);



        et_username = view.findViewById(R.id.et_username);
        et_password = view.findViewById(R.id.et_password);

        btn_login = view.findViewById(R.id.btn_login);

        requestQueue = Volley.newRequestQueue(this.getActivity());


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });



        return view;

    }
//    public void loginButtonClicked(View view) {
//            login();
//    }

    public void login(){
        StringRequest request = new StringRequest(Request.Method.POST, "https://abcmotors.co.nz/android/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("correct")){
                    startActivity(new Intent(getActivity(),CustomerPage.class));
                }else {
                    Toast.makeText(getActivity(),"Please enter correct username and "+
                            response, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),
                        error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("username", et_username.getText().toString());
                params.put("password", et_password.getText().toString());
                return params;
            }
        };
        requestQueue .add(request);

    }


}
