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

}
