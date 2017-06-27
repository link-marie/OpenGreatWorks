package com.linknext.libgreatworks.intro;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;

import com.cleveroad.slidingtutorial.Renderer;

public class RhombusRenderer implements Renderer {
    private static final float ANGLE_45 = 45.0F;

    public static RhombusRenderer create() {
        return new RhombusRenderer();
    }

    public void draw( @NonNull Canvas canvas, @NonNull RectF elementBounds, @NonNull Paint paint, boolean isActive ) {
        canvas.save();
        canvas.rotate( 45.0F, elementBounds.centerX(), elementBounds.centerY() );
        canvas.drawRect( elementBounds, paint );
        canvas.restore();
    }
}
