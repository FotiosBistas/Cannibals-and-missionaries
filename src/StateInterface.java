import java.util.ArrayList;

public interface StateInterface {
    public int CostOfTravel();
    public boolean isFinal();
    public boolean isValid(State parent);
    State AddIfValid(ArrayList<State> children, State obj);
    public ArrayList getChildren();


}
