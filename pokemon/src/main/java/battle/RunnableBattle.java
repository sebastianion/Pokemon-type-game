package battle;

import commands.Command;
import commands.CommandChooser;
import commands.CommandHelper;
import logger.Logger;
import pokemon.Pokemon;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Threaded class
 * This is where battles come to be
 * Using a semaphore, we can synchronise the Pokemon to our liking
 * the semaphore is even --> the first threaded Pokmon
 * the semaphore is odd  --> the second one
 */
public class RunnableBattle implements Runnable {
    private final AtomicInteger semaphore;
    private final Object lock;
    private final Pokemon poke1;
    private final Pokemon poke2;
    private final int choice;

    public RunnableBattle(AtomicInteger semaphore, Object lock, Pokemon poke1, Pokemon poke2, int choice) {
        this.semaphore = semaphore;
        this.lock = lock;
        this.poke1 = poke1;
        this.poke2 = poke2;
        this.choice = choice;
    }

    // save the commands and apply them when needed
    private void backupCommands(Command command) {
        if (semaphore.get() % 2 == 0)
            CommandHelper.setPrev1(command);
        else
            CommandHelper.setPrev2(command);
    }

    // method called to apply the effects of the commands given to the Pokemon
    // uses the backed up commands
    private void updateBothPokemon() {
        if (!poke1.canDodge() && !poke2.canDodge()) {
            if (CommandHelper.getPrev1() != null)
                CommandHelper.getPrev1().attack(poke1, poke2);
            if (CommandHelper.getPrev2() != null)
                CommandHelper.getPrev2().attack(poke2, poke1);
        } else if (!poke1.canDodge() && CommandHelper.getPrev2() != null) {
            CommandHelper.getPrev2().attack(poke2, poke1);
        } else if (!poke2.canDodge() && CommandHelper.getPrev1() != null)
            CommandHelper.getPrev1().attack(poke1, poke2);
    }

    @Override
    public void run() {
        synchronized (lock) {
            // using the implemented Command design pattern through the CommandChooser object
            // we are able to generate random commands for each Pokemon
            CommandChooser commandChooser = new CommandChooser(poke1);
            // get the Logger instance
            Logger logger = Logger.initialize();

            try {
                // the round number
                int round = 0;
                while (poke1.getHP() > 0 && poke2.getHP() > 0) {

                    // choice represents the Pokemon number (1 being the first one)
                    if (choice == 1) {
                        // wait for the other thread to finish
                        while (semaphore.get() % 2 != 0) {
                            lock.notify();
                            lock.wait();
                        }

                        // get previous round result;
                        // the result is loaded through the first started thread
                        // and only after the semaphore is bigger than 0
                        // (prints results only after the first round finishes)
                        if (semaphore.get() % 2 == 0 && semaphore.get() > 0) {
                            // execute the given commands
                            updateBothPokemon();
                            logger.logRoundResult(poke1, poke2, round);
                        }

                    } else if (choice == 2) {
                        // wait for the other thread to finish
                        while (semaphore.get() % 2 == 0) {
                            lock.notify();
                            lock.wait();
                        }
                    }

                    if (poke1.getHP() > 0 && poke2.getHP() > 0) {
                        // get a random command
                        Command command = commandChooser.randomCommand();

                        // if it is null, the Pokemon is stunned
                        if (command == null) {
                            // increment the round and log
                            round++;
                            logger.logStunnedPokemon(poke1, round);

                            // backup the null command
                            backupCommands(null);

                            semaphore.getAndIncrement();
                            lock.notify();

                            continue;
                        }

                        // set ability features such being able to dodge and
                        // the cooldown value
                        command.setAbilityFeatures(poke1);

                        // backup the command
                        backupCommands(command);

                        // increment the round and log
                        round++;
                        logger.logUsedCommands(poke1, poke2, command, round);
                    }

                    // increment the semaphore
                    semaphore.getAndIncrement();
                    lock.notify();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
