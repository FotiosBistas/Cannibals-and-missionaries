import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Search {
    private ArrayList<State> frontier;
    private HashSet<State> closedSet;
    Search(){
        frontier = new ArrayList<>();
        closedSet = new HashSet<>();
    }
    public State Astarsearch(State initialstate){
        State currentstate = initialstate;
        frontier.add(initialstate);
        while(!frontier.isEmpty()){
            State bestchild = Collections.min(this.frontier);
             this.frontier.remove(bestchild);
            //TODO if costs are equal then randomize which one you choose
            if(bestchild.getMaxtravels() == 0){
                return null;
            }
            if(bestchild.isFinal()){
                return bestchild;
            }
            if (!this.closedSet.contains(bestchild)) {
                this.closedSet.add(bestchild);
                this.frontier.addAll(bestchild.getChildren());
            }

            currentstate = bestchild;
        }
        return null;
    }
}
