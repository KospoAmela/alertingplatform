package com.example.alerting_mikroservis.service;

import com.example.alerting_mikroservis.dao.RulesDao;
import com.example.alerting_mikroservis.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    private final RulesDao rulesDao;

    @Autowired
    public RuleService(@Qualifier("rules") RulesDao rulesDao) {
        this.rulesDao = rulesDao;
    }

    public void addRule(Rule rule){
        rulesDao.addRule(rule);
    }

    public Rule getCPURule(){
        return rulesDao.getCPURule();
    }

    public List<Rule> getAllRules(){
        return rulesDao.getAllRules();
    }
}
