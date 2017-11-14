
import java.lang.InterruptedException;

public class Writer extends MyThread
{
    public static final int ACCESS_QUANTITY = 100;
    public static final int SLEEP_TIME = 1;

    public void run()
    {
        Library library = Library.getInstance();

        library.writeLock();
        try {
            RandomGenerator randomizer = new RandomGenerator();

            for (int i = 0; i < Writer.ACCESS_QUANTITY; i++) {
                int randomPosition = randomizer.nextInt(library.size());
                library.set(randomPosition, "MODIFICADO");
            }

            this.sleep(Writer.SLEEP_TIME);
        }
        catch (InterruptedException exception) {}
        finally {
            library.writeUnlock();
        }
    }
}
