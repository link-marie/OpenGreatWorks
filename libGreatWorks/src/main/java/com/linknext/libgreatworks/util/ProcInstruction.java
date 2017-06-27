package com.linknext.libgreatworks.util;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.linknext.libgreatworks.Common;
import com.linknext.libgreatworks.ConstLib;
import com.linknext.libopen.MyPref;

public class ProcInstruction {

    private final Activity activity;
    private final int layoutContainer;
    private final int fragmentLayout;
    private final ConstLib.kPref doNotShow;


    private Fragment fragInstruction;

    public ProcInstruction( Activity activity, int instContainer, int fragmentLayout, ConstLib.kPref pref ) {
        this.activity = activity;
        this.layoutContainer = instContainer;
        this.fragmentLayout = fragmentLayout;
        this.doNotShow = pref;
    }

    public void show() {
        show( false );
    }

    public void show( boolean force ) {

        boolean doNotShow = MyPref.readDefaultBoolean( Common.getCtx(), this.doNotShow.name(), false );
        if( ( force == false ) && doNotShow ) {
            hide( true );
        }
        else {

            View rootView = activity.findViewById( layoutContainer );
            rootView.setVisibility( View.VISIBLE );

            Bundle arg = new Bundle();
            arg.putInt( ConstLib.kIntentKey.InstructionLayout.name(), fragmentLayout );
            arg.putSerializable( ConstLib.kIntentKey.InstructionPref.name(), this.doNotShow );
            fragInstruction = new InstructionFragment();
            fragInstruction.setArguments( arg );

            activity.getFragmentManager().beginTransaction().add( layoutContainer, fragInstruction ).commit();
            activity.findViewById( layoutContainer ).setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View v ) {
                    hide();
                }
            } );
        }
    }

    public void hide() {
        hide( true );
    }

    public void hide( boolean force ) {

        final View rootView = activity.findViewById( layoutContainer );

        final Animation out = new AlphaAnimation( 1.0f, 0.0f );
        out.setDuration( 1000 );
        out.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart( Animation animation ) {
            }

            @Override
            public void onAnimationEnd( Animation animation ) {
                hideCore();
            }

            @Override
            public void onAnimationRepeat( Animation animation ) {

            }
        } );

        rootView.startAnimation( out );
    }

    private void hideCore() {
        final View rootView = activity.findViewById( layoutContainer );
        if( fragInstruction != null ) {
            activity.getFragmentManager().beginTransaction().remove( fragInstruction ).commit();
        }
        rootView.setVisibility( View.GONE );
    }

    public boolean isVisible() {
        final View rootView = activity.findViewById( layoutContainer );
        int visibility = rootView.getVisibility();
        if( visibility == View.GONE ) {
            return false;
        }
        return true;
    }
}
