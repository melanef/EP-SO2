
import java.lang.Thread;
import java.lang.InterruptedException;

public class Writer extends Thread
{
    public static final int ACCESS_QUANTITY = 100;
    public static final int SLEEP_TIME = 1;

    public void run()
    {
        Library library = Library.getInstance();

        while(true) {
            if (!library.isLocked()) {
                library.lock();

                RandomGenerator randomizer = RandomGenerator.getInstance();

                for (int i = 0; i < Writer.ACCESS_QUANTITY; i++) {
                    int randomPosition = randomizer.getRandomLibraryPosition();
                    library.set(randomPosition, "MODIFICADO");
                }

                try {
                    this.sleep(Writer.SLEEP_TIME);
                }
                catch (InterruptedException exception) {}

                library.unlock();
                return;
            }
        }
    }
}
