package battle;

import pokemon.Pokemon;
import trainer.Trainer;
import logger.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The battle between two Pokemon
 */
public class Battle {
    Pokemon pokemon1;
    Pokemon pokemon2;
    List<Trainer> trainers;
    AtomicInteger atomicInteger = new AtomicInteger(0);
    Object lock = new Object();

    public Battle(Pokemon poke1, Pokemon poke2, List<Trainer> trainers) {
        this.pokemon1 = poke1;
        this.pokemon2 = poke2;
        this.trainers = trainers;
    }

    // start the battle
    public void start() {
        // gen an instance of the Logger object
        Logger logger = Logger.initialize();

        logger.logFightStarted(pokemon1, pokemon2);

        // create 2 objects and thread them to simulate the battle
        RunnableBattle firstMove = new RunnableBattle(atomicInteger, lock, pokemon1, pokemon2, 1);
        RunnableBattle secondMove = new RunnableBattle(atomicInteger, lock, pokemon2, pokemon1, 2);
        pokemon1.setHPbackup(pokemon1.getHP());
        pokemon2.setHPbackup(pokemon2.getHP());

        // the two threads
        Thread t1 = new Thread(firstMove);
        Thread t2 = new Thread(secondMove);

        // start the threads
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // after both threads are dead, decide the winner of the battle,
        // if at least one Pokemon is alive
        int winner = 0;
        if (!t1.isAlive() && !t2.isAlive()) {
            if (pokemon1.getHP() <= 0 && pokemon2.getHP() <= 0)
                logger.logBattleDraw();
            else if (pokemon1.getHP() > 0)
                winner = -1;
            else if (pokemon2.getHP() > 0)
                winner = 1;

            // reset health and other attributes after the battle ends
            pokemon1.resetStats();
            pokemon2.resetStats();

            // winner's Pokemon gets upgraded
            switch (winner) {
                case -1 -> {
                    pokemon1.increaseAll();
                    logger.logPokemonUpgraded();
                    logger.logState(pokemon1);
                    logger.logBattleWonBy(trainers.get(0).getName());
                }
                case 1 -> {
                    pokemon2.increaseAll();
                    logger.logPokemonUpgraded();
                    logger.logState(pokemon2);
                    logger.logBattleWonBy(trainers.get(1).getName());
                }
            }
        }
    }
}
