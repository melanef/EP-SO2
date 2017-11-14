
import java.lang.InterruptedException;

public class Reader extends MyThread
{
    public static final int ACCESS_QUANTITY = 100;
    public static final int SLEEP_TIME = 1;

    public void run()
    {
        String word;
        Library library = Library.getInstance();

        library.readLock();
        try {
            RandomGenerator randomizer = new RandomGenerator();

            for (int i = 0; i < Reader.ACCESS_QUANTITY; i++) {
                int randomPosition = randomizer.nextInt(library.size());
                word = library.get(randomPosition);
                //System.out.println("Thread #" + this.instanceNumber + " - " + i + " - " + word);
            }

            this.sleep(Reader.SLEEP_TIME);
        }
        catch (InterruptedException exception) {}
        finally {
            library.readUnlock();
        }
    }
}
