package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Long> {

    @Query("select (t.numOfShift * 4 * s.basicSalary) from TimeKeeping t join Salary s where t.salary.id = s.id and t.id = :id" )
    int shiftSalary(@Param("id") Long id);

    @Query("select t from TimeKeeping t join Salary s where t.salary.id = s.id and t.month = :id" )
    List<TimeKeeping> allTimeKeepingPerMonth(@Param("id") Long id);
}
