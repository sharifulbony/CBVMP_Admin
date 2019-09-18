 package cbvmp.admin.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cbvmp.admin.data.manager.UserInfoModelManager;
import cbvmp.admin.data.model.ChangePasswordModel;
import cbvmp.admin.data.model.UserSessionManager;
import cbvmp.admin.util.error.ApplicationError;
import cbvmp.admin.util.log.SingletoneLogger;
import cbvmp.admin.util.security.CustomeEncryption;
import java.util.Date;
import javax.servlet.ServletException;
import cbvmp.admin.data.manager.AccessControlListManager;
import cbvmp.admin.data.manager.UserLoginInfoModelManager;
import cbvmp.admin.data.manager.UserModelManager;
import cbvmp.admin.data.model.AccessControlListModel;
import cbvmp.admin.data.model.IsLoginCheckModel;
import cbvmp.admin.manager.PolicyInfoModelManager;
import cbvmp.admin.data.model.UserLoginInfoModel;
import cbvmp.admin.data.model.UserModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.impl.Log4jLoggerAdapter;

/**
 *
 * @author rahat
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    Log4jLoggerAdapter logger = SingletoneLogger.getLogger("applicationLogger");

    //public int count;
    //public static String user;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");

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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        request.getRequestDispatcher("home.jsp").forward(request, response);

        /*
         =================  access control demo ====================
        PrintWriter out = response.getWriter();
        try {

            AccessControlListManager aclManager = new AccessControlListManager();
            Integer userId = 11;
            HashMap<String,AccessControlListModel> aclMap = aclManager.genrateAccessControlList(userId);
            
            request.getSession(true).setAttribute("aclMap", aclMap);
            for(String keys: aclMap.keySet()){
                out.println(aclMap.get(keys).getAlias()+"/"+aclMap.get(keys).isAllowed());
            }

        } finally {
            out.close();
        }
        
         */
    }

    @Override
    //@SuppressWarnings("empty-statement")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        UserInfoModelManager userManager = new UserInfoModelManager();
        PolicyInfoModelManager policyManager = new PolicyInfoModelManager();
        ChangePasswordModel changePassModelList;
        UserSessionManager userSession = new UserSessionManager();
        String message = null;

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date expireDate = null;
        Date today = new Date();
        //logger.debug(today.toString());
        String passExpire = null;

        try {
            boolean error = false;
            //if need login check from data base just comment out following the "%%" comment
            //%%

            userSession.userModel = userManager.verifyChangePasswordRequest("CBVMP_MASTER", user);

            //%% if(logCheckModel==null || logCheckModel.getIs_login()==0){
            //System.out.println(logCheckModel.getIs_login().toString()+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            // if(logCheckModel==null || logCheckModel.getIs_login()==0){
            if (userSession.userModel != null) {
                if (!userSession.userModel.isIsActive()) {
                    message = "Your account has been locked, contact admin";
                    error = true;

                    String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, user, "Login attempt", "unsuccessful", " ", "1", "Account locked");
                    logger.info(generateRequestLogString);
                } else {

                    IsLoginCheckModel logCheckModel = userManager.getIsLogin("CBVMP_MASTER", user);
                    if (CustomeEncryption.validatePassword(pass, userSession.userModel.getPassword())) {
                        //&& (CustomeEncryption.validatePassword(pass, userSession.userModel.getPassword()))
                        //}) {
                        error = false;
                        passExpire = userSession.userModel.getPassExpireTime();
                        //logger.debug("exp:" + passExpire);
                        System.out.println("exp: " + passExpire);
                        if (!(passExpire == null)) {
                            expireDate = dateFormat.parse(passExpire);
                        }

                        if (expireDate == null || expireDate.after(today)) {
//                        if (!userSession.userModel.isIsActive()) {
//                            message = "Your account has been locked, contact admin";
//                            error = true;
//
//                            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, user, "Login attempt", "unsuccessful", "", "1", "Account locked");
//                            logger.info(generateRequestLogString);
//                        } //if (count < 4 && base.userModel != null && base.userModel.isPasswdChangeRequired() && (CustomeEncryption.validatePassword(pass, base.userModel.getPassword()))) {
                            if (userSession.userModel.isPasswdChangeRequired()) {
                                userSession.policymodel = policyManager.getPolicyInfo("CBVMP_MASTER");
                                //userSession = new UserSessionManager();
                                userSession.setUser(user);
                                userSession.setUserId(userSession.userModel.getId());
                                userSession.setUserCmpoId(userSession.userModel.getCmpoNo());
                                userSession.setUserCategoryId(userSession.userModel.getUserCategoryId());
                                userSession.setLock(0);
                                userSession.setPasswordStrength(userSession.policymodel.getPasswordStrength());
                                userSession.dateExp = false;
                                UserLoginInfoModel userLoginInfoModel = new UserLoginInfoModel();

                                userLoginInfoModel.setId(userSession.userModel.getId());
                                userLoginInfoModel.setLogin_time(new Date());
                                //userLoginInfoModel.setLogout_time(d);
                                userLoginInfoModel.setIp_addr(request.getRemoteAddr());
                                userLoginInfoModel.setAgent_info(request.getHeader("User-Agent"));
                                userLoginInfoModel.setIs_login(0);
                                //userLoginInfoModel.setIs_sess_time_out(0);
                                userLoginInfoModel.setApp_version(SingletoneLogger.APP_VERSION);
                                userLoginInfoModel.setNode_ip(request.getServerName());

                                UserLoginInfoModelManager userLoginInfoModelManager = new UserLoginInfoModelManager();
                                userLoginInfoModelManager.save(userLoginInfoModel);
                                userSession.setLoginNo(userLoginInfoModel.getLogin_no());

                                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, user, "Login attempt", "unsuccessful", " ", "1", "Password change required ");
                                logger.info(generateRequestLogString);

                                request.getSession().invalidate();
                                message = "Password change is required";
                                request.getSession(true).setAttribute("userSession", userSession);
                                request.getSession().setAttribute("authenticated", false);
                                request.getSession().setAttribute("notLogPass", false);
                                request.getSession().setAttribute("message", message);
                                response.sendRedirect(request.getContextPath() + "/change/password");
                            } else {
                                userSession.userModel = userManager.verifyUserNamePassword("CBVMP_MASTER", user, pass);
                                //if (base.userModel != null && count < 4) {
                                if (userSession.userModel != null) {
                                    request.getSession().invalidate();

                                    UserModelManager uManager = new UserModelManager();
                                    userSession.policymodel = policyManager.getPolicyInfo("CBVMP_MASTER");
                                    //userSession.userSession = new UserSessionManager();
                                    userSession.setUser(user);
                                    userSession.setUserCmpoId(userSession.userModel.getCmpoNo());
                                    userSession.setUserCategoryId(userSession.userModel.getUserCategoryId());
                                    UserModel um = uManager.get(userSession.userModel.getId().longValue());
                                    //logger.debug("User Cmpo ID " + userSession.userModel.getCmpoNo() + " Saved id usersession " + userSession.getUserCmpoId());

                                    userSession.setLoginTime(new Date());
                                    userSession.setLock(0);
                                    userSession.setPasswordStrength(userSession.policymodel.getPasswordStrength());
                                    userSession.setMaxInactiveTime(Integer.parseInt(userSession.policymodel.getMaxInactiveTime()));
                                    userSession.setMaxSessionTimeout(Integer.parseInt(userSession.policymodel.getMaxSessionTime()));
                                    //  count=0;
                                    //update 
                                    um.setLastLoginTime(userSession.getLoginTime());
                                    uManager.update(um);

//                            modelManager.
                                    request.getSession(true).setAttribute("userSession", userSession);
                                    System.out.println("CHECK:" + userSession.getAlreadyLoggedIn());
                                    System.out.println("admin : "+ userSession.userModel.getIsAdmin());

                                    if ((logCheckModel != null && logCheckModel.getIs_login() == 1 && userSession.userModel.getIsAdmin() == 0)) {
//userSession.getAlreadyLoggedIn() ||                                         
// request.getSession().removeAttribute("base.userSession");
                                        //request.getSession().invalidate();
//                                        System.out.println("inside already block " + logCheckModel.getIs_login());
                                        message = "You are already in an active session";
                                        String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Login attempt", "unsuccessful", " ", "1", "Active Session ");
                                        logger.info(generateRequestLogString);
                                        request.setAttribute("message", message);
                                        request.getSession().setAttribute("notLogPass", true);
                                        request.getRequestDispatcher("home.jsp").forward(request, response);
                                        //base.userSession.logins.put(base.userSession.getUser(),request.getSession());
                                        //response.sendRedirect("home.jsp");
                                    } else {
                                        // logger.debug("Logged in user id:" + userSession.userModel.getId());
                                        //out = response.getWriter();
                                        try {

                                            AccessControlListManager aclManager = new AccessControlListManager();
                                            Integer userId = userSession.userModel.getId();
                                            HashMap<String, AccessControlListModel> aclMap = aclManager.genrateAccessControlList(userId);

                                            request.getSession(true).setAttribute("aclMap", aclMap);
                                            for (String keys : aclMap.keySet()) {
                                                System.out.println(aclMap.get(keys).getAlias() + "/" + aclMap.get(keys).isAllowed());
                                            }
                                            userSession.userLock = userManager.updateUserLockFlag("CBVMP_MASTER", user, 1);
                                            // update user login info
                                            System.out.println("lock value: " + userSession.userLock.getIsError());

                                            Date d = new Date();
                                            //user information store
                                            UserLoginInfoModel userLoginInfoModel = new UserLoginInfoModel();

                                            userLoginInfoModel.setId(userSession.userModel.getId());
                                            userLoginInfoModel.setLogin_time(userSession.getLoginTime());
                                            //userLoginInfoModel.setLogout_time(d);
                                            userLoginInfoModel.setIp_addr(request.getRemoteAddr());
                                            userLoginInfoModel.setAgent_info(request.getHeader("User-Agent"));
                                            userLoginInfoModel.setIs_login(1);
                                            //userLoginInfoModel.setIs_sess_time_out(0);
                                            userLoginInfoModel.setApp_version(SingletoneLogger.APP_VERSION);
                                            userLoginInfoModel.setNode_ip(request.getServerName());

                                            UserLoginInfoModelManager userLoginInfoModelManager = new UserLoginInfoModelManager();
                                            userLoginInfoModelManager.save(userLoginInfoModel);
                                            userSession.setLoginNo(userLoginInfoModel.getLogin_no());
                                            // System.out.println(userLoginInfoModel.getLogin_no());

                                            //logger.info("Logged in user=" + userSession.userModel.getId() + "information saved");
                                            //request, userid, action, result, comment, alarm
                                            // all parameter should be added
                                            //if a parameter have no value, then add empty string
                                            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, userSession.getUser(), "Login attempt", "successful", " ", "0", " ");
                                            logger.info(generateRequestLogString);
                                            request.getSession().setAttribute("authenticated", true);
                                            request.getSession().setAttribute("notLogPass", false);
                                            response.sendRedirect(request.getContextPath() + "/welcome");
                                        } finally {
                                            out.close();
                                        }
                                    }
                                } else {
                                    //count++;
                                    message = ApplicationError.EXP010206_DESC;
                                    //request.getSession().setAttribute("message", message);
                                    //response.sendRedirect(request.getContextPath()+ "/login");
                                    error = true;

                                    String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, user, "Login attempt", "unsuccessful", " ", "0", "Login Credential mismatched ");
                                    logger.error(generateRequestLogString);

                                    userSession.userLock = userManager.updateUserLockFlag("CBVMP_MASTER", user, 0);
                                }
                            }
                        } else {
                            message = "Your Password has been expired";
                            error = false;

                            String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, user, "Login attempt", "unsuccessful", " ", "1", "Password expired ");
                            logger.info(generateRequestLogString);
                            //logger.debug(message);
                            //request.getSession().invalidate();
                            //request.getSession().invalidate();
                            userSession.dateExp = true;
                            userSession.policymodel = policyManager.getPolicyInfo("CBVMP_MASTER");
                            //userSession = new UserSessionManager();
                            userSession.setPasswordStrength(userSession.policymodel.getPasswordStrength());
                            userSession.setUser(user);
                            userSession.setUserId(userSession.userModel.getId());

                            UserLoginInfoModel userLoginInfoModel = new UserLoginInfoModel();
                            userLoginInfoModel.setId(userSession.userModel.getId());
                            userLoginInfoModel.setId(userSession.userModel.getId());
                            userLoginInfoModel.setLogin_time(new Date());
                            //userLoginInfoModel.setLogout_time(d);
                            userLoginInfoModel.setIp_addr(request.getRemoteAddr());
                            userLoginInfoModel.setAgent_info(request.getHeader("User-Agent"));
                            userLoginInfoModel.setIs_login(0);
                            //userLoginInfoModel.setIs_sess_time_out(0);
                            userLoginInfoModel.setApp_version(SingletoneLogger.APP_VERSION);
                            userLoginInfoModel.setNode_ip(request.getServerName());
                            UserLoginInfoModelManager userLoginInfoModelManager = new UserLoginInfoModelManager();
                            userLoginInfoModelManager.save(userLoginInfoModel);
                            userSession.setLoginNo(userLoginInfoModel.getLogin_no());
                            //request.getSession().setAttribute("message", message);
                            request.getSession().invalidate();

                            request.getSession(true).setAttribute("userSession", userSession);
                            request.getSession().setAttribute("message", message);
                            response.sendRedirect(request.getContextPath() + "/change/password");
                        }

                    } else {
                        message = ApplicationError.EXP010206_DESC;
                        //request.getSession().setAttribute("message", message);
                        //response.sendRedirect(request.getContextPath()+ "/login");
                        error = true;

                        String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, user, "Login attempt", "unsuccessful", " ", "0", "Login Credential mismatched");
                        logger.error(generateRequestLogString);

                        userSession.userLock = userManager.updateUserLockFlag("CBVMP_MASTER", user, 0);
                        System.out.println("lock value: " + userSession.userLock.getIsError());

                    }
                }
            } else {
                message = ApplicationError.EXP010206_DESC;
                error = true;

                String generateRequestLogString = SingletoneLogger.generateRequestLogString(request, user, "Login attempt", "unsuccessful", " ", "1", "User not found ");
                logger.error(generateRequestLogString);

                //userSession.userLock = userManager.updateUserLockFlag("CBVMP_MASTER", user, 0);
                //System.out.println("lock value: " + userSession.userLock.getIsError());
            }
//            }
//            else
//            {
//                error=true;
//                message = "You are already in a active session";
//                request.setAttribute("message", message);
//                System.out.println("fgsjgsjslfkglsj");
//            }
            if (error) {
                //logger.debug(message);

                request.getSession().setAttribute("message", message);
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //processRequest(request, response);
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
