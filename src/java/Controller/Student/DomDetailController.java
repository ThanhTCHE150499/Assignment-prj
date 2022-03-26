/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Student;

import Controller.authentication.BaseRequireAuthenController;
import Dal.DomDBContext;
import Dal.RoomDBContext;
import Model.Dom;
import Model.Room;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class DomDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected String toGsonObject(String name, String value) {
        return "{\"" + name + "\" : " + value + "}";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = (request.getParameter("did") == null) ? 1 : Integer.parseInt(request.getParameter("did"));
        DomDBContext domDB = new DomDBContext();
        RoomDBContext roomDB = new RoomDBContext();
        ArrayList<Room> rooms = roomDB.getRoombyDomID(id);
        ArrayList<Dom> doms = domDB.getDoms();
        request.setAttribute("did", id);
        request.setAttribute("doms", doms);
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("view/DomDetail.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson = new Gson();
        Integer id = (request.getParameter("did") == null) ? 1 : Integer.parseInt(request.getParameter("did"));
        RoomDBContext roomDB = new RoomDBContext();
        ArrayList<Room> rooms = roomDB.getRoombyDomID(id);
        String roomtoGsonObject = toGsonObject("rooms", gson.toJson(rooms));
        response.getWriter().print(roomtoGsonObject);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
