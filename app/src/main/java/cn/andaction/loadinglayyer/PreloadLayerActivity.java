package cn.andaction.loadinglayyer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;

import cn.andaction.layyer.LayerViewController;
import cn.andaction.layyer.LayerViewHelperImp;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-07-18
 * Time: 14:41
 * Description: .....
 */
public class PreloadLayerActivity extends AppCompatActivity {

    private RelativeLayout rl_layer_container;
    private LayerViewController mLayerViewController;


    private RelativeLayout rl_error;
    private RelativeLayout rl_empty;
    private LinearLayout ll_success;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
        rl_layer_container = (RelativeLayout) findViewById(R.id.rl_layer_container);

        rl_error = (RelativeLayout) findViewById(R.id.rl_error);
        rl_empty = (RelativeLayout) findViewById(R.id.rl_empty);
        ll_success = (LinearLayout) findViewById(R.id.rl_success);

        rl_empty.findViewById(R.id.aa_btn_reload).setOnClickListener(mClickListener);
        rl_error.findViewById(R.id.aa_btn_reload).setOnClickListener(mClickListener);


        mLayerViewController = new LayerViewController(rl_layer_container, LayerViewHelperImp.LayerModel.preload);
        load();
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            load();
        }
    };

    private void load() {
        mLayerViewController.showVariableView(findViewById(R.id.rl_loadding), LayerViewHelperImp.LayerStatus.loadding);
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
                    mLayerViewController.showVariableView(rl_error, LayerViewHelperImp.LayerStatus.error);
                } else if (random == 1) {
                    mLayerViewController.showVariableView(rl_empty, LayerViewHelperImp.LayerStatus.empty);
                } else {
                    mLayerViewController.showVariableView(ll_success, LayerViewHelperImp.LayerStatus.success);
                }
            }
        });
    }


}
