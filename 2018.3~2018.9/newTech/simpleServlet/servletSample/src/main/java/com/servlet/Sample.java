package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sample extends HttpServlet {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1936901196555528367L;

    public void init() throws ServletException {
        System.out.println("This is initMethod");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("This is doGet");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("This is Servlet");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    public void destroy() {
        super.destroy();
        System.out.println("This is destroy");
    }
}
