package ru.geekbrains;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/")
public class FirstServlet implements Servlet {

    private ServletConfig servletConfig;

    public void init(ServletConfig config) throws ServletException {
        this.servletConfig = config;
    }

    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h1>Hello from servlet!</h1>");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
