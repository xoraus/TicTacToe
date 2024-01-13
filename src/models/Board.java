package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int size) {
        this.size = size;
        createBoard(size);
    }

    public void createBoard(int size) {
        this.board = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print("|\t");
            for (int j = 0; j < size; j++) {
                if (this.board.get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    System.out.print("|\t");
                } else {
                    System.out.print(this.board.get(i).get(j).getPlayer().getSymbol() + "|\t");
                }
            }
            System.out.println();
        }
    }
}