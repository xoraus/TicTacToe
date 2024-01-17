package controllers;

import models.*;

public class PlayerController {
    public static Player createHumanPlayer(String name, char symbol, DifficultyLevel difficultyLevel){
        return PlayerHuman.builder()
                .age(12)
                .playerType(PlayerType.HUMAN)
                .name(name)
                .symbol(symbol)
                .build();
    }

    public static Player createBot(String name, char symbol, DifficultyLevel difficultyLevel){
        return PlayerBot.builder()
                .name(name)
                .symbol(symbol)
                .difficultyLevel(difficultyLevel)
                .build();
    }

    private PlayerController(){

    }


}
