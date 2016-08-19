package cn.andaction.loadinglayyer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.andaction.layyer.LayerViewController;
import cn.andaction.layyer.LayerViewHelperImp;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-07-18
 * Time: 14:41
 * Description: 预加载 -> (预先将 加载中视图、加载为空视图、加载失败视图、加载成功视图放置到xml布局中)
 */
public class PreloadLayerActivity extends AppCompatActivity implements View.OnClickListener {

    /***
     * 视图切换控制器
     */
    private LayerViewController mLayerViewController;


    private RelativeLayout rl_layer_container;

    private RelativeLayout rl_loadding;
    private RelativeLayout rl_error;
    private RelativeLayout rl_empty;
    private LinearLayout ll_success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);

        initView();

        mLayerViewController = new LayerViewController(rl_layer_container, LayerViewHelperImp.LayerModel.preload);
        mLayerViewController.showVariableView(rl_loadding, LayerViewHelperImp.LayerStatus.loadding);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_success:
                mLayerViewController.showVariableView(ll_success, LayerViewHelperImp.LayerStatus.success);
                break;
            case R.id.btn_empty:
                mLayerViewController.showVariableView(rl_empty, LayerViewHelperImp.LayerStatus.empty);
                break;
            case R.id.btn_error:
                mLayerViewController.showVariableView(rl_error, LayerViewHelperImp.LayerStatus.error);
                break;
            case R.id.btn_loadding:
                mLayerViewController.showVariableView(rl_loadding, LayerViewHelperImp.LayerStatus.loadding);
                break;
        }
    }
    private void initView() {
        rl_layer_container = (RelativeLayout) findViewById(R.id.rl_layer_container);
        rl_error = (RelativeLayout) findViewById(R.id.rl_error);
        rl_empty = (RelativeLayout) findViewById(R.id.rl_empty);
        ll_success = (LinearLayout) findViewById(R.id.rl_success);
        rl_loadding = (RelativeLayout) findViewById(R.id.rl_loadding);

        findViewById(R.id.btn_success).setOnClickListener(this);
        findViewById(R.id.btn_empty).setOnClickListener(this);
        findViewById(R.id.btn_error).setOnClickListener(this);
        findViewById(R.id.btn_loadding).setOnClickListener(this);

    }



}
