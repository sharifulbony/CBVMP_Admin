/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbvmp.admin.filter;

import cbvmp.admin.data.BaseClass;
import cbvmp.admin.data.model.AccessControlListModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.log.SingletoneLogger;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author tanbir
 */
@WebFilter(filterName = "SecurityFilter",
        urlPatterns = {"/*"})
public class SecurityFilter implements Filter {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger(SecurityFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("Initializing filter");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        logger.info(SingletoneLogger.generateRequestLogString((HttpServletRequest) request));
        String requestedUrl = request.getServletPath();
        Pattern p = Pattern.compile("/build/*");
        Matcher m = p.matcher(requestedUrl);
        //System.out.println("session id "+ request.isRequestedSessionIdFromURL()); && !requestedUrl.equals("/change/password")
        UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
        if (!requestedUrl.equals("/login") && !requestedUrl.equals("/logout") && !m.find() && !requestedUrl.equals("/change/password")) {
            logger.info("With login:" + requestedUrl);

//            BaseClass base= (BaseClass) request.getSession().getAttribute("base");
            if (userSession != null) {
                //System.out.println("user ok in filter");
                HashMap<String, AccessControlListModel> aclMap = (HashMap) request.getSession().getAttribute("aclMap");;

                if (requestedUrl.equals("/report")) {
                    request.getSession().removeAttribute("jpMap");
                }

                System.out.println("outside  " + requestedUrl);
//                if (requestedUrl.equals("/change/password")) {
//                    System.out.println("password change block ion filter");
//                    chain.doFilter(req, res);
////                    response.sendRedirect(request.getContextPath() + "/change/password");
//                } 
                if (aclMap.containsKey(requestedUrl + "/")) {
                    if (aclMap.get(requestedUrl + "/").isAllowed()) {
                        chain.doFilter(req, res);;
                        System.out.println("entered access");
                    } else {
                        System.out.println("denied " + requestedUrl + "/");
                        response.sendRedirect(request.getContextPath() + "/welcome");
                    }
                } else {
                    chain.doFilter(req, res);
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login");

            }
        } //
        else if (request.getSession().getAttribute("authenticated") != null && request.getSession().getAttribute("authenticated").equals(true) && requestedUrl.equals("/login")) {
            System.out.println("login url tried " + requestedUrl + "/");
            response.sendRedirect(request.getContextPath() + "/welcome");
        } else if (requestedUrl.equals("/change/password") && (request.getSession().getAttribute("notLogPass") != null && request.getSession().getAttribute("notLogPass").equals(true))) {
            System.out.println("change pass url tried");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            logger.info("Without login:" + requestedUrl);
            logger.info(requestedUrl);
            chain.doFilter(req, res);

        }
    }

    public void destroy() {
        logger.info("Destroying filter");

    }

}
