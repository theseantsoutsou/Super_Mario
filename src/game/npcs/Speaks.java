package game.npcs;

import java.util.ArrayList;

public interface Speaks {
    ArrayList<String> monologues = new ArrayList<>();

    default ArrayList<String> getMonologues(){
        return monologues;
    }
}
