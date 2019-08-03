package mohammad.financialsreport.utilities;

import android.content.Context;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

public class PublicMethods {
    public static void toast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
    public static void setData(String key,String value){
        Hawk.put(key,value);
    }
    public static String getData(String key){
        return Hawk.get(key);
    }


}
