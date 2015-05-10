package com.sealionsoftware.lightlims.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SAMPLE", indexes = {
       @Index(name = "key", columnList = "job_identity, code", unique = true)
})
public class Sample extends MergableEntity {

    protected Sample(){}

    public Sample(Job job, String code) {
        this.job = job;
        this.code = code;
    }

    private String code;
    private Job job;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getDescription() {
        return (String) fields.get("description");
    }

    public void setDescription(String description) {
        fields.put("description", description);
    }

    @Transient
    public String getTransientField() {
        return (String) fields.get("transientField");
    }

    public void setTransientField(String transientField) {
        fields.put("transientField", transientField);
    }
}
