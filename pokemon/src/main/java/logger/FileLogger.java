package logger;

import commands.Command;
import pokemon.Pokemon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Log to file
 */
public class FileLogger implements ILogger {
    // output file name
    private static final String fileName = "log.txt";
    private static BufferedWriter writer;

    // initialize a new writer
    static {
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // close the writer
    public static void closeWriter() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logFightStarted(Pokemon poke1, Pokemon poke2) {
        try {
            writer.append("\n" + Thread.currentThread().getName() + "\n" +
                    "----------- Fight started between " +
                    poke1.getName() + " and " + poke2.getName() + " -----------\n" +
                    poke1 + "\n" + poke2 + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void logRoundResult(Pokemon poke1, Pokemon poke2, int round) {
        try {
            writer.append("\n" + Thread.currentThread().getName() +
                    "\n---------------------- ROUND " + round +
                    " result ----------------------\n\t" +
                    poke1.state() + "\n\t<--->\n\t" + poke2.state() + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logStunnedPokemon(Pokemon pokemon, int round) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();

        try {
            writer.append(format.format(time) + " " + Thread.currentThread().getName() +
                    "\tROUND " + round + ": " + pokemon.getName() +
                    " is stunned and did absolutely nothing\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logUsedCommands(Pokemon poke1, Pokemon poke2, Command command, int round) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();

        try {
            writer.append(format.format(time) + " " + Thread.currentThread().getName() +
                    "\tROUND " + round + ": " + poke1.getName() + " used " +
                    command.getType() + command.getDamage() + " on " + poke2.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logBattleDraw() {
        try {
            writer.append(Thread.currentThread().getName() +
                    "\n----------- DRAW -----------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logBattleWonBy(String name) {
        try {
            writer.append(Thread.currentThread().getName() +
                    "\n----------- " + name + " WON -----------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logState(Pokemon pokemon) {
        try {
            writer.append(pokemon.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logAdventureOver() {
        try {
            writer.append("----------- ADVENTURE OVER -----------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logPokemonUpgraded() {
        try {
            writer.append("----------- POKEMON UPGRADED -----------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
