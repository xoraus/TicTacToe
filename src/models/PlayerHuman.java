package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerHuman extends Player {
    private int rank;
    private int age;
}
