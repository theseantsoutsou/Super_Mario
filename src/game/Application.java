package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.grounds.Dirt;
import game.grounds.Floor;
import game.grounds.Sprout;
import game.grounds.Wall;
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

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));
			Actor toad = new Toad();
			world.addPlayer(toad, gameMap.at(45, 10));
			/* REMOVE ME
			gameMap.at(42,7).addActor(new Koopa());
			gameMap.at(42,9).addItem(new SuperMushroom());
			gameMap.at(42,8).addItem(new Wrench());
			*/
			world.run();

	}
}
