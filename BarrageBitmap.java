import android.graphics.Bitmap;

/**
 * Created by kisstheraik on 17/1/4.
 */

public class BarrageBitmap {
    public boolean isAlive = true;
    public float topDistance = 0 ;
    public float leftDistance = 0;
    public float step = 1 ;
    public Bitmap bitmap;
    public void onDrawFinish(boolean overBroad){

        if( overBroad ) {
            isAlive = false;
        }else {
            leftDistance += step;
        }

    }
}
