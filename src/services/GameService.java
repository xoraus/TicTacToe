package services;

import constants.Constants;
import exceptions.InvalidCellException;
import models.*;
import strategies.winning.WinningStrategy;

import java.util.List;
import java.util.Scanner;

public class GameService {

    private Game game;

    public GameService(Game game) {
        this.game = game;
    }

    public void executeNextMoves() {
        System.out.println();
        System.out.println("============ TicTacToe BOARD ==============");
        System.out.println();
        game.getBoard().print();
        while(checkEmptySpace()) {
            Player currentPlayer = game.getPlayers().get(game.getNextPlayerIndex());
            System.out.println();
            System.out.println("==========================================");
            System.out.printf("\uD83E\uDD77\uD83C\uDFFB It's %s move...\n", currentPlayer.getName());
            System.out.println();

            if(currentPlayer.getPlayerType().equals(PlayerType.BOT)){
                System.out.printf("\uD83E\uDD16 %s is thinking \uD83E\uDD14 about next move ", currentPlayer.getName());
                System.out.println();
                try {
                    long secondsToSleep = 2;
                    Thread.sleep(secondsToSleep * 1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }

            Cell cell = null;
            try {
                cell = currentPlayer.nextMove(game.getBoard());
            } catch (InvalidCellException e) {
                System.out.println("Please re-check the cell you entered!");
                continue;
            }

            System.out.println("==========================================");
            System.out.println();

            game.getMoves().add(new Move(cell, currentPlayer));
            game.getBoard().print();

            for (WinningStrategy winningStrategy: game.getWinningStrategies()) {
                if (winningStrategy.checkWin(cell, game.getBoard())) {
                    game.setGameState(GameState.SUCCESS);
                    System.out.println("==========================================");
                    System.out.printf("GAME ENDED: Player %s won!!! \uD83C\uDF89 \uD83E\uDD73 ", currentPlayer.getName());
                    return;
                }
            }

            if (currentPlayer.getPlayerType().equals(PlayerType.HUMAN) && Constants.undoFeature) {
                askIfPlayerWantsToUndo();
            }

            this.game.setNextPlayerIndex(
                    (game.getNextPlayerIndex() + 1) % game.getPlayers().size()
            );

        }

        if (!game.getGameState().equals(GameState.SUCCESS)) {
            game.setGameState(GameState.DRAW);
            System.out.println("No more cells to play, and hence game draw.");
        }
    }

    private void askIfPlayerWantsToUndo() {
        System.out.print("Would you like to undo the last move? ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if (response.equals("Y")) {
            undoLastMove();
            game.board.print();
        }
    }

    private void undoLastMove() {
        System.out.println("Removing the last move");
        List<Move> moves = game.getMoves();
        // System.out.println(moves.get(moves.size() - 1));
        Cell cell = moves.get(moves.size() - 1).getCell();

        moves.remove(moves.size() - 1);
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        game.setNextPlayerIndex(game.getNextPlayerIndex() - 1);

    }

    private boolean checkEmptySpace() {
        Board board = game.getBoard();

        int n = board.getSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    return true;
                }
            }
        }
        return false;
    }


}
