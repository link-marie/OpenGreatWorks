package com.linknext.libgreatworks.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.linknext.libgreatworks.Common;
import com.linknext.libgreatworks.R;
import com.linknext.libopen.Utl;

/**
 * Category GridView + Clicked Image
 */
@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView {
    public MyImageView( Context context ) {
        super( context );
    }

    public MyImageView( Context context, @Nullable AttributeSet attrs ) {
        super( context, attrs );
    }

    public MyImageView( Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
    }

    public boolean onTouchEvent( MotionEvent event ) {

        int action = MotionEventCompat.getActionMasked( event );
        switch( action ) {
        case ( MotionEvent.ACTION_DOWN ):
            Utl.logDebug( "ActionDown" );
            actionSelected();
            break;
        case ( MotionEvent.ACTION_UP ):
            Utl.logDebug( "ActionUp" );
            actionUnSelected();
            break;
        case ( MotionEvent.ACTION_CANCEL ):
            Utl.logDebug( "ActionCancel" );
            actionUnSelected();
            break;
        case ( MotionEvent.ACTION_OUTSIDE ):
            Utl.logDebug( "ActionOutside" );
            actionUnSelected();
            break;
        default:
            break;
        }

        return super.onTouchEvent( event );
    }

    private void actionSelected() {
//		this.setImageResource( R.drawable.img_header1 );
        setImageAnimated( this.getContext(), this, R.drawable.img_work_selected1 );
    }

    private void actionUnSelected() {
//		this.setImageResource( R.drawable.img_work_animation );
        Common.kCategory cate = (Common.kCategory)this.getTag();
        Utl.logDebug( "Category=" + cate.name() );
        setImageAnimated( this.getContext(), this, cate.getIdImg() );
    }

    private void setImageAnimated( Context c, final ImageView v, final int newDrawabieId ) {
        final Animation anim_out = AnimationUtils.loadAnimation( c, android.R.anim.fade_out );
        final Animation anim_in = AnimationUtils.loadAnimation( c, android.R.anim.fade_in );
        anim_out.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart( Animation animation ) {
            }

            @Override
            public void onAnimationRepeat( Animation animation ) {
            }

            @Override
            public void onAnimationEnd( Animation animation ) {
                v.setImageResource( newDrawabieId );
                anim_in.setAnimationListener( new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart( Animation animation ) {
                    }

                    @Override
                    public void onAnimationRepeat( Animation animation ) {
                    }

                    @Override
                    public void onAnimationEnd( Animation animation ) {
                    }
                } );
                v.startAnimation( anim_in );
            }
        } );
        v.startAnimation( anim_out );
    }

}
