package com.pluu.pluubasiclibrary.pluu.anim;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewPropertyAnimator;

import java.util.ArrayList;

/**
 * OneShot Frame Animator
 * <br>AnimationDrawable 사용 기반
 * Created by PLUUSYSTEM on 2014-10-22.
 */
public class FrameAnimator extends Animator implements AnimatorListener {

    private AnimationDrawable mFrame;
    private final int FRAME_DURATION = 500;
    private Context mContext;
    private ViewPropertyAnimator mViewPropertyAnimator;
    private View mTarget;
    private long startDelay;
    private long duration = 0L;
    private boolean mRunning = false;

    private ArrayList<AnimatorListener> mListeners;

    public FrameAnimator(Context context, View target)
    {
        this(context, target, null);
    }

    public FrameAnimator(Context context, View target, int[] frames)
    {
        mContext = context;
        mTarget = target;

        mFrame = new AnimationDrawable();
        mListeners = new ArrayList<>();

        if (frames != null && frames.length > 0)
        {
            Resources res = mContext.getResources();
            for (int i = 0; i < frames.length; i++)
            {
                mFrame.addFrame(res.getDrawable(frames[i]), FRAME_DURATION);
            }
        }
    }

    public void addFrame(int resID)
    {
        addFrame(resID, FRAME_DURATION);
    }

    public void addFrame(int resID, int duration)
    {
        Resources res = mContext.getResources();
        mFrame.addFrame(res.getDrawable(resID), duration);
    }

    @Override
    public long getStartDelay()
    {
        return startDelay;
    }

    @Override
    public void setStartDelay(long startDelay)
    {
        this.startDelay = startDelay;
    }

    @Override
    public Animator setDuration(long duration)
    {
        this.duration = duration;
        return this;
    }

    @Override
    public long getDuration()
    {
        return duration;
    }

    @Override
    public void setInterpolator(TimeInterpolator value) { }

    @Override
    public boolean isRunning()
    {
        return mRunning;
    }

    @Override
    public void setTarget(Object target)
    {
        super.setTarget(target);
        mTarget = (View) target;
    }

    @Override
    public void onAnimationStart(Animator animation)
    {
        for (AnimatorListener listener : mListeners)
        {
            listener.onAnimationStart(animation);
        }
        mRunning = true;
    }

    @Override
    public void onAnimationEnd(Animator animation)
    {
        for (AnimatorListener listener : mListeners)
        {
            listener.onAnimationEnd(animation);
        }
        mRunning = false;
    }

    @Override
    public void onAnimationCancel(Animator animation)
    {
        for (AnimatorListener listener : mListeners)
        {
            listener.onAnimationCancel(animation);
        }
        mRunning = false;
    }

    @Override
    public void onAnimationRepeat(Animator animation)
    {
        for (AnimatorListener listener : mListeners)
        {
            listener.onAnimationRepeat(animation);
        }
    }

    @Override
    public void addListener(AnimatorListener listener)
    {
        super.addListener(listener);
        mListeners.add(listener);
    }

    @Override
    public void removeListener(AnimatorListener listener)
    {
        super.removeListener(listener);
        mListeners.remove(listener);
    }

    @Override
    public void removeAllListeners()
    {
        super.removeAllListeners();
        mListeners.clear();
    }

    @Override
    public void cancel()
    {
        if (mViewPropertyAnimator != null)
        {
            mViewPropertyAnimator.cancel();
        }
    }

    @Override
    public Animator clone()
    {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void end()
    {
        throw new RuntimeException("Not implemented");
    }

    @SuppressWarnings("deprecation")
    @Override
    public void start()
    {
        if (mFrame != null)
        {
            for (int i = 0; i < mFrame.getNumberOfFrames(); i++)
            {
                duration += mFrame.getDuration(i);
            }
        }

        mTarget.setBackgroundDrawable(mFrame);

        mViewPropertyAnimator = mTarget.animate();
        mViewPropertyAnimator.setDuration(duration);
        mViewPropertyAnimator.setListener(this);
        mViewPropertyAnimator.start();

        mFrame.start();
    }

    @Override
    public boolean isStarted()
    {
        return mViewPropertyAnimator != null;
    }

}
