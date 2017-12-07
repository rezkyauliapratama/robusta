package android.rezkyauliapratama.id.robusta.model;

import java.util.List;

/**
 * Created by Rezky Aulia Pratama on 9/7/2017.
 */

public class DirectionObject {
    private List<RouteObject> routes;
    private String status;
    public DirectionObject(List<RouteObject> routes, String status) {
        this.routes = routes;
        this.status = status;
    }
    public List<RouteObject> getRoutes() {
        return routes;
    }
    public String getStatus() {
        return status;
    }
}
