package com.DataConv.Statepop.Service;

import com.DataConv.Statepop.ControllerAdvice.InvalidDistNameEx;
import com.DataConv.Statepop.Entity.Districtpop;
import com.DataConv.Statepop.Repository.DistrictRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepo distrepo;

    public void savepop(Districtpop districtpop){

       boolean ans= districtpop.getDistname().matches("[a-zA-Z]+");
        if(ans) distrepo.save(districtpop);
        else throw new InvalidDistNameEx("District name must be a string.");
    }
    public long getPopulation(){
        List<Districtpop> dispop= distrepo.findAll();
        long totalpopulation=0;
        for(Districtpop districtpop: dispop) totalpopulation+=districtpop.getPopulation();
        return totalpopulation;
    }
}
