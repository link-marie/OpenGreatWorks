package com.linknext.libgreatworks;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import com.github.ksoichiro.android.observablescrollview.ObservableGridView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.linknext.libopen.Utl;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

public class MainListFragment extends Fragment {

    private static final int INITIAL_DELAY_MILLIS = 300;
    private ObservableGridView mScrollable;
    private Toolbar mToolbar;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        Utl.logDebug( "start.." );
        View rootView = initView( inflater, container );
        mToolbar = (Toolbar)this.getActivity().findViewById( R.id.toolbar );
        Utl.logDebug( "done." );
        return rootView;
    }

    /**
     * ArrayAdapter
     */
    private View initView( LayoutInflater inflater, ViewGroup container ) {

        View rootView = inflater.inflate( R.layout.fragment_main_list, container, false );

        initGrid( rootView );

        initFab( rootView, mScrollable );

        return rootView;
    }

    private void initGrid( View rootView ) {

        mScrollable = (ObservableGridView)rootView.findViewById( R.id.activity_gridview_gv );
        mScrollable.addScrollViewCallbacks( new MyScrollViewCallbacks() );

        DisplayMetrics displayMetrics = Utl.getDisplayMetrics( getActivity() );
        int wPx = displayMetrics.widthPixels;
        int hPx = displayMetrics.heightPixels;
        int wDp = (int)Utl.px2dp( this.getActivity(), wPx );
        int hDp = (int)Utl.px2dp( this.getActivity(), hPx );
        int val = Math.min( wDp, hDp );

        int div = 1;
        if( wDp <= 380 ) {
            div = 1;
        }
        else if( wDp <= 600 ) {
            div = 2;
        }
        else if( wDp <= 900 ) {
            div = 3;
        }
        else {
            div = 4;
        }
        mScrollable.setNumColumns( div );

        Utl.logDebug( "create adapter" );
        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter( new MainListAdapter( getActivity() ) );
        swingBottomInAnimationAdapter.setAbsListView( mScrollable );
        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis( INITIAL_DELAY_MILLIS );
        mScrollable.setAdapter( swingBottomInAnimationAdapter );

        String msg = "dimPx=" + wPx + "," + hPx + " dimDp=" + wDp + "," + hDp + " div=" + div;
        Utl.logDebug( msg );

    }

    private void initFab( View rootView, AbsListView listView ) {
    }

    private void actionFabClick() {
    }

    public void refresh() {
    }

    protected int getScreenHeight() {
        return getActivity().findViewById( android.R.id.content ).getHeight();
    }

    class MyScrollViewCallbacks implements ObservableScrollViewCallbacks {

        @Override
        public void onScrollChanged( int scrollY, boolean firstScroll, boolean dragging ) {
        }

        @Override
        public void onDownMotionEvent() {
        }

        @Override
        public void onUpOrCancelMotionEvent( ScrollState scrollState ) {
            Utl.logDebug();
            if( scrollState == ScrollState.UP ) {
                if( toolbarIsShown() ) {
                    hideToolbar();
                }
            }
            else if( scrollState == ScrollState.DOWN ) {
                if( toolbarIsHidden() ) {
                    showToolbar();
                }
            }

        }

        private boolean toolbarIsShown() {
            return ViewHelper.getTranslationY( mToolbar ) == 0;
        }

        private boolean toolbarIsHidden() {
            return ViewHelper.getTranslationY( mToolbar ) == -mToolbar.getHeight();
        }

        private void showToolbar() {
            moveToolbar( 0 );
        }

        private void hideToolbar() {
            moveToolbar( -mToolbar.getHeight() );
        }

        private void moveToolbar( float toTranslationY ) {
            if( ViewHelper.getTranslationY( mToolbar ) == toTranslationY ) {
                return;
            }
            ValueAnimator animator = ValueAnimator.ofFloat( ViewHelper.getTranslationY( mToolbar ), toTranslationY ).setDuration( 200 );
            animator.addUpdateListener( new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate( ValueAnimator animation ) {
                    float translationY = (float)animation.getAnimatedValue();
                    ViewHelper.setTranslationY( mToolbar, translationY );
                    ViewHelper.setTranslationY( (View)mScrollable, translationY );
                    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams)( (View)mScrollable ).getLayoutParams();
                    lp.height = (int)-translationY + getScreenHeight() - lp.topMargin;
                    ( (View)mScrollable ).requestLayout();
                }
            } );
            animator.start();
        }

    }
}
