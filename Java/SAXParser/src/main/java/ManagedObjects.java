import java.util.HashMap;
import java.util.Map;

/**
 * Created by A.V.Tsaplin on 14.08.2016.
 */

public class ManagedObjects {

    private String objectClass;
    private Map<String, Object> fields;

    public ManagedObjects(String objectClass, Map<String, Object> fields) {
        this.objectClass = objectClass;
        this.fields = fields;
    }

    public ManagedObjects(String objectClass) {
        this.objectClass = objectClass;
        this.fields = new HashMap<String, Object>();
    }

    public ManagedObjects() {
    }

    public String getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(String objectClass) {
        this.objectClass = objectClass;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

    public void addFields(String name, Object obj) {
        this.fields.put(name, obj);
    }
}
