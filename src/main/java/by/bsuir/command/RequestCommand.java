package by.bsuir.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestCommand {
    private Map<String,Object> requestAttributes;
    private Map<String,String[]> requestParameters;
    private Map<String,Object> sessionAttributes;
    private boolean invalidateSession;

    public RequestCommand(HttpServletRequest request){
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        initRequestAttributes(request);
        initRequestParameters(request);
        initSessionAttributes(request);
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

    public void addSessionAttribute(String key, Object value){
        if(!isInvalidateSession()){
            sessionAttributes.put(key,value);
        }
    }

    public void addRequestAttribute(String key, Object value){
        requestAttributes.put(key,value);
    }

    public boolean isInvalidateSession() {
        return invalidateSession;
    }

    public void setInvalidateSession(boolean invalidateSession) {
        this.invalidateSession = invalidateSession;
    }
}
