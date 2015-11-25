package io.kombat.core.servlet;

import javax.enterprise.inject.spi.CDI;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ac-bsilva on 19/11/15.
 */
public class ServletController {

    private final Class className;
    private final Object instance;
    private final Map<String, Method> actions = new LinkedHashMap<String, Method>();

    public ServletController(Class className) {
        this.className = className;
        this.instance = CDI.current().select(className).get();
    }

    public Class getClassName() {
        return className;
    }

    public Object getInstance() {
        return instance;
    }

    public Method getAction(String name) {
        return this.actions.get(name);
    }

    public void addAction(Method action) {
        this.actions.put(action.getName(), action);
    }
}
