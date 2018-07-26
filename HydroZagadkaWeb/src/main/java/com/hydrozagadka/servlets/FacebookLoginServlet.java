package com.hydrozagadka.servlets;

import com.hydrozagadka.Beans.CreateFBConnection;
import com.hydrozagadka.Beans.FBGraphUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/login")
public class FacebookLoginServlet extends HttpServlet {

    private static final long serialVersionID = 1L;
    private String code = "";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        code = req.getParameter("code");
        if (code == null || code.equals("")) {
            throw new RuntimeException(
                    "ERROR: Didn't get code parameter in callback.");
        }
        CreateFBConnection fbConnection = new CreateFBConnection();
        String accessToken = fbConnection.getAccessToken(code);
        resp.setContentType("text/html");
        FBGraphUtil fbGraph = new FBGraphUtil(accessToken);
        String graph = fbGraph.getFBGraph();

        @SuppressWarnings("unchecked")
        Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
        ServletOutputStream out = resp.getOutputStream();
        out.println("<h2>Facebook OAuth Login</h2>");
        out.println("<div style='float:left'>");
        out.println("<img src='https://graph.facebook.com/"
                + fbProfileData.get("id")
                + "/picture?type=square' style='border-radius:50%'alt='Profile Pic'>");
        out.println("</div>");
        out.println("<div style='float:left'>");
        out.println("Name   : " + fbProfileData.get("name") + "<br>");
        out.println("Email  : " + fbProfileData.get("email") + "<br>");
        out.println("Gender : " + fbProfileData.get("gender") + "<br>");
        out.println("</div>");
    }

}
