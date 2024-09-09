package com.DataConv.Statepop.Repository;

import com.DataConv.Statepop.Entity.Districtpop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepo extends JpaRepository<Districtpop,Long> {

}
