import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        State newstate = new State(4,5,100);
        newstate.getChildren();
        long end = System.currentTimeMillis();

    }
}
