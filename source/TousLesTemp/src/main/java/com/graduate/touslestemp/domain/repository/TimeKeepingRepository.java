package com.graduate.touslestemp.domain.repository;

import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeKeepingRepository extends JpaRepository<TimeKeeping, Long> {

    // Total Salary
    @Query("select (sum(t.numOfShift * 4 * s.basicSalary)) from TimeKeeping t join Salary s where t.salary.id = s.id and t.Staff.id = :id")
    int shiftSalary(@Param("id") Long id);

    //Month Salary
    @Query("select (sum(t.numOfShift * 4 * s.basicSalary)) from TimeKeeping t join Salary s where t.salary.id = s.id and t.Staff.id = :id and t.month =:month")
    int monthSalary(@Param("id") Long id, @Param("month") Long month);

    @Query("select t from TimeKeeping t join Salary s where t.salary.id = s.id and t.month = :id")
    List<TimeKeeping> allTimeKeepingPerMonth(@Param("id") Long id);

    @Query("select t from TimeKeeping t join Salary s where t.salary.id = s.id and t.Staff.id = :id")
    List<TimeKeeping> allTimeKeepingPerStaff(@Param("id") Long id);

    @Query("select t from TimeKeeping t where MONTH(t.createdDate) = :month")
    List<TimeKeeping> findTimeKeepingByMonth(@Param("month") Long month);

    @Query("select s from TimeKeeping t join Staff s where t.Staff.id = s.id and t.month = :month")
    List<Staff> allStaffHaveTimeKeepingPerMonth(@Param("month") Long month);
}
