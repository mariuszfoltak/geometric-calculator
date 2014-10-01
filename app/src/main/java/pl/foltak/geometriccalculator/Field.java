package pl.foltak.geometriccalculator;

import lombok.Getter;

/**
 * Created by mfoltak on 9/27/14.
 */
public class Field {
    @Getter private final int resourceText;
    @Getter private final boolean editable;
    @Getter private final String name;
    private int value;

    public Field(int resourceText, boolean editable, String name) {
        this.resourceText = resourceText;
        this.editable = editable;
        this.name = name;
    }

    public void setValue(Double value) {
        throw new IllegalStateException();
    }

    public Double getValue() {
        throw new IllegalStateException();
    }
}
