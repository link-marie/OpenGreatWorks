package com.linknext.libgreatworks.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.linknext.libgreatworks.Common;
import com.linknext.libgreatworks.ConstLib;
import com.linknext.libopen.MyPref;
import com.linknext.libopen.Utl;

public class Utils {

    public static void actionUpdateInfo( Activity activity ) {
    }

    public static void reqOrientation( Activity activity ) {
        String key = ConstLib.kPref.ScreenLock.name();
        String sval = MyPref.readDefaultString( activity, key, Integer.toString( ConstLib.kOrientation.Landscape.ordinal() ) );
        int iVal = Integer.parseInt( sval );

        ConstLib.kOrientation orientation = ConstLib.kOrientation.getEnum( iVal );
        switch( orientation ) {
        case Landscape:
            activity.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
            break;
        case Portrait:
            activity.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
            break;
        default:
            activity.setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED );
            break;
        }
    }

    public static boolean isShowUpdateInfo( Context ctx ) {
        String keyCode = ConstLib.kPref.AppVersionCode.name();
        String keyNotShow = ConstLib.kPref.UpdateInfoDoNotShow.name();

        int minVersion = 1;
        int lVersionCodeSaved = MyPref.readDefaultInt( ctx, keyCode, minVersion );
        int lVersionCodeApp = Utl.getVersionCode( ctx );

        boolean toShow = false;
        if( lVersionCodeApp > minVersion ) {
            if( lVersionCodeApp > lVersionCodeSaved ) {
                MyPref.saveDefaultInt( ctx, keyCode, lVersionCodeApp );

                MyPref.saveDefaultBoolean( ctx, keyNotShow, false );

                toShow = true;
            }
            else {
                boolean lDoNotShow = MyPref.readDefaultBoolean( ctx, keyNotShow, false );
                if( !lDoNotShow ) {
                    toShow = true;
                }
            }
        }
        Utl.logDebug( "Version=" + lVersionCodeApp + " Saved=" + lVersionCodeSaved + " show=" + toShow );

        return toShow;
    }

    static public Common.kSort getSortType() {
        Common.kSort sortType = Common.kSort.getEnum( MyPref.readDefaultInt( Common.getCtx(), ConstLib.kPref.SortType.name(), Common.kSort.Default.ordinal() ) );
        return sortType;
    }

    static public void animFadeInOut( final View view ) {

        final Animation in = new AlphaAnimation( 0.0f, 1.0f );
        in.setDuration( 1000 );
        final Animation out = new AlphaAnimation( 1.0f, 0.0f );
        out.setDuration( 1000 );

        in.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart( Animation animation ) {

            }

            @Override
            public void onAnimationEnd( Animation animation ) {
                view.startAnimation( out );
            }

            @Override
            public void onAnimationRepeat( Animation animation ) {

            }
        } );

        out.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart( Animation animation ) {
            }

            @Override
            public void onAnimationEnd( Animation animation ) {
                view.startAnimation( in );
            }

            @Override
            public void onAnimationRepeat( Animation animation ) {

            }
        } );

        view.startAnimation( out );
    }

}
