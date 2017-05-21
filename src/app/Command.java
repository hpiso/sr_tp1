package app;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hugo on 17-05-05.
 */
public class Command implements Serializable {

    private String className;
    private String functionName;
    private List<Integer> params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Integer> getParams() {
        return params;
    }

    public void setParams(List<Integer> params) {
        this.params = params;
    }
}
