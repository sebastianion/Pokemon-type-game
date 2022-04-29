package commands;

/**
 * Class needed to store the commands initiated by 2 Pokemon
 * on different threads, so they can attack each other at the same time
 */
public abstract class CommandHelper {
    private static Command prev1;
    private static Command prev2;

    public static Command getPrev1() {
        return prev1;
    }

    public static void setPrev1(Command prev1) {
        CommandHelper.prev1 = prev1;
    }

    public static Command getPrev2() {
        return prev2;
    }

    public static void setPrev2(Command prev2) {
        CommandHelper.prev2 = prev2;
    }
}
