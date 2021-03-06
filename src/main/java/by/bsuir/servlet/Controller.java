package by.bsuir.servlet;

import by.bsuir.command.*;
import by.bsuir.db.ConnectionPool;
import by.bsuir.factory.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@WebServlet(urlPatterns = "/controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
      requestProcess(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        requestProcess(req,resp);
    }

    private void requestProcess(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestCommand requestCommand = new RequestCommand(req);
        Command command = CommandFactory.getCommand(requestCommand);
        if(command != null){
            ResponseCommand respCommand = command.execute(requestCommand);
            requestCommand.updateRequest(req);
            if(respCommand.getTypeTransition().equals(TransitionType.FORWARD)){
                getServletContext().getRequestDispatcher(respCommand.getPath()).forward(req,resp);
            }else {
                resp.sendRedirect(req.getContextPath() + respCommand.getPath());
            }
        }else {
            resp.sendRedirect(req.getContextPath() + "/jsp/error.jsp");
        }
    }
}
