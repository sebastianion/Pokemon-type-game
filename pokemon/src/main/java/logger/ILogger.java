package logger;

import commands.Command;
import pokemon.Pokemon;

/**
 *  Interface for observers
 */
public interface ILogger {
    // log messages
    void logFightStarted(Pokemon poke1, Pokemon poke2);
    void logRoundResult(Pokemon poke1, Pokemon poke2, int round);
    void logStunnedPokemon(Pokemon pokemon, int round);
    void logUsedCommands(Pokemon poke1, Pokemon poke2, Command command, int round);
    void logBattleDraw();
    void logBattleWonBy(String name);
    void logState(Pokemon pokemon);
    void logAdventureOver();
    void logPokemonUpgraded();
}
