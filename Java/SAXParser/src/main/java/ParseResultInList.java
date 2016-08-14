import java.util.List;

/**
 * Created by A.V.Tsaplin on 14.08.2016.
 */
public abstract class ParseResultInList {

    private static List<ManagedObjects> parseResultList;

    public static List<ManagedObjects> getParseResultList() {
        return parseResultList;
    }

    public static void setParseResultList(List<ManagedObjects> parseResultList) {
        ParseResultInList.parseResultList = parseResultList;
    }
}
