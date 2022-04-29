import logger.ConsoleLogger;
import logger.FileLogger;
import logger.Logger;
import trainer.Trainer;

import java.util.*;

/**
 * Driver class --> starts the application
 */
public class Driver {

    public static void main(String[] args) {
        // get an instance for the logger
        Logger logger = Logger.initialize();
        // add logging methods
        logger.addLoggingRoutine(new ConsoleLogger());
        logger.addLoggingRoutine(new FileLogger());

        // a hashmap of lists to store multiple tests
        // every list consists of a pair of trainers
        HashMap<Integer, List<Trainer>> trainers = new HashMap<>();

        // import all data from JSON files
        DataManagement dataManagement = DataManagement.initialize(trainers);
        dataManagement.importFromJSON();

        // testing the application
        Scanner in = new Scanner(System.in);
        System.out.println("Enter test number... (has to be between 1-10)");
        int testNo;
        while (true) {
            try {
                testNo = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number between 1-10, or 0 to quit");
                in.nextLine();
                continue;
            }

            if (testNo == 0) {
                System.out.println("Bye for now");
                System.exit(0);
            }

            if (1 <= testNo && testNo <= 10)
                break;

            System.out.println("Enter a valid number between 1-10, or 0 to quit");
        }

        // create and start an arena with a specific pair of trainers
        // decided in the previous while loop
        Arena arena = new Arena(trainers.get(testNo));
        arena.start();

        // close the writer
        FileLogger.closeWriter();
    }
}
