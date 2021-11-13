import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        State initialstate = new State(200,7,10000);
        Search search = new Search();
        long start = System.currentTimeMillis();
        State terminal = search.Astarsearch(initialstate);
        long end = System.currentTimeMillis();
        if(terminal == null){
            System.out.println("No solution found");
        }
        else{
            State temp = terminal;
            ArrayList<State> path = new ArrayList<>();
            path.add(temp);
            while(temp.getParent() != null){
                path.add(temp.getParent());
                temp = temp.getParent();
            }
            //reverse the path
            Collections.reverse(path);

            for(State item: path){
                System.out.println(item);
            }

            System.out.println();
            System.out.println("Search time:" + (double) (end-start)/1000 + "sec");//total time in seconds
            System.out.println(path.size() - 1 + " travels happened");
        }

    }
}
