package com.linknext.libgreatworks.intro;

import android.support.annotation.NonNull;

import com.cleveroad.slidingtutorial.Direction;
import com.cleveroad.slidingtutorial.PageSupportFragment;
import com.cleveroad.slidingtutorial.TransformItem;
import com.linknext.libgreatworks.R;

public class Page4Fragment extends PageSupportFragment {
    protected int getLayoutResId() {
        return R.layout.intro_page4;
    }

    @NonNull
    protected TransformItem[] getTransformItems() {

        return new TransformItem[]{
                TransformItem.create( R.id.imageView1, Direction.RIGHT_TO_LEFT, 0.20F ),
                TransformItem.create( R.id.imageView2, Direction.LEFT_TO_RIGHT, 0.06F ),
                TransformItem.create( R.id.imageView3, Direction.RIGHT_TO_LEFT, 0.08F ),
                TransformItem.create( R.id.imageView4, Direction.LEFT_TO_RIGHT, 0.10F ),
                TransformItem.create( R.id.imageView5, Direction.LEFT_TO_RIGHT, 0.12F ),
                TransformItem.create( R.id.imageView6, Direction.LEFT_TO_RIGHT, 0.15F ),
                TransformItem.create( R.id.imageView7, Direction.LEFT_TO_RIGHT, 0.18F ) };
    }
}
