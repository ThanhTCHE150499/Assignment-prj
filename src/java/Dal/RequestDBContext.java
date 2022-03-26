/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Request;
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
public class RequestDBContext extends DBContext {

    public void insert(Request request) {
        try {
            String sql = "INSERT INTO [Request]\n"
                    + "           ([StudentID]\n"
                    + "           ,[Note]\n"
                    + "           ,[Title]\n"
                    + "           ,[Status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, request.getStudent().getId());
            stm.setString(2, request.getNote());
            stm.setString(3, request.getTitle());
            stm.setInt(4, request.getStatus());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Request> getRequestbyStudentID(String studentid) {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = "select Id,StudentID,Note,Title,Status from Request where StudentID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, studentid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("Id"));
                Student student = new Student();
                student.setId(rs.getString("StudentID"));
                request.setStudent(student);
                request.setNote(rs.getString("Note"));
                request.setTitle(rs.getString("Title"));
                request.setStatus(rs.getInt("Status"));
                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }

    public ArrayList<Request> getRequests() {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = "select * from Request";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("Id"));
                request.setNote(rs.getString("Note"));
                request.setTitle(rs.getString("Title"));
                request.setStatus(rs.getInt("Status"));
                Student student = new Student();
                student.setId(rs.getString("StudentID"));
                request.setStudent(student);
                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }

    public ArrayList<Request> pagingRequest(int pagesize, int pageindex) {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = "SELECT Id,StudentID,Note,Title,Status FROM\n"
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY Id asc) as Rownum,*\n"
                    + "FROM Request) r\n"
                    + "WHERE\n"
                    + "Rownum >= (?-1)*?+1 AND Rownum <= ?*?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("Id"));
                request.setNote(rs.getString("Note"));
                request.setTitle(rs.getString("Title"));
                request.setStatus(rs.getInt("Status"));
                Student student = new Student();
                student.setId(rs.getString("StudentID"));
                request.setStudent(student);
                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }

    public int getRowCount() {
        try {
            String sql = "SELECT COUNT(*) as Total FROM Request";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateRequest(ArrayList<Request> requests) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Request]\n"
                    + "   SET [Status] = ?\n"
                    + " WHERE Id = ?";

            for (Request request : requests) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, request.getStatus());
                stm.setInt(2, request.getId());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Request> search(String q, int offset, int fetch) {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Request inner join Student \n"
                    + "on Request.StudentID = Student.StudentID\n"
                    + "where Student.StudentID like ? ORDER BY Student.StudentID\n"
                    + "OFFSET ? rows FETCH next ? rows only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+q+"%");
            stm.setInt(2, offset);
            stm.setInt(3, fetch);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("Id"));
                request.setNote(rs.getString("Note"));
                request.setTitle(rs.getString("Title"));
                request.setStatus(rs.getInt("Status"));
                Student student = new Student();
                student.setId(rs.getString("StudentID"));
                request.setStudent(student);
                requests.add(request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requests;
    }
    
    public int totalSearch(String q) {
        try {
            String sql = "SELECT COUNT(*) as Total FROM Request INNER JOIN Student ON Request.StudentID = Student.StudentID"
                    + " WHERE Student.StudentID like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+q+"%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
