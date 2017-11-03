
import java.util.Random;

public class RandomGenerator
{
    protected Random randomizer;
    protected static RandomGenerator instance = null;

    protected RandomGenerator()
    {
        this.randomizer = new Random();
    }

    public static void init()
    {
        instance = new RandomGenerator();
    }

    public static RandomGenerator getInstance()
    {
        if (instance == null) {
            RandomGenerator.init();
        }

        return instance;
    }

    public int getRandomLibraryPosition()
    {
        Library library = Library.getInstance();
        return this.randomizer.nextInt(library.size());
    }

    public int nextInt(int limit)
    {
        return this.randomizer.nextInt(limit);
    }
}
