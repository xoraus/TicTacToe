import controllers.GameController;
import controllers.PlayerController;
import constants.Constants;
import models.DifficultyLevel;
import models.Game;
import models.Player;
import strategies.winning.ColumnWinningStrategy;
import strategies.winning.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static boolean undo = false;
    public static void main(String[] args) {
        System.out.println("========== Welcome to the Tic Tac Toe ==========");
        System.out.print("How many players would be playing? ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.print("Enter the row dimension for the board (3/4/5...) ");
        int dimension = sc.nextInt();

        List<Player> players = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Please enter details of Player " + (i + 1));
            System.out.print("Player Name: ");
            String name = sc.next();
            System.out.print("Player Symbol: ");
            String symbol = sc.next();
            System.out.print("Is this player a BOT? (Y/N)? ");
            if (sc.next().equals("Y")) {
                System.out.print("What's the level of the bot? (E/M/H) ");
                String level = sc.next();
                DifficultyLevel difficultyLevel;
                switch (level) {
                    case "E":
                        difficultyLevel = DifficultyLevel.EASY;
                        break;
                    case "M":
                        difficultyLevel = DifficultyLevel.MEDIUM;
                        break;
                    default:
                        difficultyLevel = DifficultyLevel.HARD;
                }
                players.add(PlayerController.createBot(name, symbol.charAt(0), difficultyLevel));
            } else {
                players.add(PlayerController.createHumanPlayer(name, symbol.charAt(0)));
            }
        }

        System.out.print("Do you want UNDO feature in the game: Y/N ");
        String input = sc.next();
        if(input.equals("Y")){
            Constants.undoFeature = true;
        }

        assert players.stream().map(player -> player.getSymbol()).
                collect(Collectors.toSet()).size() == players.size();

        Game game = GameController.initiateGame(
                dimension,
                players,
                List.of(new ColumnWinningStrategy(), new RowWinningStrategy()));

        System.out.print("Press Y to start the game!!! ");
        if (sc.next().equals("Y") || sc.next().equals("y")) {
            GameController gc = new GameController(game);
            gc.startGame();
        }

        System.out.println("Game ended.");

    }
}