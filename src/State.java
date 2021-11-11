import java.util.ArrayList;
import java.util.Objects;
import java.lang.Math;

// N will be assigned to the apostles and cannibals variables when the program is called
// M will be assigned to the boatsize when the program is called
// K will be assigned to the maxtravles when the program is called
enum Position {left,right};
class State{
    // a variable to define how many apostles are in left side
    private int leftapostles;
    // a variable to define how many cannibals are in left side
    private int leftcannibals;
    //the same thing for the right side
    private int rightapostles,rightcannibals = 0;
    // a variable to define the space inside the boat
    private static int boatsize;
    // a variable to define the max travels
    private static int maxtravels;
    //a variable to evaluate the cost of the travels
    private int cost_of_travel = 0;
    // a variable to determine which side are we on
    private Position pos = Position.left;
    // a variable to maintain the parent of the child
    private State parent;
    // cannibals and apostles on the boat
    private int cannibalsonboat;
    private int apostlesonboat;

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

    State(State copystate){
        this.leftapostles = copystate.leftapostles;
        this.leftcannibals = copystate.leftcannibals;
        this.rightcannibals = copystate.rightcannibals;
        this.rightapostles = copystate.rightapostles;
        this.pos = copystate.pos;
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

    public int getMaxtravels() {
        return maxtravels;
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

    public int CostOfTravel() { // see if we can find anything better
        double leftap = leftapostles;
        double leftcan = leftcannibals;
        double bsize = boatsize;
        if(leftcannibals + leftapostles <= boatsize){
            cost_of_travel= 1;
        }
        else if(pos == Position.left){
            cost_of_travel = (int) (2*(Math.ceil((leftap + leftcan)/bsize) - 1)) + 1;

        }
        else if (pos == Position.right) {
            cost_of_travel =  (int) (2*(Math.ceil((leftap + leftcan)/bsize) -1)) + 2;
        }
        return cost_of_travel;
    }

    public boolean isFinal() {
        return leftapostles == 0 && leftcannibals == 0;
    }

    public boolean isValid(State parent) { // this method will check if the state will be valid
        if(leftapostles >= 0 && leftcannibals >= 0 && rightapostles >= 0 && rightcannibals >= 0){
            if((leftapostles == 0 || leftapostles >= leftcannibals) && parent.pos == Position.left ){
                return true;
            }
            else return (rightapostles == 0 || rightapostles >= rightcannibals) && parent.pos == Position.right;
        }
        return false;
    }


    public State AddIfValid(ArrayList<State> children, State newstate){
        if(newstate.isValid(this)){
            newstate.setParent(this);
            newstate.CostOfTravel();
            children.add(newstate);
        }
        return newstate;
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

    /*@Override
    public int identifier() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {

    }

    @Override
    public int hashCode() {
        return ;
    }

    @Override
    public int compareTo(State o) {
        return 0;
    }*/
}
