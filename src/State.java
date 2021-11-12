import java.util.ArrayList;
import java.util.Objects;
import java.lang.Math;

// N will be assigned to the apostles and cannibals variables when the program is called
// M will be assigned to the boatsize when the program is called
// K will be assigned to the maxtravles when the program is called
enum Position {left,right};
class State implements Comparable<State>{
    // a variable to define how many apostles are in left side
    private int leftapostles;
    // a variable to define how many cannibals are in left side
    private int leftcannibals;
    //the same thing for the right side
    private int rightapostles,rightcannibals = 0;
    // a variable to define the space inside the boat
    private static int boatsize;
    //a variable to represent the max travels
    private int maxtravels;
    //a variable to evaluate the cost of the travels
    private double cost_of_travel = 0;
    // a variable to determine which side are we on
    private Position pos = Position.left;
    // a variable to maintain the parent of the child
    private State parent;
    // cannibals and apostles on the boat

    State(int N,int M,int K){
        // we have the same number of cannibals and missionaries at the start they all are in the left side
        leftapostles = N;
        leftcannibals = N;
        boatsize = M;
        maxtravels = K;
    }

    State(int leftapostles,int leftcannibals,int rightapostles,int rightcannibals, Position pos){
        this.leftapostles = leftapostles;
        this.leftcannibals = leftcannibals;
        this.rightcannibals = rightcannibals;
        this.rightapostles = rightapostles;
        this.pos = pos;
    }

    State(State copystate){ // copy constructor
        this.leftapostles = copystate.leftapostles;
        this.leftcannibals = copystate.leftcannibals;
        this.rightcannibals = copystate.rightcannibals;
        this.rightapostles = copystate.rightapostles;
        this.pos = copystate.pos;
        this.parent = copystate.parent;
    }

    public int getMaxtravels() {
        return maxtravels;
    }

    public void setMaxtravels(int maxtravels) {
        this.maxtravels = maxtravels;
    }

    public int getLeftapostles() {
        return leftapostles;
    }

    public void setLeftapostles(int leftapostles) {
        this.leftapostles = leftapostles;
    }

    public int getLeftcannibals() {
        return leftcannibals;
    }

    public void setLeftcannibals(int leftcannibals) {
        this.leftcannibals = leftcannibals;
    }

    public int getRightapostles() {
        return rightapostles;
    }

    public void setRightapostles(int rightapostles) {
        this.rightapostles = rightapostles;
    }

    public int getRightcannibals() {
        return rightcannibals;
    }

    public void setRightcannibals(int rightcannibals) {
        this.rightcannibals = rightcannibals;
    }

    public int getBoatsize() {
        return boatsize;
    }

    public double getCost_of_travel() {
        return cost_of_travel;
    }

    public void setCost_of_travel(int cost_of_travel) {
        this.cost_of_travel = cost_of_travel;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setParent(State parent){this.parent = parent;}

    public State getParent(){return parent;}

    public double CostOfTravel() { // this is the heuristic function
        double leftap = leftapostles;
        double leftcan = leftcannibals;
        double bsize = boatsize;
        if(leftcannibals + leftapostles <= boatsize){
            cost_of_travel= 1;
        }
        else if(pos == Position.left){
            cost_of_travel = this.parent.cost_of_travel + 2*((leftap + leftcan)/bsize - 1) + 1;

        }
        else if (pos == Position.right) {
            cost_of_travel = this.parent.cost_of_travel + 2*((leftap + leftcan)/bsize - 1) + 2;
        }
        return cost_of_travel;
    }

    public boolean isFinal() {
        return leftapostles == 0 && leftcannibals == 0;
    }

    public boolean isValid(State parent) { // this method will check if the state will be valid
        if(leftapostles >= 0 && leftcannibals >= 0 && rightapostles >= 0 && rightcannibals >= 0){//this check is ensuring that the numbers will not be negative
            if((leftapostles == 0 || leftapostles >= leftcannibals) && parent.pos == Position.left ){//this ensures that leftapostles will be greater than the number of leftcannibals,leftapostles == 0 allows them to be equal to zero
                return true;
            }
            else return (rightapostles == 0 || rightapostles >= rightcannibals) && parent.pos == Position.right; // this ensures that rightapostles will be greater than the number of rightcannibals,rightapostles == 0 allows them to be equal to zero
        }
        return false;
    }


    public State AddIfValid(ArrayList<State> children, State newstate){//checks if the newstate if valid
        if(newstate.isValid(this)){
            newstate.setParent(this);
            newstate.setMaxtravels(this.getMaxtravels()-1);
            newstate.CostOfTravel();//calculates the value of the heuristic function
            children.add(newstate);//adds to the front
        }
        return newstate;
    }

    @Override
    public String toString() {
        return "State{" +
                "leftapostles=" + leftapostles +
                ", leftcannibals=" + leftcannibals +
                ", rightapostles=" + rightapostles +
                ", rightcannibals=" + rightcannibals +
                ", maxtravels=" + maxtravels +
                ", cost_of_travel=" + cost_of_travel +
                ", pos=" + pos +
                ", parent=" + parent +
                '}';
    }

    public ArrayList<State> getChildren() {
        ArrayList<State> children = new ArrayList<>();
        if(Position.left == pos) {
            for (int j = 1; j <= boatsize ; j++) {
                for (int i = 0; i <= j; i++) {// boat size > = 2 else the problem won't work
                    int mis = j - i;
                    int can = i;
                    // we allow states like 1 missionary and 2 cannibals with boat size three cause we can say leave 1 missionary and 1 cannibal to the right and return with 1 cannibal  to the left
                    AddIfValid(children, new State(leftapostles - mis, leftcannibals - can, rightapostles + mis, rightcannibals + can, Position.right));
                }
            }
        }
        else {
            for (int j = 1; j <= boatsize ; j++) {
                for (int i = 0; i <= j; i++) {// boat size > = 2 else the problem won't work
                    int mis = j - i;
                    int can = i;
                    // we allow states like 1 missionary and 2 cannibals with boat size three cause we can say leave 1 missionary and 1 cannibal to the right and return with 1 cannibal  to the left
                    AddIfValid(children, new State(leftapostles + mis, leftcannibals + can, rightapostles - mis, rightcannibals - can, Position.left));
                }
            }

        }
        return children;
    }


    public int identifier() { //TODO find a unique
        double bsize = boatsize;
        if (Position.left == this.pos){
            return (int) Math.floor(Math.pow(((this.leftapostles + this.leftcannibals)/bsize),2));
        }else{
            return (int) Math.floor(Math.pow(((this.rightapostles + this.rightcannibals)/bsize),2));
        }

    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof State)){
            return false;
        }
        else if(obj == this){
            return true;
        }
        else{
            State o = (State)obj;
            if(o.pos == this.pos && o.leftapostles == this.leftapostles && o.leftcannibals == this.leftcannibals && o.rightapostles == this.rightapostles && o.rightcannibals == this.rightcannibals){
                return true;
            }
        }

        return false;

    }

    @Override
    public int hashCode()//we need this for the closed set of A*
    {
        if (Position.left == this.pos){
            return this.leftapostles + this.leftcannibals + this.identifier();
        }
        else{
            return this.rightapostles + this.rightcannibals + this.identifier();

        }
    }

    @Override
    public int compareTo(State o) { // we take -1 for the normal return 1 case and we take 1 for the normal return -1 in order Collections.sort(this.frontier); to sort them in increasing order
        if(o.cost_of_travel == this.cost_of_travel){
            return 0;
        }
        else if(o.cost_of_travel > this.cost_of_travel){
            return -1;
        }
        else{
            return 1;
        }
    }
}
