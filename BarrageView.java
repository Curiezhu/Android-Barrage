import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.kejian.mike.cardwindow.Barrage.Data.BarrageBitmap;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kisstheraik on 17/1/4.
 * 弹幕的界面
 */

public class BarrageView extends View{


    //维护一个需要画的数据缓存
    private ArrayList<WeakReference<BarrageBitmap>> currentList = new ArrayList<>();

    private final Object clock = new Object();

    public boolean addBitmap(BarrageBitmap barrageBitmap){
        invalidate();
        WeakReference<BarrageBitmap> barrageBitmapWeakReference= new WeakReference<BarrageBitmap>(barrageBitmap);

                synchronized (clock) {
                    currentList.add(barrageBitmapWeakReference);
                }

                return true;

    }


    public BarrageView(Context context) {
        super(context);
    }

    public BarrageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BarrageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //具体的绘制策略
    @Override
    public void onDraw(Canvas canvas){

        if( currentList == null || currentList.isEmpty() ){
            return;
        }

        Iterator<WeakReference<BarrageBitmap>> iterator = currentList.iterator();

        while(iterator.hasNext()){

            final BarrageBitmap barrageBitmap=iterator.next().get();

            if( barrageBitmap == null ){
                continue;
            }

            if( !barrageBitmap.isAlive || ifOverBoard(barrageBitmap.leftDistance)){
                iterator.remove();
                continue;
            }

            draw(canvas,barrageBitmap);
            //在子线程回调订阅者
            this.post(new Runnable() {
                @Override
                public void run() {
                    barrageBitmap.onDrawFinish(false);
                }
            });

            //每次画完之后需要有一个延迟 20ms

           invalidate();
        }

    }

    public  boolean ifOverBoard(float location){

        if( location > getWidth() ){
            return true;
        }else{
            return false;
        }
    }


    private void draw(Canvas canvas,BarrageBitmap barrageBitmap){

        if( canvas == null || barrageBitmap == null ){
            return;
        }

        canvas.drawBitmap(barrageBitmap.bitmap,barrageBitmap.leftDistance,barrageBitmap.topDistance,null);

    }
}
