package com.linknext.libgreatworks.intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.linknext.libgreatworks.R;

public class IntroductionActivity extends AppCompatActivity implements View.OnClickListener {
    public static void start( Context context ) {
        context.startActivity( new Intent( context, IntroductionActivity.class ) );
    }

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_intro );
        findViewById( R.id.bRetry ).setOnClickListener( this );
        if( savedInstanceState == null ) {
            replaceTutorialFragment();
        }
    }

    public void onClick( View v ) {
        int id = v.getId();
        if( id == R.id.bRetry ) {
            replaceTutorialFragment();
        }
    }

    public void replaceTutorialFragment() {
        getSupportFragmentManager().beginTransaction().replace( R.id.container, new IntroductionFragment() ).commit();
    }
}
