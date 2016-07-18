package cn.andaction.loadinglayyer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

import cn.andaction.layyer.LayerViewController;
import cn.andaction.layyer.LayerViewHelperImp;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-07-18
 * Time: 14:41
 * Description: .....
 */
public class ResponsiveLayoutActivity extends AppCompatActivity {

    FrameLayout mFrameLayout;
    LayerViewController mLayerViewController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsive);
        mFrameLayout = (FrameLayout) findViewById(R.id.rl_layer_container);
        mLayerViewController = new LayerViewController(mFrameLayout);

        load();
    }
    private void load() {
       showLoaddingView("走心的加载中...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    switchLayer();
                }
            }
        }).start();
    }

    private void switchLayer() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int random = new Random().nextInt(3);
                if (random == 0) {
                    showEmptyView(null,mClickListener);
                } else if (random == 1) {
                    showErrorView(null, mClickListener);
                } else {
                    mLayerViewController.showVariableView(LayoutInflater.from(ResponsiveLayoutActivity.this).inflate(R.layout.success_view,null), LayerViewHelperImp.LayerStatus.success);
                }
            }
        });
    }
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            load();
        }
    };


    public void showEmptyView(String emptyTips, View.OnClickListener retryListener) {
        View view = LayoutInflater.from(this).inflate(R.layout.aa_layout_empty, null);
        TextView tv_empty = (TextView) view.findViewById(R.id.aa_tv_empty);
        if (!TextUtils.isEmpty(emptyTips))
            tv_empty.setText(emptyTips);
        if (null != retryListener)
            view.findViewById(R.id.aa_btn_reload).setOnClickListener(retryListener);
        mLayerViewController.showVariableView(view, LayerViewHelperImp.LayerStatus.empty);
    }
    public void showErrorView(String errorTips, View.OnClickListener retryListener) {
        View view = LayoutInflater.from(this).inflate(R.layout.aa_layout_error, null);
        TextView tv_error = (TextView) view.findViewById(R.id.aa_tv_error);
        if (!TextUtils.isEmpty(errorTips))
            tv_error.setText(errorTips);
        if (null != retryListener)
            view.findViewById(R.id.aa_btn_reload).setOnClickListener(retryListener);
        mLayerViewController.showVariableView(view, LayerViewHelperImp.LayerStatus.error);

    }
    public void showLoaddingView(String loadTips) {
        View view = LayoutInflater.from(this).inflate(R.layout.aa_layout_loadding, null);
        TextView tv_load = (TextView) view.findViewById(R.id.aa_tv_load);
        if (!TextUtils.isEmpty(loadTips))
            tv_load.setText(loadTips);
        mLayerViewController.showVariableView(view, LayerViewHelperImp.LayerStatus.loadding);
    }

}
