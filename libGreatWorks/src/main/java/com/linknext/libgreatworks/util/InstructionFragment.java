package com.linknext.libgreatworks.util;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.linknext.libgreatworks.ConstLib;
import com.linknext.libgreatworks.R;
import com.linknext.libopen.MyPref;
import com.linknext.libopen.Utl;

import static com.linknext.libgreatworks.util.Utils.animFadeInOut;

/**
 */
public class InstructionFragment extends Fragment {

    private int layoutId;
    private ConstLib.kPref pref;

    public void onCreate( Bundle saveInstanceState ) {
        super.onCreate( saveInstanceState );

        Bundle arguments = getArguments();
        layoutId = arguments.getInt( ConstLib.kIntentKey.InstructionLayout.name() );
        pref = (ConstLib.kPref)arguments.get( ConstLib.kIntentKey.InstructionPref.name() );
    }

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

        View view = inflater.inflate( layoutId, container, false );

		/*
        CheckBox initialize
		 */
        CheckBox ckb = (CheckBox)view.findViewById( R.id.checkBox );
        boolean doNotShow = MyPref.readDefaultBoolean( getActivity(), pref.name(), false );
        ckb.setChecked( doNotShow );
        ckb.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged( CompoundButton buttonView, boolean isChecked ) {
                Utl.logDebug( "checked=" + isChecked );
                MyPref.saveDefaultBoolean( getActivity(), pref.name(), isChecked );
            }
        } );

		/*
		Instruction Animation
		 */
        TextView tvHide = (TextView)view.findViewById( R.id.textTouch );
        animFadeInOut( tvHide );

        return view;
    }


}
