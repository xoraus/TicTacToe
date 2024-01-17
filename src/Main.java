import controllers.GameController;
import controllers.PlayerController;
import models.*;
import strategies.winning.ColumnWinningStrategy;
import strategies.winning.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome tot the TicTacToe Game!!!");
        System.out.println("===================================");
        System.out.println("Let's start Playing");

        System.out.println("How many players would be playing?");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println("Enter the dimensions for the board");
        int dimension = sc.nextInt();

        List<Player> players = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("Please enter details of Player" + (i + 1));
            System.out.println("Name: ");
            String name = sc.next();
            System.out.println("Sybmol: ");
            String symbol = sc.next();
            System.out.println("Is this player a bot?: (Y/N) ");

            DifficultyLevel difficultyLevel = null;
            if (sc.next().equals("Y")) {
                System.out.println("What is the level of the  bot? (E/M/H) ");
                String level = sc.next();
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
                players.add(PlayerController.createHumanPlayer(name, symbol.charAt(0), difficultyLevel));
            }
        }

        Game game = GameController.initiateGame(dimension, players, List.of(new ColumnWinningStrategy(), new RowWinningStrategy()));

        System.out.println("Are you ready to start the game? ");
        if(sc.next().equals("Y")){
            GameController gc = new GameController(game);
            gc.startGame();
        }

        System.out.println("Game Ended");
    }
}