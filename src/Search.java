import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

class Search {
    private ArrayList<State> frontier;
    private HashSet<State> closedSet;
    // a variable to define the max travels
    private static int maxtravels;
    Search(int maxtravels){
        frontier = new ArrayList<>();
        closedSet = new HashSet<>();
        this.maxtravels = maxtravels;
    }
    public int getMaxtravels(){
        return maxtravels;
    }
    public State Astarsearch(State initialstate){
        State currentstate = initialstate;
        frontier.add(initialstate);
        while(!frontier.isEmpty()){
            State bestchild = this.frontier.remove(0);

            if(bestchild.isFinal()){
                return bestchild;
            }
            if (!this.closedSet.contains(bestchild)) {
                this.closedSet.add(bestchild);
                this.frontier.addAll(bestchild.getChildren());
                Collections.sort(this.frontier);
                maxtravels--;
            }

            currentstate = bestchild;
        }
        return null;
    }
}
