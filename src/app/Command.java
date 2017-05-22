package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 17-05-05.
 */
public class Command implements Serializable {

    private String className;
    private String methodName;
    private ArrayList<String> params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }
}
