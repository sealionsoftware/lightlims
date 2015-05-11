package com.sealionsoftware.lightlims.domain;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "JOB", indexes = {
        @Index(name = "key", columnList = "code", unique = true)
})
public class Job extends MergableEntity<Job> {

    protected Job(){}

    public Job(String code) {
        this.code = code;
    }

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
}
