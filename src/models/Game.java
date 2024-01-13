package models;

import strategies.WinningStrategies;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public Board board;
    private List<Player> players;
    private List<Move> moves;
    private int nextPlayerIndex;
    private GameState gameState;
    private Player winner;
    private List<WinningStrategies> winningStrategies;

    public Game(int dimensions, List<Player> players, List<WinningStrategies> winningStrategies) {
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = createBoard(dimensions);
        this.moves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.gameState = GameState.INIT;
    }

    private Board createBoard(int dimensions) {
        return new Board(dimensions);
    }
}
