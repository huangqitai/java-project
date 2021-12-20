package com.qitai.utils.exception;

import com.southgis.ibase.logstatistics.service.ILogService;
import com.qitai.utils.CheckUtil;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @date 2020-01-08
 * @author 胡现荣
 */
public class ExceptionUtil {

    /**
     * 记录日志
     * getRequest().getLocalAddr()方法获取接口路径有可能错误，如需准确请求接口名，需要在调用方法体内部使用如下方法
     * WebServiceUtils.getRequest().getLocalAddr()
     * @param e 异常信息
     */
    public static void log(Exception e) {
        String localaddr = "";
        try {
            localaddr = getRequest().getLocalAddr();
        } catch (Exception ignored) {System.err.println("获取getLocalAddr出错");}
        try {
            ILogService iLogService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ILogService.class);
            iLogService.writeException("", "", getCurrentUserId(), localaddr, e, "");
        }catch (Exception ex){
            System.err.println("执行记录接口日志异常");
        }
    }

    /**
     * 记录日志
     * @param localAddr 请求接口路径
     * @param e 异常信息
     */
    public static void log(String localAddr,Exception e) {
        try {
            ILogService iLogService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ILogService.class);
            iLogService.writeException("", "", getCurrentUserId(), localAddr, e, "");
        }catch (Exception exception){
            System.err.println("执行记录接口日志异常");
        }
    }
    /**
     * 获取当前请求对象，需要在web.xml中配置RequestContextListener监听器
     * （这样可使得Request对象与Controller中的更一致，同时也可防止线程方面的问题）：<br/>
     * &lt;listener&gt;<br/>
     * &lt;listener-class&gt;<br/>
     * org.springframeworntext.request.RequestContextListener<br/>
     * &lt;/listener-class&gt;<br/>
     * &lt;/listener&gt;<br/>
     * 注：RequestContextListener监听器主要用来解决Request作用域的Bean管理。
     *
     * @return request
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        ServletRequestAttributes srAttr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (srAttr != null) {
            request = srAttr.getRequest();
        }

        return request;
    }

    /**
     * 获取当前用户ID，需要在web.xml中配置RequestContextListener监听器（参见getRequest）。
     * 先从Cas的AttributePrincipal查找，未找到再从HttpSession的Attribute中查找
     *
     * @return 未找到，返回null
     */
    public static String getCurrentUserId() {
        return getCurrentUserId(getRequest());
    }

    /**
     * 从请求对象中获取当前用户ID。
     * 1.从CAS的AttributePrincipal通过“USERID”名查找（CAS服务传入的扩展信息）
     * 2.从认证信息Principal中获取name值（单点登录服务接口约定传入登录ID）
     *
     * @param request 需要传入的请求对象
     * @return 未找到，返回null
     */
    public static String getCurrentUserId(HttpServletRequest request) {
        if (request == null) return null;

        String userId = null;
        Principal principal = null;

        HttpSession session = request.getSession(false);
        if (session != null) {
            Object ass = session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
            //判断是否已注销，如果已注销，不返回有效用户ID
            if (ass != null) {
                //先从请求对象中获取
                principal = request.getUserPrincipal();
                if (principal == null) {
                    //再从全局对象中获取
                    org.jasig.cas.client.validation.Assertion assSave =
                            org.jasig.cas.client.util.AssertionHolder.getAssertion();
                    if (assSave != null) {
                        principal = assSave.getPrincipal();
                    }
                }

                if (principal == null) {
                    if (ass instanceof org.jasig.cas.client.validation.Assertion) {
                        principal = ((org.jasig.cas.client.validation.Assertion) ass).getPrincipal();
                    }
                }
            }
        }

        if (principal != null) {
            userId = principal.getName();
        }
        //从请求头获取当前用户ID（用于内部无验证要求的服务
        if (CheckUtil.isNullorEmpty(userId)) {
            userId = request.getHeader("X-AUTH-ID");
        }
        return CheckUtil.isNullorEmpty(userId) ? null : userId;
    }
}
