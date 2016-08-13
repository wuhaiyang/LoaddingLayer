package cn.andaction.layyer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-07-18
 * Time: 13:52
 * Description: .....
 */
public class LayerViewController {

    private ILayerViewHelper mHelper;

    public LayerViewController (ViewGroup view) {
        this (view, LayerViewHelperImp.LayerModel.responsive);
    }

    public LayerViewController (ViewGroup view, LayerViewHelperImp.LayerModel model) {
        mHelper = new LayerViewHelperImp (view, model);
    }

    public void showVariableView (@Nullable View showView, LayerViewHelperImp.LayerStatus status) {
        mHelper.showVariableLayer (showView, status);
    }

    public Context getContext () {
        return mHelper.getContext ();
    }

    public LayerViewHelperImp.LayerStatus getStatus () {
        return mHelper.getCurrentStatus ();
    }


}
