package lqy.bwie.com.lianxi;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/16
 * desc:
 */
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
