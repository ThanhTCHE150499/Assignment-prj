/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Dom;
import Model.Room;
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
public class RoomDBContext extends DBContext {
    public Room getRoombyRoomCode(String roomcode){
        try {
            String sql = "select RoomCode,DomId,SumBed from Room where RoomCode = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, roomcode);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Room room = new Room();
                Dom dom = new Dom();
                dom.setId(rs.getInt("DomId"));
                room.setRoom_code(rs.getString("RoomCode"));
                room.setSumbed(rs.getInt("SumBed"));
                room.setDom(dom);
                return room;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Room> getRoombyDomID (int domid){
          ArrayList<Room> rooms = new ArrayList<>();
        try {
            String sql = "select RoomCode,DomId,SumBed from Room where DomId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, domid);
              ResultSet rs = stm.executeQuery();
              while(rs.next()){
                  Room room = new Room();
                  Dom dom = new Dom();
                  dom.setId(rs.getInt("DomId"));
                  room.setDom(dom);
                  room.setRoom_code(rs.getString("RoomCode"));
                  room.setSumbed(rs.getInt("SumBed"));
                  rooms.add(room);
              }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }
}
