package services;

import models.*;
import strategies.winning.WinningStrategies;

public class GameService {
    private Game game;
    public GameService(Game game){
        this.game = game;
    }


    public void executeNextMoves(){
        while(checkEmptySpace()){
            Player currentPlayer = game.getPlayers().get(game.getNextPlayerIndex());
            System.out.printf("It's %s move: \n", currentPlayer.getName());
            Cell cell = currentPlayer.nextMove(game.getBoard());
            game.board.print();

            for(WinningStrategies winningStrategies: game.getWinningStrategies()){
                if(winningStrategies.checkWin(cell, game.getBoard())){
                    game.setGameState(GameState.SUCCESS);
                    System.out.printf("Player %s won!!! " , currentPlayer.getName());
                    return;
                }
            }

            this.game.setNextPlayerIndex(
                    (game.getNextPlayerIndex()  + 1) % game.getPlayers().size());
        };

    }

    private boolean checkEmptySpace(){
        Board board = game.getBoard();

        int n = board.getSize();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    return true;
                }
            }
        }

        return false;
    }
}
