package dohi.programming.assignment.framework;

/**
 * Back-end URI collection
 */
public class V1 {

    private static final String URI_BASE = "/rest/v1/";

    private static final String URI_CREATE = "create";
    public static final String URI_CREATE_ABSOLUTE = URI_BASE + URI_CREATE;

    private static final String URI_UPDATE = "update";
    public static final String URI_UPDATE_ABSOLUTE = URI_BASE + URI_UPDATE;

    private static final String URI_DELETE = "delete";
    public static final String URI_DELETE_ABSOLUTE = URI_BASE + URI_DELETE;

    private static final String URI_RETRIEVE = "retrieve";
    public static final String URI_RETRIEVE_ABSOLUTE = URI_BASE + URI_RETRIEVE;

    private static final String URI_RETRIEVE_ALL = "retrieveAll";
    public static final String URI_RETRIEVE_ALL_ABSOLUTE = URI_BASE + URI_RETRIEVE_ALL;
    private V1() {
    }
}
