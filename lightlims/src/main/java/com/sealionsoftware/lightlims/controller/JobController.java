package com.sealionsoftware.lightlims.controller;

import com.sealionsoftware.lightlims.domain.Job;
import com.sealionsoftware.lightlims.domain.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/job")
public class JobController  {

    @PersistenceContext
    private EntityManager store;

    @RequestMapping(method = RequestMethod.GET)
    public List<Job> read(){
        return store
                .createQuery("SELECT j FROM Job j", Job.class)
                .getResultList();
    }

    @RequestMapping(value = "{code}", method = RequestMethod.GET)
    public Job read(@PathVariable String code){
        return store
                .createQuery("SELECT j FROM Job j WHERE code = :code", Job.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void create(@RequestBody Job job){
        store.persist(job);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.POST)
    public void update(@PathVariable String code, @RequestBody Job job){
        Job original = read(code);
        original.merge(job);
        store.merge(original);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String code){
        Job original = read(code);
        store.remove(original);

    }
}
