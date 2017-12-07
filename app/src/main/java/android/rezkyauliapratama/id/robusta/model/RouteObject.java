package android.rezkyauliapratama.id.robusta.model;

/**
 * Created by Rezky Aulia Pratama on 9/7/2017.
 */

import java.util.List;

public class RouteObject {
    private List<LegsObject> legs;
    public RouteObject(List<LegsObject> legs) {
        this.legs = legs;
    }
    public List<LegsObject> getLegs() {
        return legs;
    }
}