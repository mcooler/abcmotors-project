package onshirt.co.nz.abcmotors;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        final MainFragment mainFragment = new MainFragment();
        final LoanFragment loanApplication = new LoanFragment();
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
                    showFragment(loanApplication);
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
    private void showFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
        //avoide overlapping
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame);
        frameLayout.removeAllViews();
//        if (!fragment.isAdded()){
//            fragmentTransaction.hide(fragment).add(R.id.frame, fragment);
//        }else{
//            fragmentTransaction.hide(fragment).show(fragment);
//        }
//        fragmentTransaction.commit();

    }
}
