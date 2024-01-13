import models.Game;
import models.Player;
import models.PlayerHuman;
import models.PlayerType;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player = new PlayerHuman();
        player.setPlayerType(PlayerType.HUMAN);

        Game game = new Game(3, List.of(player), List.of());
        game.board.print();

    }
}