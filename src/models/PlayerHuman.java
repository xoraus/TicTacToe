package models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Scanner;

@SuperBuilder
public class PlayerHuman extends Player {
    private int rank;
    private int age;

    @Override
    public Cell nextMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Pleas enter the row:");
        int row = sc.nextInt();

        System.out.println("Please enter the col");
        int col = sc.nextInt();

        if(!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
            throw new IllegalArgumentException("Cells not avilable");
        }

        Cell cell = board.getBoard().get(row).get(col);
        cell.setPlayer(this);
        cell.setCellState(CellState.OCCUPIED);

        return cell;

    }
}
