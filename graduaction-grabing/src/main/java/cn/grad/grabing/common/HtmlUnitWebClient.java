package cn.grad.grabing.common;

import cn.grad.grabing.util.DateUtils;
import cn.grad.grabing.util.Validation;
import com.gargoylesoftware.htmlunit.WebClient;
import com.sun.corba.se.impl.orbutil.ObjectUtility;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * html unit浏览器模拟工具
 */
@Component("htmlUnitWebClient")
public class HtmlUnitWebClient extends WebClient {

    /**
     * 初始化浏览器
     */
    public void initializer(HtmlUnitWebClient webClient, Map<String, Object> params) throws Exception {
        if (params.isEmpty()) return;
        Class clientBean = webClient.getClass();
        Method[] beanMethods = clientBean.getMethods();
        for (Method method : beanMethods) {
            String methodName = method.getName();
            if (methodName.startsWith("set")) {
                Class[] cc = method.getParameterTypes();
                if (cc.length == 1) {
                    String type = cc[0].getName();    //parameter type
                    //property name
                    String prop = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                    //set property value
                    if (!params.keySet().contains(prop))
                        continue;
                    switch (type) {
                        case "java.lang.String":
                            method.invoke(webClient, params.get(prop).toString());
                            break;
                        case "int":
                            method.invoke(webClient, params.get(prop));
                            break;
                        case "java.lang.Integer":
                            method.invoke(webClient, Integer.valueOf(params.get(prop).toString()));
                            break;
                        case "long":
                            method.invoke(webClient, Long.valueOf(params.get(prop).toString()));
                            break;
                        case "java.lang.Long":
                            method.invoke(webClient, Long.valueOf(params.get(prop).toString()));
                            break;
                        case "boolean":
                            method.invoke(webClient, Boolean.valueOf(params.get(prop).toString()));
                            break;
                        case "java.lang.Boolean":
                            method.invoke(webClient, Boolean.valueOf(params.get(prop).toString()));
                            break;
                        case "java.util.Date":
                            method.invoke(webClient, DateUtils.parse(params.get(prop).toString()));
                            break;
                        default:
                            method.invoke(webClient, params.get(prop));
                            break;
                    }
                }
            } else if (methodName.startsWith("get")) {
                //getter
                Object obj = method.invoke(webClient);
                if(ObjectUtils.equals(webClient.getCookieManager(),obj)){

                }
                String getterName = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                Class[] cc = method.getParameterTypes();

                if (Validation.isObjNotNull(field)) {

                }
                if (cc.length == 1) {
                    String type = cc[0].getName();    //parameter type
                    //property name
                    String prop = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                    //set property value
                    if (!params.keySet().contains(prop))
                        continue;
                    switch (type) {
                        case "java.lang.String":
                            method.invoke(webClient, params.get(prop).toString());
                            break;
                        case "int":
                            method.invoke(webClient, params.get(prop));
                            break;
                        case "java.lang.Integer":
                            method.invoke(webClient, Integer.valueOf(params.get(prop).toString()));
                            break;
                        case "long":
                            method.invoke(webClient, Long.valueOf(params.get(prop).toString()));
                            break;
                        case "java.lang.Long":
                            method.invoke(webClient, Long.valueOf(params.get(prop).toString()));
                            break;
                        case "boolean":
                            method.invoke(webClient, Boolean.valueOf(params.get(prop).toString()));
                            break;
                        case "java.lang.Boolean":
                            method.invoke(webClient, Boolean.valueOf(params.get(prop).toString()));
                            break;
                        case "java.util.Date":
                            method.invoke(webClient, DateUtils.parse(params.get(prop).toString()));
                            break;
                        default:
                            method.invoke(webClient, params.get(prop));
                            break;
                    }
                }
            }
        }
    }


}
