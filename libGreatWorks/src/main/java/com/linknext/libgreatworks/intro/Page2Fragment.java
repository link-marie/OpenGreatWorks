package com.linknext.libgreatworks.intro;

import android.support.annotation.NonNull;

import com.cleveroad.slidingtutorial.Direction;
import com.cleveroad.slidingtutorial.PageSupportFragment;
import com.cleveroad.slidingtutorial.TransformItem;
import com.linknext.libgreatworks.R;

public class Page2Fragment extends PageSupportFragment {
    protected int getLayoutResId() {
        return R.layout.intro_page2;
    }

    @NonNull
    protected TransformItem[] getTransformItems() {
        return new TransformItem[]{
                TransformItem.create( R.id.imageView1, Direction.RIGHT_TO_LEFT, 0.2F ),
                TransformItem.create( R.id.imageView2, Direction.LEFT_TO_RIGHT, 0.6F ),
                TransformItem.create( R.id.imageView3, Direction.RIGHT_TO_LEFT, 0.08F ),
                TransformItem.create( R.id.imageView4, Direction.LEFT_TO_RIGHT, 0.1F ),
                TransformItem.create( R.id.imageView5, Direction.LEFT_TO_RIGHT, 0.03F ),
                TransformItem.create( R.id.imageView6, Direction.LEFT_TO_RIGHT, 0.09F ),
                TransformItem.create( R.id.imageView7, Direction.LEFT_TO_RIGHT, 0.14F ),
                TransformItem.create( R.id.imageView8, Direction.LEFT_TO_RIGHT, 0.15F ) };
    }
}
