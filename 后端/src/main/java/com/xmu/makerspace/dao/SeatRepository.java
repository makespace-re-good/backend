package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.Seat;
import com.xmu.makerspace.domain.SeatPK;
import com.xmu.makerspace.model.seat.SimpleMemberOfSeat;
import com.xmu.makerspace.model.SimpleSeatInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by status200 on 2017/9/3.
 */
public interface SeatRepository extends CrudRepository<Seat, SeatPK> {

    Seat findByStudentId(String studentId);

    List<Seat> findAll();

    int countByStudentId(String studentId);

    // 查找所有房间号
    // 注意这里的查询是jpql,而不是一般的sql
    @Query("SELECT DISTINCT roomNo FROM Seat")
    List<Integer> findDistinctRoomNo();

    int countByRoomNo(int roomNo);

    int countByRoomNoAndStudentIdNotNull(int roomNo);

    List<Seat> findByRoomNo(int roomNo);

    // 根据学号找出一个学生所属团队的简单信息
    // 注意这里 new 关键字后边的类名必须为完全限定名
    @Query("select new com.xmu.makerspace.model.seat.SimpleMemberOfSeat(m.studentId,m.studentName,t.teamId,t.teamName) " +
            "from FormalMember m,FormalTeam t " +
            "where m.studentId = :studentId and m.teamId = t.teamId")
    List<SimpleMemberOfSeat> findSimpleMemberOfSeatByStudentId(@Param("studentId") String studentId);

    @Query("select new com.xmu.makerspace.model.SimpleSeatInfo(roomNo,seatNo) from Seat where studentId = :studentId")
    SimpleSeatInfo findSimpleSeatInfoByStudentId(@Param("studentId") String studentId);

    @Query(value = "UPDATE seat SET student_id=NULL AND team_id=NULL WHERE student_id=?1 AND team_id=?2", nativeQuery = true)
    @Modifying
    @Transactional
    void removeRecord(String student_id, int team_id);


    @Query(value = "UPDATE seat SET seat.student_id=243 AND seat.team_id=123 WHERE seat.room_no=?1 AND seat.seat_no=?2", nativeQuery = true)
    @Modifying
    @Transactional
    void changeSeat(int room_no, int seat_no, String student_id, int team_id);

    @Modifying
    @Transactional
    void deleteAllByStudentIdAndTeamId(String student_id, int team_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE seat SET student_id=?1 AND team_id=?2 WHERE  room_no=?3 AND seat_no=?4", nativeQuery = true)
    void chooseSeat(String student_id, int team_id, int room_no, int seat_no);

    ArrayList<Seat> findAllByStudentIdIsNull();

    Seat findAllByStudentIdAndTeamId(String student_id, int team_id);

    Seat findAllByRoomNoAndSeatNo(int room_no, int seat_no);

    /**
     * after delete team
     * the seat of member will be delete
     * delete seat
     **/
    @Query(value = "UPDATE seat set student_id=?1,team_id=?2 where team_id=?3", nativeQuery = true)
    @Modifying
    @Transactional
    int deleteSeat(String student_id, Integer setTeam_id, Integer studentTeam_id);
}