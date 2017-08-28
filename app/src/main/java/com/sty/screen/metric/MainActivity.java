package com.sty.screen.metric;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvDensity;
    private TextView tvDensityXY;
    private TextView tvDensityDpi;
    private TextView tvPrintSize;
    private TextView tvRealSize;
    private TextView tvScreenInch;
    private TextView tvScreenInch2;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initViews(){
        tvDensity = (TextView) findViewById(R.id.tv_density);
        tvDensityXY = (TextView) findViewById(R.id.tv_densityXY);
        tvDensityDpi = (TextView) findViewById(R.id.tv_densityDpi);
        tvPrintSize = (TextView) findViewById(R.id.tv_print_size);
        tvRealSize = (TextView) findViewById(R.id.tv_real_size);
        tvScreenInch = (TextView) findViewById(R.id.tv_screen_inch);
        tvScreenInch2 = (TextView) findViewById(R.id.tv_screen_inch2);

        tvDensity.setText("屏幕密度density：" + ScreenMetricUtils.getDensity(this));
        tvDensityDpi.setText("屏幕密度Dpi：" + ScreenMetricUtils.getDensityDpi(this));
        tvDensityXY.setText("屏幕密度XY：" + ScreenMetricUtils.getDensityXY(this));
        tvPrintSize.setText("屏幕尺寸：" + ScreenMetricUtils.getPrintScreenPixel(this));
        tvRealSize.setText("屏幕真实尺寸：" + ScreenMetricUtils.getRealScreenPixel(this));
        tvScreenInch.setText("屏幕Inch：" + ScreenMetricUtils.getScreenInchOfDevice(this));
        tvScreenInch2.setText("屏幕Inch2：" + ScreenMetricUtils.getScreenInch(this));
    }
}
