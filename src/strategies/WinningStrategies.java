package strategies;

import models.Board;
import models.Cell;

public interface WinningStrategies {
    Boolean checkWin(Cell cell, Board board);

}
