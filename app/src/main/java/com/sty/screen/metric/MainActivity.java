package com.sty.screen.metric;

import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvDensity;
    private TextView tvDensityXY;
    private TextView tvDensityDpi;
    private TextView tvPrintSize;
    private TextView tvRealSize;
    private TextView tvScreenInch;
    private TextView tvScreenInch2;

    private TextView tvStatusBar;
    private TextView tvTitleBar;
    private TextView tvScreenHeight;
    private TextView tvAppAreaHeight;
    private TextView tvAppAreaTop;
    private TextView tvDrawableAreaHeight;
    private TextView tvDrawableAreaTop;

    private Button btnJudgeIsPad;
    private Button btnForceSetOrientation;

    private Handler handler;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        initViews();
        setListeners();
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

        tvStatusBar = (TextView) findViewById(R.id.tv_status_bar);
        tvTitleBar = (TextView) findViewById(R.id.tv_title_bar);
        tvScreenHeight = (TextView) findViewById(R.id.tv_screen_height);
        tvAppAreaHeight = (TextView) findViewById(R.id.tv_app_area_height);
        tvAppAreaTop = (TextView) findViewById(R.id.tv_app_area_top);
        tvDrawableAreaHeight = (TextView) findViewById(R.id.tv_drawable_area_height);
        tvDrawableAreaTop = (TextView) findViewById(R.id.tv_drawable_area_top);

        btnJudgeIsPad = (Button) findViewById(R.id.btn_judge_ispad);
        btnForceSetOrientation = (Button) findViewById(R.id.btn_set_orientation);

        tvDensity.setText("屏幕密度density：" + ScreenMetricUtils.getDensity(this));
        tvDensityDpi.setText("屏幕密度Dpi：" + ScreenMetricUtils.getDensityDpi(this));
        tvDensityXY.setText("屏幕密度XY：" + ScreenMetricUtils.getDensityXY(this));
        tvPrintSize.setText("屏幕尺寸：" + ScreenMetricUtils.getPrintScreenPixel(this));
        tvRealSize.setText("屏幕真实尺寸：" + ScreenMetricUtils.getRealScreenPixel(this));
        tvScreenInch.setText("屏幕Inch：" + ScreenMetricUtils.getScreenInchOfDevice(this));
        tvScreenInch2.setText("屏幕Inch2：" + ScreenMetricUtils.getScreenInch(this));

        tvStatusBar.setText("状态栏高度：" + ScreenMetricUtils.getStatusBarHeight(this));
        tvScreenHeight.setText("屏幕高度：" + ScreenMetricUtils.getScreenHeight(this));

    }

    private void setListeners(){
        btnJudgeIsPad.setOnClickListener(this);
        btnForceSetOrientation.setOnClickListener(this);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //获取状态栏高度(应用区域顶部位置)
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        final int statusBarHeight = frame.top;

        //获取应用区域高度
        final int appAreaHeight = frame.height();

        // 获取绘制区域的顶部位置
        Window window = getWindow();
        final int drawableAreaTop = getWindow()
                .findViewById(Window.ID_ANDROID_CONTENT).getTop();

        //获取绘制区域高度
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(frame);
        final int drawableAreaHeight = frame.height();

        // 获取标题栏的高度
        final int titleBarHeight = drawableAreaTop - statusBarHeight;

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvAppAreaHeight.setText("应用区域高度：" + appAreaHeight);
                        tvAppAreaTop.setText("应用区域顶部位置：" + statusBarHeight);
                        tvDrawableAreaHeight.setText("绘制区域高度：" + drawableAreaHeight);
                        tvDrawableAreaTop.setText("绘制区域顶部位置：" + drawableAreaTop);
                        tvTitleBar.setText("标题栏的高度：" + titleBarHeight);
                    }
                });
            }
        }).start();

        Log.i("Tag", "statusBarHeight=" + statusBarHeight + " appAreaHeight=" + appAreaHeight +
                " drawableAreaTop=" + drawableAreaTop + " drawableAreaHeight=" + drawableAreaHeight +
                " titleBarHeight=" + titleBarHeight);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_judge_ispad:
                Toast.makeText(this, "is pad?: " +ScreenMetricUtils.isPad(MainActivity.this), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_set_orientation:
                ScreenMetricUtils.forceSetOrientation(this);
                break;
            default:
                break;
        }
    }
}
