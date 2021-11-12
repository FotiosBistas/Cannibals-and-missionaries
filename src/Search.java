import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Search {
    private ArrayList<State> frontier;
    private HashSet<State> closedSet;
    Search(int maxtravels){
        frontier = new ArrayList<>();
        closedSet = new HashSet<>();
    }
    public State Astarsearch(State initialstate){
        State currentstate = initialstate;
        frontier.add(initialstate);
        while(!frontier.isEmpty()){
            State bestchild = this.frontier.remove(0);
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
                Collections.sort(this.frontier);
            }

            currentstate = bestchild;
        }
        return null;
    }
}
