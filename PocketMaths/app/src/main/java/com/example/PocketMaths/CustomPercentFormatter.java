package com.example.PocketMaths;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

// Extending ValueFormatter. Not extending the Percentage Formatter directly since it may
// be changed in the future like it has been changed before.
public class CustomPercentFormatter extends ValueFormatter {

    public DecimalFormat mFormat;
    private PieChart pieChart;

    public CustomPercentFormatter() {
        mFormat = new DecimalFormat("###,###,##0.0");
    }

    public CustomPercentFormatter(PieChart pieChart) {
        this();
        this.pieChart = pieChart;
    }

    @Override
    public String getFormattedValue(float value) {
        // Only create value label if the value is not 0:
        if (value != 0) {
            return mFormat.format(value) + " %";
        }
        return "";
    }

    @Override
    public String getPieLabel(float value, PieEntry pieEntry) {
        if (pieChart != null && pieChart.isUsePercentValuesEnabled()) {
            // Converted to percent
            return getFormattedValue(value);
        } else {
            // raw value, skip percent sign
            return mFormat.format(value);
        }
    }
}

