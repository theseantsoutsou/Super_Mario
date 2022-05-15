package game;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.grounds.*;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;
import game.npcs.Goomba;
import game.npcs.Koopa;
import game.npcs.Toad;

/**
 * The Application class is the main class that drives the Mario World game.
 *
 * @author FIT2099 Teaching Team, Connor Gibson, Shang-Fu Tsou, Lucus Choy
 * @version 2.0
 * @since 02-May-2022
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new WarpPipe());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............C...............................##......................+..........",
				"...............................................#................................",
				"......................................C.........#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+......................C.......+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###.........C....................",
				"........................+........................##.............+...............",
				".......................................C...........#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);
			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));
			Actor toad = new Toad();
			gameMap.addActor(toad, gameMap.at(44, 10));
			world.run();

	}
}
