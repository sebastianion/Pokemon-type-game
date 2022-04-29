import battle.Battle;
import logger.Logger;
import pokemon.Pokemon;
import pokemon.PokemonBuilder;
import trainer.Trainer;
import trainer.TrainerBuilder;

import java.util.*;

/**
 * Arena class, simulates 4 adventures
 */
public class Arena {
    private final List<Trainer> trainers;
    private Pokemon neutrel1;
    private Pokemon neutrel2;
    // get an instance for the logger
    private static Logger logger = Logger.initialize();

    public Arena(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    // enum for the possible events that may happen in an adventure
    private enum Event {
        vsNeutrel1, vsNeutrel2, PvP
    }

    // battle between a trainer and a neutrel Pokemon
    private void startBattleNeutrels(Trainer trainer, int pokeNo, Pokemon neutrel) {
        new Battle(trainer.getPokemons().get(pokeNo), neutrel,
                Arrays.asList(trainer, new TrainerBuilder().withName(neutrel.getName()).build()))
                .start();
    }

    // battle between two trainers
    private void startBattleTrainers(List<Trainer> trainers, int pokeNo) {
        new Battle(trainers.get(0).getPokemons().get(pokeNo),
                trainers.get(1).getPokemons().get(pokeNo), trainers)
                .start();

        logger.logAdventureOver();
    }

    // method to choose a random possible event
    private String eventChooser(List<Trainer> trainers, int pokeNo, Pokemon neutrel1, Pokemon neutrel2) {
        // instruction generates an event from the Event enum
        Event e = Event.values()[new Random().nextInt(Event.values().length)];

        // in case the event points to a battle with neutrels, both
        // trainers will have to take part in it and fight with a neutrel;
        // in case of a PvP, return "IT'S TIME"
        switch (e) {
            case vsNeutrel1:
                startBattleNeutrels(trainers.get(0), pokeNo, neutrel1);
                startBattleNeutrels(trainers.get(1), pokeNo, neutrel1);
                return "Not yet...";
            case vsNeutrel2:
                startBattleNeutrels(trainers.get(0), pokeNo, neutrel2);
                startBattleNeutrels(trainers.get(1), pokeNo, neutrel2);
                return "Not yet...";
            case PvP:
                return "IT'S TIME";
        }

        return "Not yet...";
    }

    // simulate an adventure;
    // the battle between trainers is the last event of an adventure
    private void adventure(int pokeNo) {
        // execute battles with neutrels until the PvP option gets selected
        while (true)
            if (eventChooser(trainers, pokeNo, neutrel1, neutrel2).equals("IT'S TIME"))
                break;

        // if the Pokemon are not dead from the fights with neutrels (unlikely to ever happen),
        // start the final battle of the adventure
        if (trainers.get(0).getPokemons().get(pokeNo).getHP() > 0 &&
                trainers.get(1).getPokemons().get(pokeNo).getHP() > 0)
            startBattleTrainers(trainers, pokeNo);
    }

    // start the arena
    public void start() {
        // two *very easy to defeat* Pokemon have spawned!
        neutrel1 = new PokemonBuilder()
                .withName("Neutrel1")
                .withHP(10)
                .withAttack(3)
                .withSpecialAttack(-1)
                .withDefence(1)
                .withSpecialDefence(1)
                .build();

        neutrel2 = new PokemonBuilder()
                .withName("Neutrel2")
                .withHP(20)
                .withAttack(6)
                .withSpecialAttack(-1)
                .withDefence(1)
                .withSpecialDefence(1)
                .build();

        // each Pokemon equips their items
        for (Trainer trainer : trainers)
            for (Pokemon pokemon : trainer.getPokemons())
                pokemon.equipItems();

        // first three adventures
        adventure(0);
        adventure(1);
        adventure(2);

        // compute the trainers' best Pokemon
        Pokemon bestPokemon1 = trainers.get(0).bestPokemon();
        Pokemon bestPokemon2 = trainers.get(1).bestPokemon();

        // final adventure
        new Battle(bestPokemon1, bestPokemon2, trainers).start();
        logger.logAdventureOver();
    }
}
