package utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    /*
     * 它会根据请求中的m，来决定调用本类的哪个方法
     */
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=utf-8");
        String methodName = req.getParameter("method");

        // 当没用指定要调用的方法时，那么默认请求的是execute()方法。
        if (methodName == null || methodName.isEmpty()) {
            methodName = "execute";
        }
        //反射调用合适的服务
        Class c = this.getClass();
        try {
            Method m = c.getMethod(methodName, HttpServletRequest.class,
                    HttpServletResponse.class);
            String result = (String) m.invoke(this, req, res);
            if (result != null && !result.isEmpty()) {
                req.getRequestDispatcher(result).forward(req, res);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
