import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by A.V.Tsaplin on 14.08.2016.
 */

public class ManagedObjects {

    private String objectClass;
    private String distName;
    private Map<String, Object> fields;

    public ManagedObjects(String objectClass, String distName) {
        this.distName = distName;
        this.objectClass = objectClass;
        this.fields = new HashMap<>();
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void addFields(String name, Object obj) {
        this.fields.put(name, obj);
    }

    public String getObjectClass() {
        return objectClass;
    }

    public String getDistName() {
        return distName;
    }
}
