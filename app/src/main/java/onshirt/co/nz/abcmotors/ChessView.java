package onshirt.co.nz.abcmotors;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class ChessView extends View {
    private int mPanelWith; //width
    private float mLineHeigth;  //heigh between each line
    private int MAX_LINE = 10;    //total line
    private Paint mPint = new Paint();    //paint the chess board
    private Bitmap wPieces;     //white chess
    private Bitmap bPieces;     //black chess
    private ArrayList<Point> wPoints = new ArrayList<>();   //point of white chess
    private ArrayList<Point> bPoints = new ArrayList<>();   //point of black chess
    private float radioPoeces = 1.0f * 3 / 4;    //size of chess
    private boolean mIsWitch = true;
    private boolean isGameOver = false;
    private Context mContext;
    private IsChessWin isChessWin;
    private String TAG = "CHESSVIEW";

    public ChessView(Context context) {
        super(context);
        mContext = context;
        init();
    }


    public ChessView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public ChessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    //method of restart
    public void myreStart() {
        wPoints.clear();
        bPoints.clear();
        isGameOver = false;
        Log.i(TAG, "myreStart: " + wPoints.size() + ":::" + bPoints.size());
        invalidate();

    }

        //
    public void init() {

        mPint.setColor(Color.BLACK);

        mPint.setStyle(Paint.Style.STROKE);


        wPieces = BitmapFactory.decodeResource(getResources(), R.drawable.stone_w2);
        bPieces = BitmapFactory.decodeResource(getResources(), R.drawable.stone_b1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize, heightSize);

        //
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        //
        setMeasuredDimension(width, width);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWith = w;
        mLineHeigth = mPanelWith * 1.0f / MAX_LINE;

        int piecesWidth = (int) (mLineHeigth * radioPoeces);
        //
        wPieces = Bitmap.createScaledBitmap(wPieces, piecesWidth, piecesWidth, true);
        bPieces = Bitmap.createScaledBitmap(bPieces, piecesWidth, piecesWidth, true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isGameOver = isChessWin.isGameOverMethod(wPoints, bPoints);
        if (isGameOver) {
            showDialog();
            return false;
        }
        int action = event.getAction();
        if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Point point = getSimulatePoint(x, y);

            if (wPoints.contains(point) || bPoints.contains(point)) {
                return false;
            }
            if (mIsWitch) {
                wPoints.add(point);
            } else {
                bPoints.add(point);
            }
            mIsWitch = !mIsWitch;

            invalidate();
            return true;
        }
        return true;
    }


    public Point getSimulatePoint(int x, int y) {
        return new Point((int) (x / mLineHeigth), (int) (y / mLineHeigth));
    }


    public void showDialog() {
        String successText = isChessWin.isWhiteWinFlag() ? "White Chess Win!" : "Black Chess Win";
        new AlertDialog.Builder(mContext)
                .setMessage("Well done: " + successText + ",would you like try again?")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myreStart();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
        drawPieces(canvas);

        isChessWin = new IsChessWin(mContext);
        isGameOver = isChessWin.isGameOverMethod(wPoints, bPoints);
        if(isGameOver){
            showDialog();
        }

    }

    public void drawBoard(Canvas canvas) {
        int w = mPanelWith;
        float lineHeight = mLineHeigth;

        for (int i = 0; i < MAX_LINE; i++) {
            int startX = (int) (lineHeight / 2);
            int endX = (int) (w - lineHeight / 2);
            int y = (int) ((0.5 + i) * lineHeight);
            canvas.drawLine(startX, y, endX, y, mPint);
            canvas.drawLine(y, startX, y, endX, mPint);
        }
    }


    public void drawPieces(Canvas canvas) {
        Log.i(TAG, "drawPieces: " + wPoints.size() + ":::" + bPoints.size());
        for (int i = 0; i < wPoints.size(); i++) {
            Point point = wPoints.get(i);

            canvas.drawBitmap(wPieces, ((point.x + (1 - radioPoeces) / 2) * mLineHeigth), (point.y + (1 - radioPoeces) / 2) * mLineHeigth, null);
        }
        for (int i = 0; i < bPoints.size(); i++) {
            Point point = bPoints.get(i);
            canvas.drawBitmap(bPieces, ((point.x + (1 - radioPoeces) / 2) * mLineHeigth), (point.y + (1 - radioPoeces) / 2) * mLineHeigth, null);
        }
    }

    private static final String INSTANCE = "instance";
    private static final String INSTANCE_GAMEOVER = "instance_gameover";
    private static final String INSTANCE_WHITEARRAY = "instance_whitearray";
    private static final String INSTANCE_BLACKARRAY = "instance_blackarray";

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();

        bundle.putParcelable(INSTANCE, super.onSaveInstanceState());
        bundle.putBoolean(INSTANCE_GAMEOVER, isGameOver);
        bundle.putParcelableArrayList(INSTANCE_WHITEARRAY, wPoints);
        bundle.putParcelableArrayList(INSTANCE_BLACKARRAY, bPoints);
        return bundle;
    }


    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            isGameOver = bundle.getBoolean(INSTANCE_GAMEOVER);
            wPoints = bundle.getParcelableArrayList(INSTANCE_WHITEARRAY);
            bPoints = bundle.getParcelableArrayList(INSTANCE_BLACKARRAY);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE));
            return;
        }
        super.onRestoreInstanceState(state);
    }
}
