package com.example.alerting_mikroservis.model;

import com.example.alerting_mikroservis.CPUMeasurement;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;


public class CPURule extends Rule {
    private static final int numberOfMeasurements = 50;
    private Queue<CPUMeasurement> recentMeasurements;
    private int counter;
    public CPURule(@JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("limit") Double limit, @JsonProperty("inARow")int inARow) {
        super(name, service, severity, limit, 0.0, "N/A", inARow);
        recentMeasurements = new LinkedList<>();
        this.counter = 0;
    }

    @Override
    public String getDescription() {
        return "CPU ne moze raditi na vise od " + this.getLimit() + "%";
    }

    public boolean followsRule(CPUMeasurement measurement){
        double average = 0;
        for(Double m : measurement.getMeasurement()){
            average+=m;
        }
        average /= measurement.getMeasurement().size();
        if(average < this.getLimit()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sendAlert(CPUMeasurement measurement){
        if(this.recentMeasurements.size() >= numberOfMeasurements){
            this.recentMeasurements.poll();
        }
        this.recentMeasurements.add(measurement);

        Queue<CPUMeasurement> temp = this.recentMeasurements;
        while(!temp.isEmpty()){
            if(followsRule(temp.poll())){
                counter = 0;
                return false;
            }
            counter++;
        }
        if(counter >= this.getInARow()){
            return true;
        }
        return false;
    }
}
