package logger;

import commands.Command;
import pokemon.Pokemon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import org.slf4j.LoggerFactory;  <--| logging alternative
// import org.slf4j.Logger;         <--|

/**
 * Log to console
 * (could have imported org.slf4j and used that logger instead of System.out...
 *      otherwise, the behaviour is similar)
 */
public class ConsoleLogger implements ILogger {
    @Override
    public void logFightStarted(Pokemon poke1, Pokemon poke2) {
        System.out.println("\n" + Thread.currentThread().getName() + "\n" +
                "----------- Fight started between " +
                poke1.getName() + " and " + poke2.getName() + " -----------\n" +
                poke1 + "\n" + poke2);
    }

    @Override
    public void logRoundResult(Pokemon poke1, Pokemon poke2, int round) {
        System.out.println("\n" + Thread.currentThread().getName() +
                "\n---------------------- ROUND " + round +
                " result ----------------------\n\t" +
                poke1.state() + "\n\t<--->\n\t" + poke2.state() + "\n\n");
    }

    @Override
    public void logStunnedPokemon(Pokemon pokemon, int round) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();

        System.out.println(format.format(time) + " " + Thread.currentThread().getName() +
                "\tROUND " + round + ": " + pokemon.getName() +
                " is stunned and did absolutely nothing");
    }

    @Override
    public void logUsedCommands(Pokemon poke1, Pokemon poke2, Command command, int round) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();

        System.out.println(format.format(time) + " " + Thread.currentThread().getName() +
                "\tROUND " + round + ": " + poke1.getName() +
                " used " + command.getType() + command.getDamage() + " on " + poke2.getName());
    }

    @Override
    public void logBattleDraw() {
        System.out.println(Thread.currentThread().getName() +
                "\n----------- DRAW -----------");
    }

    @Override
    public void logBattleWonBy(String name) {
        System.out.println(Thread.currentThread().getName() +
                "\n----------- " + name + " WON -----------");
    }

    @Override
    public void logState(Pokemon pokemon) {
        System.out.println(pokemon);
    }

    @Override
    public void logAdventureOver() {
        System.out.println("----------- ADVENTURE OVER -----------");
    }

    @Override
    public void logPokemonUpgraded() {
        System.out.println("----------- POKEMON UPGRADED -----------");
    }
}
