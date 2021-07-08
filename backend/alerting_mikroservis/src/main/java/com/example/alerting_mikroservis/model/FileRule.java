package com.example.alerting_mikroservis.model;

import com.example.alerting_mikroservis.model.File;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.Queue;


@Entity
@DiscriminatorValue("File")
public class FileRule extends Rule {

    @Transient
    private static final int numberOfLogs = 100;

    @Transient
    private Queue<File> recentLogs;

    @Transient
    private int counter = 0;

    public FileRule(){}
    
    public FileRule(@JsonProperty("name") String name, @JsonProperty("service") String service, @JsonProperty("severity") String severity, @JsonProperty("limit") Double limit, @JsonProperty("timePeriod") Double timePeriod, @JsonProperty("timeUnit") String timeUnit, @JsonProperty("inARow") int inARow) {
        super(name, service, severity, null, null, null, inARow);
        recentLogs = new LinkedList<>();
        this.counter = 0;
    }

    @Override
    public String getDescription() {
        return "Prilikom procesiranja fileova mora biti manje od " + this.getInARow() + " pogrešnih logova zaredom.";
    }

    public boolean followsRule(File log){
        if(log.getLogs().contains("ERROR")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean sendAlert(File log){
        if(this.recentLogs.size() >= numberOfLogs){
            this.recentLogs.poll();
        }
        this.recentLogs.add(log);

        Queue<File> temp = this.recentLogs;
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
