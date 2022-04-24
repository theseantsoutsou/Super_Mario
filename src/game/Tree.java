package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Random;

public class Tree extends Ground {

    //Private Attributes
    private int age;

    /**
     * Constructor.
     *
     */
    public Tree() {
        super('T');
        this.age = 0;
    }

    /**
     * Ground can also experience the joy of time.
     * Ran every player turn, increments Sapling's Age and tries to spawn a $20 coin.
     * @param location The location of the Sprout
     */
    @Override
    public void tick(Location location) {
        this.age++;
        if (this.age %5 == 0) {
            this.growSprout(location);
        }
        if (!this.die(location)) {
            this.spawn(location);
        }
    }

    public void spawn(Location location) {
        Random r = new Random();
        if (r.nextInt(100) <= 15 && !location.containsAnActor()) {
            location.addActor(new Koopa());
        }
    }

    public void growSprout(Location location) {
        Integer[][] adjacency = {{0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}, {-1,-1}, {-1,0}, {-1,1}};
        Random r = new Random();
        Boolean grown = false;
        int count = 0;

        while (!grown && count < 8) {
            ArrayList<Integer> visited = new ArrayList<>();
            int idx = r.nextInt(8);
            if (!visited.contains(idx)) {
                visited.add(idx);
                count++;
                int x = location.x() + adjacency[idx][0];
                int y = location.y() + adjacency[idx][1];

                if (x != -1 && x != 80 && y != -1 && y != 19 && location.map().at(x,y).getGround() instanceof Dirt) {
                    location.map().at(x,y).setGround(new Sprout());
                    grown = true;
                }
            }
        }
    }

    public Boolean die(Location location) {
        Boolean dead = false;
        Random r = new Random();
        if (r.nextInt(100) <= 20) {
            location.setGround(new Dirt());
            dead = true;
        }
        return dead;
    }
}
