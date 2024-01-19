package strategies.winning;

import models.Board;
import models.Cell;
import models.Player;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy {


    private final HashMap<Player, HashMap<Integer, Integer>> counts = new HashMap<>();
    @Override
    public boolean checkWin(Cell cell, Board board) {
        Player currentPlayer = cell.getPlayer();
        int col = cell.getCol();

        if (!counts.containsKey(currentPlayer)) {
            counts.put(currentPlayer, new HashMap<>());
        }

        if (!counts.get(currentPlayer).containsKey(col)) {
            counts.get(currentPlayer).put(col, 0);
        }

        int cnt = counts.get(currentPlayer).get(col);
        counts.get(currentPlayer).put(col, cnt + 1);
        return cnt + 1 == board.getSize();
    }
}