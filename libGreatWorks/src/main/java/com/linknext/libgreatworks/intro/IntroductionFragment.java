package com.linknext.libgreatworks.intro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.cleveroad.slidingtutorial.IndicatorOptions;
import com.cleveroad.slidingtutorial.OnTutorialPageChangeListener;
import com.cleveroad.slidingtutorial.TutorialOptions;
import com.cleveroad.slidingtutorial.TutorialPageProvider;
import com.cleveroad.slidingtutorial.TutorialSupportFragment;
import com.linknext.libgreatworks.ConstLib;
import com.linknext.libgreatworks.R;
import com.linknext.libopen.MyPref;
import com.linknext.libopen.Utl;

public class IntroductionFragment extends TutorialSupportFragment implements OnTutorialPageChangeListener {

	private static final int kPagesTotal = 8;
	private static final int kPagesActual = 4;

    private final View.OnClickListener mOnSkipClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        MyPref.saveDefaultBoolean( getActivity(), ConstLib.kPref.InitialInstruction.name(), true );
        getActivity().finish();
        }
    };

	private final TutorialPageProvider<Fragment> mTutorialPageProvider = new TutorialPageProvider<Fragment>() {
		@NonNull
		@Override
		public Fragment providePage(int position) {
            position %= kPagesActual;
            switch (position) {
            case 0: return new Page1Fragment();
            case 1: return new Page2Fragment();
            case 2: return new Page3Fragment();
            case 3: return new Page4Fragment();
            default: {
                throw new IllegalArgumentException("Unknown position: " + position);
            }
			}
		}
	};

    private int[] pagesColors;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if( this.pagesColors == null ) {
			this.pagesColors = new int[] {
                ContextCompat.getColor(getContext(), R.color.introPage1)
                , ContextCompat.getColor(getContext(), R.color.introPage2)
                , ContextCompat.getColor(getContext(), R.color.introPage3)
                , ContextCompat.getColor(getContext(), R.color.introPage4)
                , ContextCompat.getColor(getContext(), R.color.introPage5)
                , ContextCompat.getColor(getContext(), R.color.introPage6)
                , ContextCompat.getColor(getContext(), R.color.introPage7)
                , ContextCompat.getColor(getContext(), R.color.introPage8)
		    };
        }
        addOnTutorialPageChangeListener( this );
    }

    protected TutorialOptions provideTutorialOptions() {
		return newTutorialOptionsBuilder( getContext())
                .setUseInfiniteScroll(true)
                .setPagesColors(this.pagesColors)
                .setPagesCount( kPagesTotal )
                .setTutorialPageProvider( mTutorialPageProvider)
                .setIndicatorOptions(
                    IndicatorOptions.newBuilder(getContext())
                    .setElementSizeRes(R.dimen.intro_indicator_size)
                    .setElementSpacingRes(R.dimen.intro_indicator_spacing)
                    .setElementColorRes( android.R.color.darker_gray)
                    .setSelectedElementColor( Color.LTGRAY)
                    .setRenderer(RhombusRenderer.create()).build())
                .setOnSkipClickListener(this.mOnSkipClickListener)
                .build();
	}

    protected int getLayoutResId()
  {
    return R.layout.intro_layout;
  }

    public void onPageChanged( int position ) {
        Utl.logDebug( "onPageChanged: position = " + position );
        if( position == TutorialSupportFragment.EMPTY_FRAGMENT_POSITION ) {
            Utl.logDebug( "onPageChanged: Empty fragment is visible" );
        }
    }
}
