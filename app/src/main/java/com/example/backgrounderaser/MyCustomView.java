package com.example.backgrounderaser;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

public class MyCustomView extends View {

    private Bitmap destBitmap;
    private Canvas destCanvas = new Canvas();
    private Paint destPaint = new Paint();
    private Path destPath = new Path();
    private ArrayList<Bitmap> listAction = new ArrayList<>();
    private static final int REQUESST_PERMISSION = 1001;


    public MyCustomView(Context context) {
        super(context);

        Bitmap rawBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.img_tshirt);

        destBitmap = Bitmap.createBitmap(rawBitmap.getWidth(),rawBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        destCanvas.setBitmap(destBitmap);
        destCanvas.drawBitmap(rawBitmap,0,0,null);


        destPaint.setAlpha(0);
        destPaint.setAntiAlias(true);
        destPaint.setStyle(Paint.Style.STROKE);
        destPaint.setStrokeJoin(Paint.Join.ROUND);
        destPaint.setStrokeCap(Paint.Cap.ROUND);
        destPaint.setStrokeWidth(50);
        destPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        destCanvas.drawPath(destPath,destPaint);
        canvas.drawBitmap(destBitmap,0,0,null);
        super.onDraw(canvas);

    }

    /*public void lastAction(Bitmap bitmap){
        listAction.add(bitmap);
    }
    public void Undo(){
        if (listAction.size() > 0){
            listAction.remove(listAction.size()-1);
            if (listAction.size() > 0){
                destBitmap = listAction.get(listAction.size() - 1);
            }
            else {
                destBitmap = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
            }
            destCanvas = new Canvas(destBitmap);
            invalidate();
        }
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xPos = event.getX();
        float yPos = event.getY();

        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                destPath.moveTo(xPos,yPos);
                break;

            case MotionEvent.ACTION_MOVE:
                destPath.lineTo(xPos,yPos);
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                //Undo();
                break;

                default:
                    return false;
        }
        invalidate();
        return true;
    }



    private void touchUp() {
        destPath.reset();
    }

    public Bitmap getBitmap() {
        this.setDrawingCacheEnabled(true);

        this.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache());

        this.setDrawingCacheEnabled(false);
        return bitmap;
    }




}
