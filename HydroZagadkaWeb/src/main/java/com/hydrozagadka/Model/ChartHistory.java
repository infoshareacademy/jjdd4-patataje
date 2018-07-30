package com.hydrozagadka.Model;

public class ChartHistory {

    private Double waterDeep;

    private Double flow;

    private Double temperature;

    public ChartHistory(Double waterDeep, Double flow, Double temperature) {
        this.waterDeep = waterDeep;
        this.flow = flow;
        this.temperature = temperature;
    }

    public Double getWaterDeep() {
        return waterDeep;
    }

    public Double getFlow() {
        return flow;
    }

    public Double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChartHistory{");
        sb.append("waterDeep=").append(waterDeep);
        sb.append(", flow=").append(flow);
        sb.append(", temperature=").append(temperature);
        sb.append('}');
        return sb.toString();
    }
}
