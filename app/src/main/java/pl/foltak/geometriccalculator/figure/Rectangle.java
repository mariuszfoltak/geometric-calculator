package pl.foltak.geometriccalculator.figure;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import pl.foltak.geometriccalculator.Field;
import pl.foltak.geometriccalculator.R;

/**
 * Created by mfoltak on 9/27/14.
 */
public class Rectangle implements Figure {
    @Getter
    private Double sideA;
    @Getter
    private Double sideB;
    @Getter
    private Double diagonal;
    @Getter
    private Double area;
    @Getter
    private Double perimeter;
    @Getter
    private Drawable figureImage;
    @Getter
    private List<Field> fields;

    public Rectangle (Resources resources) {
        figureImage = resources.getDrawable(R.drawable.figure_rectangle);
        fields = initFields(resources);
    }

    private List<Field> initFields(Resources resources) {
        List<Field> fields = new LinkedList<Field>();
        fields.add(new Field(resources.getString(R.string.figure_rectangle_sideA), true, "sideA") {
            @Override
            public void setValue(Double value) {
                setSideA(value);
            }

            @Override
            public Double getValue() {
                return sideA;
            }
        });
        fields.add(new Field(resources.getString(R.string.sideB), true, "sideB") {
            @Override
            public void setValue(Double value) {
                setSideB(value);
            }

            @Override
            public Double getValue() {
                return sideB;
            }
        });
        fields.add(new Field(resources.getString(R.string.diagonal), true, "diagonal") {
            @Override
            public Double getValue() {
                return diagonal;
            }

            @Override
            public void setValue(Double value) {
                setDiagonal(value);
            }

        });
        fields.add(new Field(resources.getString(R.string.area), false, "area") {
            @Override
            public Double getValue() {
                return area;
            }
        });
        fields.add(new Field(resources.getString(R.string.perimeter), false, "perimeter") {
            @Override
            public Double getValue() {
                return perimeter;
            }
        });

        return fields;
    }

    private void setSideA(Double sideA) {
        this.sideA = sideA;
        if (sideA != null) {
            if (sideB != null) {
                diagonal = computeDiagonal(sideA, sideB);
                area = computeArea(sideA, sideB);
                perimeter = computePerimeter(sideA, sideB);
            } else if (diagonal != null) {
                sideB = computeSide(sideA, diagonal);
                area = computeArea(sideA, sideB);
                perimeter = computePerimeter(sideA, sideB);
            }
        }
    }

    private void setSideB(Double sideB) {
        this.sideB = sideB;
        if (sideB != null) {
            if (sideA != null) {
                diagonal = computeDiagonal(sideA, sideB);
                area = computeArea(sideA, sideB);
                perimeter = computePerimeter(sideA, sideB);
            } else if (diagonal != null) {
                sideA = computeSide(sideB, diagonal);
                area = computeArea(sideA, sideB);
                perimeter = computePerimeter(sideA, sideB);
            }
        }
    }

    private void setDiagonal(Double diagonal) {
        this.diagonal = diagonal;
        if (diagonal != null) {
            if (sideA != null) {
                sideB = computeSide(sideA, diagonal);
                area = computeArea(sideA, sideB);
                perimeter = computePerimeter(sideA, sideB);
            } else if (sideB != null) {
                sideA = computeSide(sideB, diagonal);
                area = computeArea(sideA, sideB);
                perimeter = computePerimeter(sideA, sideB);
            }
        }
    }

    Double computeDiagonal(@NonNull Double sideA, @NonNull Double sideB) {
        return Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
    }

    Double computeArea(@NonNull Double sideA, @NonNull Double sideB) {
        return sideA * sideB;
    }

    Double computePerimeter(@NonNull Double sideA, @NonNull Double sideB) {
        return 2 * (sideA + sideB);
    }

    Double computeSide(@NonNull Double side, @NonNull Double diagonal) {
        return Math.sqrt(Math.pow(diagonal, 2) - Math.pow(side, 2));
    }
}
