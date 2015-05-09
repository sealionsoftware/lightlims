package com.sealionsoftware.lightlims.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "JOB", indexes = {
        @Index(name = "key", columnList = "code", unique = true)
})
public class Job {

    private Map<String, Object> fields = new HashMap<>();

    protected Job(){}

    public Job(String code) {
        this.code = code;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @JsonIgnore
    private int identity;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public void merge(Job partialUpdate){
        fields.putAll(partialUpdate.fields);
    }
}
