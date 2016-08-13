package cn.andaction.layyer;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * User: Geek_Soledad(wuhaiyang@danlu.com)
 * Date: 2016-07-18
 * Time: 11:04
 * Description: .....
 */
public class LayerViewHelperImp implements ILayerViewHelper {
    /**
     * host container view include :successview errorview loaddingview emptyview ....
     */
    private ViewGroup mContainerView;
    private LayerModel mLayerModel;

    private LayerStatus mLayerStatus;
    private long mLastSwitchTime;
    private boolean isFirstSwitch = true;

    public LayerViewHelperImp (@NonNull ViewGroup containerView) {
        this (containerView, LayerModel.responsive);
    }

    public LayerViewHelperImp (@NonNull ViewGroup containerView, @NonNull LayerModel layerModel) {
        mContainerView = containerView;
        mLayerModel = layerModel;
        if (!(mContainerView instanceof FrameLayout || mContainerView instanceof RelativeLayout)) {
            throw new RuntimeException ("containerview must be relativelayout or framelayout");
        }
    }

    @Override
    public void showVariableLayer (final View view, final LayerStatus status) {
        if (mLayerStatus == status) {
            return;
        }

        long cur = System.currentTimeMillis ();
        long diff = cur - mLastSwitchTime;
        long await = 0;
        if (diff < 1000) {
            await = 1000 - diff;
        }
        mLastSwitchTime = cur;
        if (await > 0) {
            mContainerView.postDelayed (new Runnable () {
                @Override
                public void run () {
                    lazySwitchAction (view);
                }
            }, await);
        } else {
            lazySwitchAction (view);
        }
        mLayerStatus = status;
    }

    @Override
    public Context getContext () {
        return mContainerView.getContext ();
    }

    @Override
    public LayoutInflater getLayoutInflator () {
        return LayoutInflater.from (getContext ());
    }

    @Override
    public LayerStatus getCurrentStatus () {
        return mLayerStatus;
    }

    @Override
    public LayerModel getLayerModel () {
        return mLayerModel;
    }

    @Override
    public void releaseMemory () {
        mContainerView.removeAllViews ();
    }

    private void lazySwitchAction (final View view) {
        if (LayerModel.responsive == mLayerModel) {
            View chidView = mContainerView.getChildAt (0);
            if (null != chidView) {
                alphaAnim (chidView, 1.0f, 0f);
            }
            alphaAnim (view, 0f, 1.0f);
        } else {
            // 预加载 四种布局已经在xml中实现 每一个子布局必须是relativelayout framelayout linearlayout ...
            View needGoneView = null;
            View needVisibleView = null;
            boolean isVisibleFlag = false;
            for (int i = mContainerView.getChildCount () - 1; i >= 0; i--) {
                View childView = mContainerView.getChildAt (i);
                if (childView.getVisibility () == View.VISIBLE && !isVisibleFlag) {
                    needGoneView = childView;
                    isVisibleFlag = true;
                }
                if (childView != needGoneView) {
                    childView.setVisibility (View.GONE);
                }
                if (childView == view) {
                    needVisibleView = view;
                }
            }
            if (null != needGoneView) {
                needGoneView.setVisibility (View.GONE);
            }
            if (null != needVisibleView) {
                needVisibleView.setVisibility (View.VISIBLE);
            }
            //            isFirstSwitch = false;
            if (!isFirstSwitch) {
                if (null != needGoneView) {
                    //1.0 - > 0.0 动画
                    alphaAnim (needGoneView, 1.0f, 0f);
                }
                if (null != needVisibleView) {
                    // 0.0 - > 1.0  属性透明度动画
                    alphaAnim (needVisibleView, 0f, 1.0f);
                }
            } else {
                if (null != needGoneView) {
                    needGoneView.setVisibility (View.GONE);
                }
                if (null != needVisibleView) {
                    needVisibleView.setVisibility (View.VISIBLE);
                }
                isFirstSwitch = false;
            }
        }
    }

    private void alphaAnim (final View view, float fromAlpha, final float toAlpha) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat (view, "alpha", fromAlpha, toAlpha).setDuration (400);
        alphaAnim.addListener (new Animator.AnimatorListener () {
            @Override
            public void onAnimationStart (Animator animation) {

            }

            @Override
            public void onAnimationEnd (Animator animation) {
                if (toAlpha == 0f) {
                    if (mLayerModel == LayerModel.responsive) {
                        mContainerView.removeAllViews();
                    } else {
                        view.setVisibility (View.GONE);
                    }
                } else {
                    if (mLayerModel == LayerModel.responsive) {
                        mContainerView.removeAllViews();
                        mContainerView.addView (view);
                    } else {
                        view.setVisibility (View.VISIBLE);
                    }
                }
            }

            @Override
            public void onAnimationCancel (Animator animation) {

            }

            @Override
            public void onAnimationRepeat (Animator animation) {

            }
        });
        alphaAnim.start ();
    }

    public enum LayerStatus {
        success, error, loadding, empty
    }

    public enum LayerModel {
        responsive, preload
    }

}
