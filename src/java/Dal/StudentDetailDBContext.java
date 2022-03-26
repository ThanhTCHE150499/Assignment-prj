/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Bed;
import Model.Detail;
import Model.Dom;
import Model.Room;
import Model.Semester;
import Model.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class StudentDetailDBContext extends DBContext {

    public void addStudentDetail(Detail detail) {
        try {
            String sql = "INSERT INTO [Detail]\n"
                    + "           ([StudentID]\n"
                    + "           ,[RoomCode]\n"
                    + "           ,[Date_Booked]\n"
                    + "           ,[Price]\n"
                    + "           ,[BedNumber]\n"
                    + "           ,[SemesterId]\n"
                    + "           ,[Date_Checkout])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, detail.getStudent().getId());
            stm.setString(2, detail.getRoom().getRoom_code());
            stm.setDate(3, detail.getDate_booked());
            stm.setBigDecimal(4, detail.getPrice());
            stm.setInt(5, detail.getBed().getNumber());
            stm.setInt(6, detail.getSemester().getId());
            stm.setDate(7, detail.getDate_checkout());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Detail> getDetailsbyStuID(Student student) {
        ArrayList<Detail> details = new ArrayList<>();
        try {
            String sql = "select Detail.Id as id, StudentID, RoomCode, Date_Booked,Price,BedNumber, Semester.NumberOfSemester as semester,\n"
                    + "Semester.Year as year, Date_Checkout\n"
                    + "from Detail \n"
                    + "inner join Semester on Detail.SemesterId = Semester.Id\n"
                    + "where StudentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, student.getId());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Detail detail = new Detail();
                Room room = new Room();
                Dom dom = new Dom();
                Bed bed = new Bed();
                Semester semester = new Semester();
                room.setRoom_code(rs.getString("RoomCode"));
                student.setId(rs.getString("StudentID"));
                detail.setId(rs.getInt("id"));
                detail.setStudent(student);
                detail.setRoom(room);
                detail.setDate_booked(rs.getDate("Date_Booked"));
                detail.setPrice(rs.getBigDecimal("Price"));
                bed.setNumber(rs.getInt("BedNumber"));
                detail.setBed(bed);
                semester.setNumbersemester(rs.getInt("semester"));
                semester.setYear(rs.getInt("year"));
                detail.setSemester(semester);
                detail.setDate_checkout(rs.getDate("Date_Checkout"));
                details.add(detail);
                student.getStudent_details().add(detail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return details;
    }

    public Detail getDetailbyId(int id) {
        try {
            String sql = "select Date_Booked, Date_Checkout, Price, BedNumber, Semester.NumberOfSemester as semester, Semester.Year as year \n"
                    + "from Detail inner join Semester on Detail.SemesterId = Semester.Id\n"
                    + "where Detail.Id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Detail detail = new Detail();
                detail.setDate_booked(rs.getDate("Date_Booked"));
                detail.setDate_checkout(rs.getDate("Date_Checkout"));
                detail.setPrice(rs.getBigDecimal("Price"));
                Bed bed = new Bed();
                bed.setNumber(rs.getInt("BedNumber"));
                detail.setBed(bed);
                Semester semester = new Semester();
                semester.setNumbersemester(rs.getInt("semester"));
                semester.setYear(rs.getInt("year"));
                detail.setSemester(semester);
                return detail;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
