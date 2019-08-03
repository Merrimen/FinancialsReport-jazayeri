package mohammad.financialsreport;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

public class myApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
