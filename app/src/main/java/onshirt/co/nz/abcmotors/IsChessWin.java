package onshirt.co.nz.abcmotors;

import android.content.Context;
import android.graphics.Point;

import java.util.List;

public class IsChessWin {
    private boolean isGameOver = false;
    private boolean isWhiteWin;
    private int MAX_NUMWIN = 5;
    private int CURRENT_NUM = 0;
    private Context mContext;
    private boolean isRestart=false;
    public IsChessWin(Context context) {
        super();
        mContext = context;
    }


    public boolean isGameOverMethod(List<Point> whitePoints, List<Point> blackPoints) {
        boolean whiteWin = isWhiteWin(whitePoints);
        boolean blackWin = isBlackWin(blackPoints);

        if (whiteWin || blackWin) {
            isGameOver = true;
            isWhiteWin = whiteWin;
        }
        return isGameOver;
    }


    public boolean isWhiteWinFlag(){
        return isWhiteWin;
    }


    private boolean isWhiteWin(List<Point> points) {
        if (isFiveConnect(points)) {
            return true;
        }
        return false;
    }

    private boolean isBlackWin(List<Point> points) {
        if (isFiveConnect(points)) {
            return true;
        }
        return false;
    }


    private boolean isFiveConnect(List<Point> points) {
        for (Point p : points) {
            int x = p.x;
            int y = p.y;
            if (isHorizontalFive(x, y, points)) {
                return true;
            } else if (isVerticalFive(x, y, points)) {
                return true;
            } else if (isSkewFive(x, y, points)) {
                return true;
            }
        }
        return false;
    }


    private boolean isHorizontalFive(int x, int y, List<Point> points) {
        for (int i = 0; i < MAX_NUMWIN; i++) {
            if (points.contains(new Point(x + i, y))) {
                CURRENT_NUM++;
            } else {
                break;
            }
        }
        if (MAX_NUMWIN == CURRENT_NUM) {
            return true;
        } else {
            CURRENT_NUM = 0;
        }

        for (int i = 0; i < MAX_NUMWIN; i++) {
            if (points.contains(new Point(x - i, y))) {
                CURRENT_NUM++;
            } else {
                break;
            }
        }
        if (MAX_NUMWIN == CURRENT_NUM) {
            return true;
        } else {
            CURRENT_NUM = 0;
        }
        return false;
    }


    private boolean isVerticalFive(int x, int y, List<Point> points) {
        for (int i = 0; i < MAX_NUMWIN; i++) {

            if (points.contains(new Point(x, y + i))) {
                CURRENT_NUM++;
            } else {
                break;
            }
        }
        if (MAX_NUMWIN == CURRENT_NUM) {
            return true;
        } else {
            CURRENT_NUM = 0;
        }

        for (int i = 0; i < MAX_NUMWIN; i++) {
            if (points.contains(new Point(x, y - i))) {
                CURRENT_NUM++;
                if (5 == CURRENT_NUM) {
                    return true;
                }
            } else {
                CURRENT_NUM = 0;
                break;
            }
        }
        if (MAX_NUMWIN == CURRENT_NUM) {
            return true;
        } else {
            CURRENT_NUM = 0;
        }
        return false;
    }




    private boolean isSkewFive(int x, int y, List<Point> points) {

        for (int i = 0; i < MAX_NUMWIN; i++) {
            if (points.contains(new Point(x - i, y + i))) {
                CURRENT_NUM++;
            } else {
                break;
            }
        }
        if (MAX_NUMWIN == CURRENT_NUM) {
            return true;
        } else {
            CURRENT_NUM = 0;
        }

        for (int i = 0; i < MAX_NUMWIN; i++) {
            if (points.contains(new Point(x - i, y - i))) {
                CURRENT_NUM++;
            } else {
                break;
            }
        }
        if (MAX_NUMWIN == CURRENT_NUM) {
            return true;
        } else {
            CURRENT_NUM = 0;
        }

        for (int i = 0; i < MAX_NUMWIN; i++) {
            if (points.contains(new Point(x + i, y - i))) {
                CURRENT_NUM++;
            } else {
                break;
            }
        }
        if (MAX_NUMWIN == CURRENT_NUM) {
            return true;
        } else {
            CURRENT_NUM = 0;
        }

        for (int i = 0; i < MAX_NUMWIN; i++) {
            if (points.contains(new Point(x + i, y - i))) {
                CURRENT_NUM++;
            } else {
                break;
            }
        }
        if(MAX_NUMWIN==CURRENT_NUM){
            return true;
        }else{
            CURRENT_NUM=0;
        }

        for (int i = 0; i < MAX_NUMWIN; i++) {
            if (points.contains(new Point(x + i, y + i))) {
                CURRENT_NUM++;
            } else {
                break;
            }
        }
        if(MAX_NUMWIN==CURRENT_NUM){
            return true;
        }else{
            CURRENT_NUM=0;
        }
        return false;
    }
}