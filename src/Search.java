import java.util.ArrayList;
import java.util.HashSet;

class Search {
    private ArrayList<State> frontier;
    private HashSet<State> closedSet;
    Search(){
        frontier = new ArrayList<>();
        closedSet = new HashSet<>();
    }
    public State Astarsearch(State initialstate, int heurestic){
        State currentstate = initialstate;
        return currentstate;
    }
}
