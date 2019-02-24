package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.FormalMember;
import com.xmu.makerspace.domain.Student;
import com.xmu.makerspace.model.seat.SimpleMemberOfSeat;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by status200 on 2017/9/1.
 */
public interface StudentRepository extends CrudRepository<Student,String> {

    Student findByAttendanceNumber(int attendance_number);

    int countByAttendanceNumber(int attendanceNumber);

    int countByStudentIdAndAttendanceNumberNotNull(String studentId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE student SET  attendance_number=?1 WHERE student_id=?2",nativeQuery = true)
    void changeAttendance_number(int attendance_number,String student_id);

    Student findAllByStudentId(String student_id);
    ArrayList<Student> findAll();
}
