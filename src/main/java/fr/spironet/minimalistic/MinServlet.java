package fr.spironet.minimalistic;

import javax.servlet.http.*;
import java.io.*;


public class MinServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -1074473813454807497L;

    @Override
    public void service(HttpServletRequest req,
            HttpServletResponse res) throws IOException {
        res.setHeader("Content-Type", "text/plain");
        res.setHeader("Content-Length", "1111");
        res.getOutputStream().write("it works.".getBytes());
        
        
     }
}
