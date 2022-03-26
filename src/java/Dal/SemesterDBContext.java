/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Semester;
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
public class SemesterDBContext extends DBContext{
    public ArrayList<Semester> getSemesters(){
        ArrayList<Semester> semesters = new ArrayList<>();
        try {
            String sql = "select Id,NumberOfSemester,Year from Semester";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Semester semester = new Semester();
                semester.setId(rs.getInt("Id"));
                semester.setNumbersemester(rs.getInt("NumberOfSemester"));
                semester.setYear(rs.getInt("Year"));
                semesters.add(semester);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemesterDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return semesters;
    }
    
    public Semester getbyID(int id){
        try {
            String sql = "select Id,NumberOfSemester,Year from Semester where Id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Semester semester = new Semester();
                semester.setId(rs.getInt("Id"));
                semester.setNumbersemester(rs.getInt("NumberOfSemester"));
                semester.setYear(rs.getInt("Year"));
                return semester;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemesterDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
