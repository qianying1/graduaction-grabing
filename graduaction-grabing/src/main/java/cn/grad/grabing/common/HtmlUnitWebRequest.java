package cn.grad.grabing.common;

import cn.grad.grabing.util.DateUtils;
import com.gargoylesoftware.htmlunit.WebRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Map;

/**
 * html unit请求工具
 */
@Component("htmlUnitWebRequest")
public class HtmlUnitWebRequest extends WebRequest {

    public HtmlUnitWebRequest(URL url) {
        super(url);
    }

    public HtmlUnitWebRequest() {
        super(null);
    }

    /**
     * 初始化
     *
     * @param webRequest
     * @param params
     */
    public void initializer(HtmlUnitWebRequest webRequest, Map<String, Object> params) throws Exception {
        if (params.isEmpty()) return;
        Class clientBean = webRequest.getClass();
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
                            method.invoke(webRequest, params.get(prop).toString());
                            break;
                        case "java.lang.Integer":
                            method.invoke(webRequest, Integer.valueOf(params.get(prop).toString()));
                            break;
                        case "int":
                            method.invoke(webRequest, params.get(prop));
                            break;
                        case "long":
                            method.invoke(webRequest, Long.valueOf(params.get(prop).toString()));
                            break;
                        case "java.lang.Long":
                            method.invoke(webRequest, Long.valueOf(params.get(prop).toString()));
                            break;
                        case "boolean":
                            method.invoke(webRequest, Boolean.valueOf(params.get(prop).toString()));
                            break;
                        case "java.lang.Boolean":
                            method.invoke(webRequest, Boolean.valueOf(params.get(prop).toString()));
                            break;
                        case "java.util.Date":
                            method.invoke(webRequest, DateUtils.parse(params.get(prop).toString()));
                            break;
                        default:
                            method.invoke(webRequest, params.get(prop));
                            break;
                    }
                }
            }
        }
    }
}
