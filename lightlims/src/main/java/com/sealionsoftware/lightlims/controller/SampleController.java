package com.sealionsoftware.lightlims.controller;

import com.sealionsoftware.lightlims.domain.Job;
import com.sealionsoftware.lightlims.domain.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/job/{jobCode}/sample")
public class SampleController {

    @Autowired
    private JobController jobController;
    @PersistenceContext
    private EntityManager store;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Sample> read(){
        return store
                .createQuery("SELECT s FROM Sample s", Sample.class)
                .getResultList();
    }

    @RequestMapping(value = "{code}", method = RequestMethod.GET)
    public @ResponseBody Sample read(@PathVariable String jobCode, @PathVariable String code){
        return store
                .createQuery("SELECT s FROM Sample s WHERE job.code = :jobCode AND code = :code", Sample.class)
                .setParameter("jobCode", jobCode)
                .setParameter("code", code)
                .getSingleResult();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void create(@PathVariable String jobCode, @RequestBody Sample sample){
        Job job = jobController.read(jobCode);
        sample.setJob(job);
        store.persist(sample);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.POST)
    public void update(@PathVariable String jobCode, @PathVariable String code, @RequestBody Sample sample){
        Sample original = read(jobCode, code);
        original.merge(sample);
        store.merge(original);
    }

    @RequestMapping(value = "{code}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String jobCode, @PathVariable String code){
        Sample original = read(jobCode, code);
        store.remove(original);
    }
}
