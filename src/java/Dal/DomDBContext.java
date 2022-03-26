/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Dom;
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
public class DomDBContext extends DBContext{
    public Dom getDombyID(int id){
        try {
            String sql = "select id,Name from Dom where Id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Dom dom = new Dom();
                dom.setId(rs.getInt("id"));
                dom.setName(rs.getString("Name"));
                return dom;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Dom> getDoms(){
        ArrayList<Dom> doms = new ArrayList<>();
        try {
            String sql = "select Id,Name from Dom";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Dom dom = new Dom();
                dom.setId(rs.getInt("Id"));
                dom.setName(rs.getString("Name"));
                doms.add(dom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doms;
    }
    
}
