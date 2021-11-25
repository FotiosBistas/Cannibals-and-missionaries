import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        boolean testmultiplevalues = Boolean.parseBoolean(args[3]);
        int N = Integer.parseInt(args[0]);// number of cannibals and missionaries n+n
        int M = Integer.parseInt(args[1]);// boat size
        int K = Integer.parseInt(args[2]);// max repetitions
        if(!testmultiplevalues){ // try it without the repetitions
            State initialstate = new State(N, M, K);
            Search search = new Search();
            long start = System.currentTimeMillis();
            State terminal = search.Astarsearch(initialstate);
            long end = System.currentTimeMillis();
            if (terminal == null) {
                System.out.println("----------------------------------------------------------");
                System.out.println("No solution found");
                System.out.println("For " + N + " missionaries and " + N + " apostles" + ", " + M + " boat size" + " and " + K + " max repetitions");
                System.out.println((double) (end - start) / 1000 + " secs");
                System.out.println("----------------------------------------------------------");
            } else {
                State temp = terminal;
                ArrayList<State> path = new ArrayList<>();
                path.add(temp);
                while (temp.getParent() != null) {
                    path.add(temp.getParent());
                    temp = temp.getParent();
                }
                //reverse the path
                Collections.reverse(path);
                    /*for(State item: path){
                        System.out.println(item);
                    }*/
                System.out.println("Solution found");
                System.out.println("For " + N + " missionaries and" + N + " apostles" + ", " + M + " boat size" + " and " + K + " max repetitions");
                System.out.println("Search time:" + (double) (end - start) / 1000 + "sec");//total time in seconds
                System.out.println(path.size() - 1 + " travels happened");
            }
        }
        else {
            for (int i = 1; i <= N; i++) {
                for (int j = 2; j <= M; j++) {
                    State initialstate = new State(i, j, 10000);
                    Search search = new Search();
                    long start = System.currentTimeMillis();
                    State terminal = search.Astarsearch(initialstate);
                    long end = System.currentTimeMillis();
                    if (terminal == null) {
                        System.out.println("----------------------------------------------------------");
                        System.out.println("No solution found");
                        System.out.println("For " + i + " missionaries and" + i + " apostles" + ", " + j + " boat size" + " and " + K + " max repetitions");
                        System.out.println((double) (end - start) / 1000 + " secs");
                        System.out.println("----------------------------------------------------------");
                    } else {
                        State temp = terminal;
                        ArrayList<State> path = new ArrayList<>();
                        path.add(temp);
                        while (temp.getParent() != null) {
                            path.add(temp.getParent());
                            temp = temp.getParent();
                        }
                        //reverse the path
                        Collections.reverse(path);
                    /*for(State item: path){
                        System.out.println(item);
                    }*/
                        System.out.println("----------------------------------------------------------");
                        System.out.println("Solution found");
                        System.out.println("For " + i + " missionaries and " + i + " apostles" + ", " + j + " boat size" + " and " + K + " max repetitions");
                        System.out.println("Search time:" + (double) (end - start) / 1000 + "sec");//total time in seconds
                        System.out.println(path.size() - 1 + " travels happened");
                        System.out.println("----------------------------------------------------------");
                    }
                }

            }
        }
    }
}
