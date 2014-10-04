package pl.foltak.geometriccalculator;

import lombok.Getter;

/**
 * Created by mfoltak on 9/27/14.
 */
public class Field {
    @Getter private final String label;
    @Getter private final boolean editable;
    @Getter private final String name;
    private int value;

    public Field(String label, boolean editable, String name) {
        this.label = label;
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
