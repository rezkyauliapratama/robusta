package android.rezkyauliapratama.id.robusta.model;

/**
 * Created by Rezky Aulia Pratama on 9/7/2017.
 */
import java.util.List;

public class LegsObject {

    private List<StepsObject> steps;

    private DistanceObject distance;

    private DurationObject duration;

    public LegsObject(DurationObject duration, DistanceObject distance, List<StepsObject> steps) {
        this.duration = duration;
        this.distance = distance;
        this.steps = steps;
    }

    public List<StepsObject> getSteps() {
        return steps;
    }

    public DistanceObject getDistance() {
        return distance;
    }

    public DurationObject getDuration() {
        return duration;
    }
}
