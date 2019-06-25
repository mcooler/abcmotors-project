package onshirt.co.nz.abcmotors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FiveChess extends AppCompatActivity implements View.OnClickListener {
    private Button btn_reatart;
    private ChessView chessView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fivechess);
        btn_reatart = findViewById(R.id.bt_restart);
        chessView=  findViewById(R.id.custon_chess_main);
        btn_reatart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_restart:
                chessView.myreStart();
                break;
        }
    }
}
