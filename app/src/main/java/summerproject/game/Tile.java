package summerproject.game;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public final class Tile {
    private static final String TAG = Tile.class.getSimpleName();

    private final int col;
    private final int row;

    public Paint tileBG;
    private Rect tileRect;

    public Bitmap bitmap;

    public Tile(final int col, final int row, Bitmap img) {
        this.col = col;
        this.row = row;

        bitmap=img;
        this.tileBG=new Paint();
    }

    public void resizeBitmap(int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap,newWidth,newHeight,false);
        bitmap = resizedBitmap;
//        resizedBitmap.recycle();
    }

    public void draw(final Canvas canvas) {
        canvas.drawBitmap(bitmap,tileRect.top,tileRect.left,tileBG);
    }

    public void unitDraw(final Canvas canvas, Bitmap img){
        canvas.drawBitmap(overlay(bitmap,img),tileRect.top,tileRect.left,tileBG);
    }

    public void sfxDraw(final Canvas canvas, Bitmap unit, Bitmap sfx){
        if(unit!=null) {
            canvas.drawBitmap(overlay(overlay(bitmap, unit), sfx), tileRect.top, tileRect.left, tileBG);
        }
        else{
            canvas.drawBitmap(overlay(bitmap,sfx), tileRect.top, tileRect.left, tileBG);
        }
    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }


    public boolean isTouched(final int x, final int y) {
        return tileRect.contains(x, y);
    }

    public void setTileRect(final Rect tileRect) {
        this.tileRect = tileRect;
    }


}
