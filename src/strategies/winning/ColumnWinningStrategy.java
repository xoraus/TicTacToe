package strategies.winning;

import models.Board;
import models.Cell;
import models.CellState;
import models.Player;

public class ColumnWinningStrategy implements WinningStrategies{
    @Override
    public Boolean checkWin(Cell cell, Board board) {
        Player currentPlayer = cell.getPlayer();

        int col = cell.getCol();

        int row = cell.getRow();

        for (int i = 0; i < board.getSize(); i++) {
            Cell currentCell = board.getBoard().get(row).get(i);
            if(currentCell.getCellState().equals(CellState.EMPTY) || !currentCell.getPlayer().equals(currentPlayer)){
                return  false;
            }
        }

        return true;
    }
}
