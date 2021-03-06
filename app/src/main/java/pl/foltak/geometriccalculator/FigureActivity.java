package pl.foltak.geometriccalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import pl.foltak.geometriccalculator.figure.Figure;
import pl.foltak.geometriccalculator.figure.Rectangle;


public class FigureActivity extends ActionBarActivity {

    private boolean isComputingValues = false;
    private Map<String, EditText> fields = new HashMap<String, EditText>();
    private Figure figure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        figure = new Rectangle(getResources());
        RelativeLayout layout = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_figure, null);

        setFigureImage(layout);

        initFields((TableLayout) layout.findViewById(R.id.fieldTableLayout), figure);

        setContentView(layout);
    }

    private void setFigureImage(RelativeLayout layout) {
        ImageView imageView = (ImageView) layout.findViewById(R.id.figureImageView);
        imageView.setImageDrawable(figure.getFigureImage());
    }

    public void initFields(final ViewGroup view, final Figure figure) {
        for (final Field field : figure.getFields()) {
            addFieldToLayout(view, field);
        }
    }

    private void addFieldToLayout(ViewGroup view, Field field) {
        ViewGroup layout = inflateLayout();
        prepareLabel(field, layout);
        prepareEditable(field, layout);
        view.addView(layout);
    }

    private ViewGroup inflateLayout() {
        LayoutInflater inflater = getLayoutInflater();
        return (ViewGroup) inflater.inflate(R.layout.field, null);
    }

    private void prepareEditable(Field field, ViewGroup layout) {
        EditText editText = (EditText) layout.findViewWithTag("editText");
        editText.setEnabled(field.isEditable());
        editText.addTextChangedListener(new FieldTextWatcher(field));

        fields.put(field.getName(), editText);
    }

    private void prepareLabel(Field field, ViewGroup layout) {
        TextView textView = (TextView) layout.findViewWithTag("textView");
        textView.setText(field.getLabel());
    }

    private String formatDouble(Double value) {
        return value == null ? "" :
                new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.US)).format(value);
    }

    private Double parseDouble(String value) {
        return StringUtils.isBlank(value) ? null : NumberUtils.toDouble(value);
    }

    private class FieldTextWatcher implements TextWatcher {

        private final Field field;

        private FieldTextWatcher(Field field) {
            this.field = field;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (isComputingValues) return;
            isComputingValues = true;
            field.setValue(parseDouble(charSequence.toString()));
            for (Field otherField : figure.getFields()) {
                if (otherField.getName() != field.getName()) {
                    EditText editText = fields.get(otherField.getName());
                    editText.setText(formatDouble(otherField.getValue()));
                }
            }
            isComputingValues = false;
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}
