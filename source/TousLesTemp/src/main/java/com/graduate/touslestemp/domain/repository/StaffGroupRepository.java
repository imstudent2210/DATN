package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.StaffGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffGroupRepository extends JpaRepository<StaffGroup, Long> {
    StaffGroup findStaffGroupByName(String staffGroup);
    @Query("select c from StaffGroup c where c.isActivated = true")
    List<StaffGroup> findStaffGroupActivated();
}
