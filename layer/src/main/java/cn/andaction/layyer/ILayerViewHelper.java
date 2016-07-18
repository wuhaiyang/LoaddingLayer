package cn.andaction.layyer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by wuhaiyang on 2016/7/18.
 */
public interface ILayerViewHelper {

    void showVariableLayer(View view,LayerViewHelperImp.LayerStatus status);

    Context getContext();

    LayoutInflater getLayoutInflator();

    LayerViewHelperImp.LayerStatus getCurrentStatus();

    LayerViewHelperImp.LayerModel getLayerModel();

    void releaseMemory();


}
