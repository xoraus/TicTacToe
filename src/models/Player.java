package models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class Player {
     private String name;
     private int id;
     private char symbol;
     private PlayerType playerType;

     public abstract Cell nextMove(Board board);

}
