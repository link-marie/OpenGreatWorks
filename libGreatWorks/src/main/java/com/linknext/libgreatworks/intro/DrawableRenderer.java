package com.linknext.libgreatworks.intro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.cleveroad.slidingtutorial.Renderer;
import com.linknext.libgreatworks.R;

public class DrawableRenderer implements Renderer {
    private Drawable mDrawableActive;
    private Drawable mDrawable;

    private DrawableRenderer( @NonNull Context context ) {
        this.mDrawableActive = ContextCompat.getDrawable( context, R.drawable.vec_checkbox_fill_circle_outline );
        this.mDrawable = ContextCompat.getDrawable( context, R.drawable.vec_checkbox_blank_circle_outline );
    }

    public static DrawableRenderer create( @NonNull Context context ) {
        return new DrawableRenderer( context );
    }

    public void draw( @NonNull Canvas canvas, @NonNull RectF elementBounds, @NonNull Paint paint, boolean isActive ) {
        Drawable drawable = isActive ? this.mDrawableActive : this.mDrawable;
        drawable.setBounds( (int)elementBounds.left, (int)elementBounds.top, (int)elementBounds.right, (int)elementBounds.bottom );
        drawable.draw( canvas );
    }
}
