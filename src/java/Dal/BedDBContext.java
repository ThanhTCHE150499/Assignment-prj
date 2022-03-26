/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Bed;
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
public class BedDBContext extends DBContext {

    public ArrayList<Bed> getBedByRoomCode(String roomcode) {
        ArrayList<Bed> beds = new ArrayList<>();
        try {
            String sql = "select BedNumber, Status  \n"
                    + "from Bed inner join Room on Bed.RoomCode = Room.RoomCode\n"
                    + "where Bed.RoomCode = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, roomcode);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Bed bed = new Bed();
                bed.setNumber(rs.getInt("BedNumber"));
                bed.setStatus(rs.getString("Status"));
                beds.add(bed);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BedDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beds;
    }

    public Bed getBedbyRoomandNumber(String roomcode, int bednumber) {
        try {
            String sql = "select RoomCode,BedNumber,Price from Bed where RoomCode = ? and BedNumber = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, roomcode);
            stm.setInt(2, bednumber);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Bed bed = new Bed();
                Room room = new Room();
                room.setRoom_code(rs.getString("RoomCode"));
                bed.setNumber(rs.getInt("BedNumber"));
                bed.setRoom(room);
                bed.setPrice(rs.getBigDecimal("Price"));
                return bed;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BedDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateStatus(Bed bed) {
        try {
            String sql = "UPDATE [Bed]\n"
                    + "   SET [Status] = ?\n"
                    + " WHERE RoomCode = ? and BedNumber = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, bed.getStatus());
            stm.setString(2, bed.getRoom().getRoom_code());
            stm.setInt(3, bed.getNumber());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BedDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
