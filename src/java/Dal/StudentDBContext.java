/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Room;
import Model.Student;
import java.math.BigDecimal;
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
public class StudentDBContext extends DBContext {

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "Select * from Student";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getString("StudentID"));
                student.setName(rs.getString("StudentName"));
                student.setGender(rs.getBoolean("Gender"));
                student.setDob(rs.getDate("DOB"));
                Room room = new Room();
                room.setRoom_code(rs.getString("RoomCode"));
                student.setRoom_code(room);
                student.setAddress(rs.getString("Address"));
                student.setMoney(rs.getBigDecimal("Money"));
                student.setUsername(rs.getString("Username"));
                students.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public Student getStudentbyUsername(String username) {
        try {
            String sql = "Select * from Student inner join Account on Student.Username = Account.Username\n"
                    + "where Account.Username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("StudentID"));
                s.setName(rs.getString("StudentName"));
                s.setGender(rs.getBoolean("Gender"));
                s.setDob(rs.getDate("DOB"));
                Room room = new Room();
                room.setRoom_code(rs.getString("RoomCode"));
                s.setRoom_code(room);
                s.setAddress(rs.getString("Address"));
                s.setMoney(rs.getBigDecimal("Money"));
                s.setUsername(rs.getString("Username"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addStudent(Student student) {
        try {
            
            connection.setAutoCommit(false);
            String sql1 = "INSERT INTO [GroupAccount]\n"
                    + "           ([gid]\n"
                    + "           ,[username])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm1 = connection.prepareStatement(sql1);
            stm1.setInt(1, 2);
            stm1.setString(2, student.getUsername());
            stm1.executeUpdate();
            String sql = "INSERT INTO [Student]\n"
                    + "           ([StudentID]\n"
                    + "           ,[StudentName]\n"
                    + "           ,[Gender]\n"
                    + "           ,[DOB]\n"
                    + "           ,[RoomCode]\n"
                    + "           ,[Address]\n"
                    + "           ,[Money]\n"
                    + "           ,[Username])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, student.getId());
            stm.setString(2, student.getName());
            stm.setBoolean(3, student.isGender());
            stm.setDate(4, student.getDob());
            stm.setString(5, student.getRoom_code().getRoom_code());
            stm.setString(6, student.getAddress());
            stm.setBigDecimal(7, student.getMoney());
            stm.setString(8, student.getUsername());
            stm.executeUpdate();
           
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateStudent(Student student) {
        try {
            String sql_updateStu = "UPDATE [Student]\n"
                    + "   SET [StudentName] = ?\n"
                    + "      ,[Gender] = ?\n"
                    + "      ,[DOB] = ?\n"
                    + "      ,[Address] = ?\n"
                    + "\n"
                    + " WHERE Username = ?";
            PreparedStatement stm_updateStu = connection.prepareStatement(sql_updateStu);
            stm_updateStu.setString(1, student.getName());
            stm_updateStu.setBoolean(2, student.isGender());
            stm_updateStu.setDate(3, student.getDob());
            stm_updateStu.setString(4, student.getAddress());
            stm_updateStu.setString(5, student.getUsername());
            stm_updateStu.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStudentMoney(Student student) {
        try {
            String sql = "UPDATE [Student]\n"
                    + "   SET [Money] = ?\n"
                    + " WHERE Username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBigDecimal(1, student.getMoney());
            stm.setString(2, student.getUsername());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
