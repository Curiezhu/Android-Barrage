import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by kisstheraik on 17/1/4.
 * 弹幕数据，目前只支持文字
 */

public class BarrageItem {

    public String text;
    public int hight = 0;
    public int wight = 0;
    public float step = 0;

    public BarrageItem(int h,int w,String text,float step){

        this.hight = h;
        this.wight = w;
        this.text = text;
        this.step = step;

    }

    @Override
    public boolean equals(Object that){

        if(that != null && that instanceof BarrageItem) {
            return this.text.equals(((BarrageItem) that).text);
        }else {
            return false;
        }
    }
    @Override
    public Object clone(){

        BarrageItem barrageItem = new BarrageItem(0,0,"",0);

        barrageItem.text = this.text;

        return barrageItem;
    }
    //根据当前的弹幕信息获取bitmap
    public BarrageBitmap getBitmap(){

        BarrageBitmap barrageBitmap = new BarrageBitmap();

        barrageBitmap.isAlive = true;
        barrageBitmap.leftDistance = 0;
        barrageBitmap.step = this.step;
        barrageBitmap.topDistance = 0;

        //start a new bitmap
        barrageBitmap.bitmap = Bitmap.createBitmap( hight ,wight , Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(barrageBitmap.bitmap);

        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setTextSize(22);

        canvas.drawText(text,hight/2,wight/2,paint);

        return barrageBitmap;
    }



}
