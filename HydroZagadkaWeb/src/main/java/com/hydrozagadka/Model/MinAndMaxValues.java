package com.hydrozagadka.Model;

public class MinAndMaxValues {

    private Double max;
    private Double min;

    public MinAndMaxValues(Double max, Double min) {
        this.max = max;
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MinAndMaxValues{");
        sb.append("max=").append(max);
        sb.append(", min=").append(min);
        sb.append('}');
        return sb.toString();
    }
}
