package models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import strategies.botplaying.BotPlayingStrategy;
import strategies.botplaying.EasyBotPlayingStrategy;

import java.util.Scanner;

@Getter
@Setter
@SuperBuilder
public class PlayerBot extends Player{
    private DifficultyLevel difficultyLevel;
    BotPlayingStrategy botPlayingStrategy = new EasyBotPlayingStrategy();

//    public void setDifficultyLevel(DifficultyLevel difficultyLevel){
//        this.difficultyLevel = difficultyLevel;
//    }

    @Override
    public Cell nextMove(Board board) {
        botPlayingStrategy = new EasyBotPlayingStrategy();
        Cell cell = botPlayingStrategy.suggestMove(board);

        cell.setPlayer(this);
        cell.setCellState(CellState.OCCUPIED);

        return cell;
    }
}
