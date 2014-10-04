package pl.foltak.geometriccalculator.figure;

import android.graphics.drawable.Drawable;

import java.util.List;

import pl.foltak.geometriccalculator.Field;

/**
 * Created by mfoltak on 9/27/14.
 */
public interface Figure {
    List<Field> getFields();
    Drawable getFigureImage();
}
