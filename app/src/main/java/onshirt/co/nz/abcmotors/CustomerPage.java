package onshirt.co.nz.abcmotors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class CustomerPage extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_page);


        Button apply_loan = findViewById(R.id.loan_apply);
        apply_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerPage.this, LoanApplication.class);
                startActivity(intent);
            }
        });


        Button board_game = findViewById(R.id.boardgame);
        board_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerPage.this, FiveChess.class);
                startActivity(intent);
            }
        });



    }
}
