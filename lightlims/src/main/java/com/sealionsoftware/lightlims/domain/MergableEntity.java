package com.sealionsoftware.lightlims.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import java.util.HashMap;
import java.util.Map;

@Access(AccessType.PROPERTY)
@MappedSuperclass
public class MergableEntity {

    protected Map<String, Object> fields = new HashMap<>();

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Access(AccessType.FIELD)
    @JsonIgnore
    private int identity;

    public void merge(MergableEntity partialUpdate){
        fields.putAll(partialUpdate.fields);
    }
}
