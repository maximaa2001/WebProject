package by.bsuir.command;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.*;

public class RequestCommand {
    private final static Logger logger = Logger.getLogger(RequestCommand.class);
    private Map<String,Object> requestAttributes;
    private Map<String,String[]> requestParameters;
    private Map<String,Object> sessionAttributes;
    private Map<String, Part> requestParts;

    private boolean invalidateSession;

    public RequestCommand(HttpServletRequest request){
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        requestParts = new HashMap<>();
        initRequestAttributes(request);
        initRequestParameters(request);
        initSessionAttributes(request);
        initParts(request);
    }

    private void initRequestAttributes(HttpServletRequest request){
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String key = attributeNames.nextElement();
            requestAttributes.put(key,request.getAttribute(key));
        }
    }

    private void initRequestParameters(HttpServletRequest request){
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String key = parameterNames.nextElement();
            requestParameters.put(key,request.getParameterValues(key));
        }
    }

    private void initSessionAttributes(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null) {
            Enumeration<String> attributeNames = request.getSession().getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String key = attributeNames.nextElement();
                sessionAttributes.put(key, request.getSession().getAttribute(key));
            }
        }
    }

    private void initParts(HttpServletRequest request){
        try {
            Collection<Part> parts = request.getParts();
            Object[] objects = parts.toArray();
            for (int i = 0; i < objects.length; i++) {
                Part part = (Part) objects[i];
                requestParts.put(part.getName(),part);
            }
        }catch (ServletException | IOException e){
            logger.log(Level.ERROR, "Error while init Parts");
        }

    }

    public void updateRequest(HttpServletRequest request){
        if(invalidateSession){
            request.getSession().invalidate();
        }
        Set<Map.Entry<String, Object>> entriesRequest = requestAttributes.entrySet();
        for (Map.Entry<String, Object> element: entriesRequest){
            request.setAttribute(element.getKey(),element.getValue());
        }
        Set<Map.Entry<String, Object>> entriesSession = sessionAttributes.entrySet();
        HttpSession session = request.getSession(false);
        if(session != null){
            for (Map.Entry<String, Object> element: entriesSession){
                session.setAttribute(element.getKey(),element.getValue());
            }
        }
    }

    public Object getRequestAttribute(String key){
        return requestAttributes.get(key);
    }

    public String getRequestParameter(String key){
        String[] param = requestParameters.getOrDefault(key,null);
        return param != null ? param[0] : null;
    }

    public Object getSessionAttribute(String key){
        return sessionAttributes.get(key);
    }

    public Part getRequestPart(String key){
        return requestParts.get(key);
    }

    public void addSessionAttribute(String key, Object value){
        if(!isInvalidateSession()){
            sessionAttributes.put(key,value);
        }
    }

    public void addRequestAttribute(String key, Object value){
        requestAttributes.put(key,value);
    }

    public void removeRequestAttribute(String key){
        requestAttributes.remove(key);
    }

    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }
}
