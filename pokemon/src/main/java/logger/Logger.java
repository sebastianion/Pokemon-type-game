package logger;

import commands.Command;
import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Logger class implemented using the observer and singleton design patterns
 */
public class Logger {
    public static Logger instance = null;
    private static Lock lock = new ReentrantLock();
    private List<ILogger> observers;

    private Logger() {
        observers = new ArrayList<>();
    }

    // get an instance
    public static Logger initialize() {
        lock.lock();
        try {
            if (instance == null)
                instance = new Logger();
        } finally {
            lock.unlock();
        }
        return instance;
    }

    public void addLoggingRoutine(ILogger observer) {
        if (observers.contains(observer))
            return;
        observers.add(observer);
    }

    public void logFightStarted(Pokemon poke1, Pokemon poke2) {
        for (ILogger observer : observers) {
            observer.logFightStarted(poke1, poke2);
        }
    }

    public void logRoundResult(Pokemon poke1, Pokemon poke2, int round) {
        for (ILogger observer : observers) {
            observer.logRoundResult(poke1, poke2, round);
        }
    }

    public void logStunnedPokemon(Pokemon pokemon, int round) {
        for (ILogger observer : observers) {
            observer.logStunnedPokemon(pokemon, round);
        }
    }

    public void logUsedCommands(Pokemon poke1, Pokemon poke2, Command command, int round) {
        for (ILogger observer : observers) {
            observer.logUsedCommands(poke1, poke2, command, round);
        }
    }

    public void logBattleDraw() {
        for (ILogger observer : observers) {
            observer.logBattleDraw();
        }
    }

    public void logBattleWonBy(String name) {
        for (ILogger observer : observers) {
            observer.logBattleWonBy(name);
        }
    }

    public void logState(Pokemon pokemon) {
        for (ILogger observer : observers) {
            observer.logState(pokemon);
        }
    }

    public void logAdventureOver() {
        for (ILogger observer : observers) {
            observer.logAdventureOver();
        }
    }

    public void logPokemonUpgraded() {
        for (ILogger observer : observers) {
            observer.logPokemonUpgraded();
        }
    }
}
