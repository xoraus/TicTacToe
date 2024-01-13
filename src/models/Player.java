package models;

import lombok.Getter;
import lombok.Setter;

public abstract class Player {
    @Getter @Setter private String name;
    @Getter @Setter private int id;
    @Getter @Setter private char symbol;
    @Getter @Setter private PlayerType playerType;
}
