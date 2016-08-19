package cn.andaction.loadinglayyer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import cn.andaction.layyer.LayerViewController;
import cn.andaction.layyer.LayerViewHelperImp;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-07-18
 * Time: 14:41
 * Description: .....
 */
public class ResponsiveLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    FrameLayout mFrameLayout;

    LayerViewController mLayerViewController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsive);
        mFrameLayout = (FrameLayout) findViewById(R.id.rl_layer_container);
        mLayerViewController = new LayerViewController(mFrameLayout);



        findViewById(R.id.btn_success).setOnClickListener(this);
        findViewById(R.id.btn_empty).setOnClickListener(this);
        findViewById(R.id.btn_error).setOnClickListener(this);
        findViewById(R.id.btn_loadding).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_success:
                View successView = LayoutInflater.from(this).inflate(R.layout.success_view,null);
                mLayerViewController.showVariableView(successView, LayerViewHelperImp.LayerStatus.success);
                break;
            case R.id.btn_empty:
                View emptyView = LayoutInflater.from(this).inflate(R.layout.aa_layout_empty,null);
                mLayerViewController.showVariableView(emptyView, LayerViewHelperImp.LayerStatus.empty);
                break;
            case R.id.btn_error:
                View errorView = LayoutInflater.from(this).inflate(R.layout.aa_layout_error, null);
                mLayerViewController.showVariableView(errorView, LayerViewHelperImp.LayerStatus.error);
                break;
            case R.id.btn_loadding:
                View loaddingView = LayoutInflater.from(this).inflate(R.layout.aa_layout_loadding, null);
                mLayerViewController.showVariableView(loaddingView, LayerViewHelperImp.LayerStatus.loadding);
                break;
        }
    }


}
