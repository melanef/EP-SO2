
import java.lang.Thread;

public class Reader extends Thread
{
    public static final int ACCESS_QUANTITY = 100;
    public static final int SLEEP_TIME = 1;

    public void run()
    {
        String word;
        Library library = Library.getInstance();

        while(true) {
            if (!library.isLocked()) {
                library.lock();

                RandomGenerator randomizer = RandomGenerator.getInstance();

                for (int i = 0; i < Reader.ACCESS_QUANTITY; i++) {
                    int randomPosition = randomizer.getRandomLibraryPosition();
                    word = library.get(randomPosition);
                }

                try {
                    this.sleep(Reader.SLEEP_TIME);
                }
                catch (InterruptedException exception) {}

                library.unlock();
                return;
            }
        }
    }
}
