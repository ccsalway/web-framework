package core;

public class Helpers {

    @SafeVarargs
    public static <T> T firstNotEmpty(T... args) {
        for (T arg : args)
            if (arg != null) {
                if (arg instanceof String && ((String) arg).isEmpty())
                    continue;
                return arg;
            }
        throw new NullPointerException();
    }

}