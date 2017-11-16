package com.sty.screen.metric;

import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Steven.T on 2017/11/16/0016.
 */

public class TabletUtil {
    //SCREENLAYOUT_SIZE_SMALL: 小尺寸
    //SCREENLAYOUT_SIZE_NORMAL: 正常尺寸
    //SCREENLAYOUT_SIZE_LARGE: 大尺寸
    //SCREENLAYOUT_SIZE_XLARGE: 超大尺寸

    /**
     * 通过屏幕尺寸来判断是否为Pad
     * @param context
     * @return
     */
    public static boolean isTabletByLayout(Context context){
        boolean isTablet = false;
        int sizeMode = context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        if(sizeMode >= Configuration.SCREENLAYOUT_SIZE_LARGE){
            isTablet = true;
        }
        Log.i("Tag", "isTabletByLayout:" + isTablet);
        return isTablet;
    }

    //PHONE_TYPE_NONE: 无
    //PHONE_TYPE_GSM: GSM
    //PHONE_TYPE_CDMA: CDMA
    //PHONE_TYPE_SIP: SIP

    /**
     * 通过是否能拨打电话来判断是否为Pad
     * @param context
     * @return
     */
    public static boolean isTabletByPhone(Context context){
        boolean isTablet = false;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = telephonyManager.getPhoneType();
        if(phoneType == TelephonyManager.PHONE_TYPE_NONE){
            isTablet = true;
        }
        Log.i("Tag", "can't make call?: " + isTablet);
        return isTablet;
    }

    /**
     * 通过屏幕是否打于6英寸来判断是否为Pad
     * @param context
     * @return
     */
    public static boolean isMoreThan6Inch(Context context){
        boolean moreThan6Inch = false;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        //屏幕尺寸
        double screenInches = Math.sqrt(x + y);
        //打于6英寸则为Pad
        if(screenInches >= 6.0){
            moreThan6Inch = true;
        }
        Log.i("Tag", "x:" + x + " y:" + y + " screen inch:" + screenInches + " moreThan6Inch:" + moreThan6Inch);
        return moreThan6Inch;
    }
}
