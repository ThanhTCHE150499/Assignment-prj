/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Bed;
import Model.BookBed;
import Model.Request;
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
public class BookBedRequestDBContext extends DBContext {

    public ArrayList<BookBed> bookbedsbyStudentID(String stuid) {
        ArrayList<BookBed> bookbeds = new ArrayList<>();
        try {
            String sql = "select StudentId, Roomcode,Booked_Date,Booked_Checkout,Bednumber,Status,Semester.NumberOfSemester as semester,\n"
                    + "Semester.Year as year\n"
                    + "from BookBedRequest inner join Semester on BookBedRequest.SemesterId = Semester.Id\n"
                    + "where StudentId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, stuid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BookBed bookbed = new BookBed();
                Semester semester = new Semester();
                Room room = new Room();
                Bed bed = new Bed();
                room.setRoom_code(rs.getString("Roomcode"));
                bookbed.setRoom(room);
                bed.setNumber(rs.getInt("Bednumber"));
                bookbed.setBed(bed);
                bookbed.setBooked_date(rs.getDate("Booked_Date"));
                bookbed.setDate_checkout(rs.getDate("Booked_Checkout"));
                bookbed.setStatus(rs.getInt("Status"));
                Student student = new Student();
                student.setId(rs.getString("StudentId"));
                semester.setNumbersemester(rs.getInt("semester"));
                semester.setYear(rs.getInt("year"));
                bookbed.setSemester(semester);
                bookbed.setStudent(student);
                bookbeds.add(bookbed);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookbeds;
    }
    
    public BookBed getBookBedbyStudentandSemester(Student student, Semester semester){
        try {
            String sql = "select * from BookBedRequest where StudentId = ? and SemesterId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, student.getId());
            stm.setInt(2, semester.getId());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                BookBed bookbed = new BookBed();
                Bed bed = new Bed();
                Room room = new Room();
                bed.setNumber(rs.getInt("Bednumber"));
                room.setRoom_code(rs.getString("Roomcode"));
                bookbed.setBooked_date(rs.getDate("Booked_Date"));
                bookbed.setDate_checkout(rs.getDate("Booked_Checkout"));
                bookbed.setSemester(semester);
                bookbed.setStudent(student);
                bookbed.setStatus(rs.getInt("Status"));
                bookbed.setRoom(room);
                bookbed.setBed(bed);
                return bookbed;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insert(BookBed bookbed) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO[BookBedRequest]\n"
                    + "           ([StudentId]\n"
                    + "           ,[Roomcode]\n"
                    + "           ,[Booked_Date]\n"
                    + "           ,[Booked_Checkout]\n"
                    + "           ,[Bednumber]\n"
                    + "           ,[Status]\n"
                    + "           ,[SemesterId])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, bookbed.getStudent().getId());
            stm.setString(2, bookbed.getRoom().getRoom_code());
            stm.setDate(3, bookbed.getBooked_date());
            stm.setDate(4, bookbed.getDate_checkout());
            stm.setInt(5, bookbed.getBed().getNumber());
            stm.setInt(6, bookbed.getStatus());
            stm.setInt(7, bookbed.getSemester().getId());
            stm.executeUpdate();
            String update_sql = "UPDATE [Bed]\n"
                    + "   SET [Status] = ?\n"
                    + " WHERE RoomCode = ? and BedNumber = ?";
            PreparedStatement update_stm = connection.prepareStatement(update_sql);
            update_stm.setString(1, "Booked");
            update_stm.setString(2, bookbed.getRoom().getRoom_code());
            update_stm.setInt(3, bookbed.getBed().getNumber());
            update_stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<BookBed> search(String q, int offset, int fetch) {
        ArrayList<BookBed> bookbedrequests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM BookBedRequest inner join Student \n"
                    + "on BookBedRequest.StudentId = Student.StudentID\n"
                    + "where Student.StudentID like ? ORDER BY Student.StudentID\n"
                    + "OFFSET ? rows FETCH next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + q + "%");
            stm.setInt(2, offset);
            stm.setInt(3, fetch);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                BookBed bookbed = new BookBed();
                Student student = new Student();
                Room room = new Room();
                Bed bed = new Bed();
                Semester semester = new Semester();
                student.setId(rs.getString("StudentId"));
                room.setRoom_code(rs.getString("Roomcode"));
                bed.setNumber(rs.getInt("Bednumber"));
                semester.setId(rs.getInt("SemesterId"));
                bookbed.setStudent(student);
                bookbed.setRoom(room);
                bookbed.setBed(bed);
                bookbed.setSemester(semester);
                bookbed.setBooked_date(rs.getDate("Booked_Date"));
                bookbed.setDate_checkout(rs.getDate("Booked_Checkout"));
                bookbed.setStatus(rs.getInt("Status"));
                bookbedrequests.add(bookbed);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookbedrequests;
    }

    public int totalSearch(String q) {
        try {
            String sql = "SELECT COUNT(*) as Total FROM BookBedRequest INNER JOIN Student ON BookBedRequest.StudentId = Student.StudentID"
                    + " WHERE Student.StudentID like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + q + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateBookBed(ArrayList<BookBed> bookbeds) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [BookBedRequest]\n"
                    + "   SET [Status] = ?\n"
                    + " WHERE StudentId = ? and SemesterId = ?";
            for (BookBed bookbed : bookbeds) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, bookbed.getStatus());
                stm.setString(2, bookbed.getStudent().getId());
                stm.setInt(3, bookbed.getSemester().getId());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(BookBedRequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
