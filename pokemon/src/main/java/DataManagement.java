import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pokemon.Pokemon;
import trainer.Trainer;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Import data from JSON files
 */
public class DataManagement {
    public static DataManagement instance = null;

    // store data as a hashmap of lists
    // every list consists of a pair of trainers
    HashMap<Integer, List<Trainer>> trainers;
    private static final String PATH_TO_RESOURCES = "src/main/resources";

    private DataManagement(HashMap<Integer, List<Trainer>> trainers) {
        this.trainers  = trainers;
    }

    // singleton instance
    public static DataManagement initialize(HashMap<Integer, List<Trainer>> trainers) {
        if (instance == null)
            instance = new DataManagement(trainers);
        return instance;
    }

    // check if imported data is valid or not
    private void checkTrainer(Trainer trainer) {
        if (trainer.getPokemons().size() != Trainer.SET_POKEMONS)
            throw new IllegalAccessError();
        for (Pokemon pokemon : trainer.getPokemons()) {
            if (pokemon.getAbilities().size() > Pokemon.MAX_ABILITIES ||
                    pokemon.getItems().size() > Pokemon.MAX_ITEMS)
                throw new IllegalAccessError();
        }
    }

    // validate data using the above method for every trainer
    public void validateData() {
        for (List<Trainer> trainersPair : trainers.values()) {
            checkTrainer(trainersPair.get(0));
            checkTrainer(trainersPair.get(1));
        }
    }

    // import from JSON files using GSON
    public void importFromJSON() {
        Gson gson = new Gson();

        // open input folder and list files into an array
        File inFolder = new File(PATH_TO_RESOURCES);
        File[] files = inFolder.listFiles();

        // iterate through files and store the data accordingly
        if (files != null) {
            for (File file : files) {
                File newFile = new File(file.getPath());
                try {
                    // check the file extension
                    String[] parsedName = file.getName().split("[.]");
                    if (parsedName.length == 2 && !parsedName[1].equals("json"))
                        continue;

                    // read all data from file by setting the delimiter to the end of the file
                    String in = new Scanner(newFile).useDelimiter("\\Z").next();

                    // get the data type
                    Type dataType = new TypeToken<ArrayList<Trainer>>(){}.getType();

                    List<Trainer> listOfTwoTrainers = new ArrayList<>(gson.fromJson(in, dataType));

                    // get test number
                    parsedName = parsedName[0].split("[_]");
                    // parsedName[1] --> the test number

                    // add the imported pair of trainers to the main collection
                    trainers.put(Integer.valueOf(parsedName[1]), listOfTwoTrainers);

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        validateData();
    }
}
