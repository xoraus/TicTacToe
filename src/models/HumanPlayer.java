package models;

import exceptions.InvalidCellException;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Scanner;

@Getter
@Setter
@SuperBuilder
public class HumanPlayer extends Player {
    private int rank;
//    private int age;

    @Override
    public Cell nextMove(Board board) throws InvalidCellException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter the row & col (separated by a space): ");
        String inputLine = sc.nextLine();
        String[] tokens = inputLine.split(" ");
        int row = Integer.parseInt(tokens[0]);
        int col = Integer.parseInt(tokens[1]);

        if (row < 0 || row >= board.getSize()
            && col < 0 || col >= board.getSize() ||
                !board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            throw new InvalidCellException();
        }

        Cell cell =  board.getBoard().get(row).get(col);
        cell.setPlayer(this);
        cell.setCellState(CellState.OCCUPIED);

        return cell;
    }
}
