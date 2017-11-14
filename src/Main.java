
import java.lang.Integer;
import java.lang.String;
import java.lang.InterruptedException;
import java.util.ArrayList;

public class Main
{
    public static final String INPUT_FILEPATH = "../input/bd.txt";
    public static final int THREAD_QUANTITY = 10;
    public static final int SAMPLE_SIZE = 50;
    private Library library;

    public static void main(String[] args)
    {
        Library library = Library.getInstance();
        library.populate(Main.INPUT_FILEPATH);

        System.out.println("\nModo: lock padrão");
        Main.test();
        library.setReadWriteLock();
        System.out.println("\nModo: lock leitor-escritor");
        Main.test();
    }

    public static MyThread [] fillThreads(int writersQuantity)
    {
        MyThread [] threads = new MyThread[Main.THREAD_QUANTITY];

        ArrayList<Integer> usedPositions = new ArrayList<Integer>();
        int position;
        MyThread thread = null;
        RandomGenerator randomizer = new RandomGenerator();

        for (int i = 0; i < Main.THREAD_QUANTITY; i++) {
            position = randomizer.nextInt(Main.THREAD_QUANTITY);

            if (i < writersQuantity) {
                thread = (MyThread) new Writer();
                //System.out.println(position + " - Writer");
            } else {
                thread = (MyThread) new Reader();
                //System.out.println(position + " - Reader");
            }

            threads[position] = thread;
        }

        return threads;
    }

    public static void test()
    {
        long begin, end, sum;
        for (int i = 0; i <= Main.THREAD_QUANTITY; i++) {
            sum = 0;
            for (int j = 0; j < Main.SAMPLE_SIZE; j++) {
                MyThread [] threads = Main.fillThreads(i);

                begin = System.currentTimeMillis();
                for (int l = 0; l < Main.THREAD_QUANTITY; l++) {
                    //System.out.println("Thread #" + l + " - Beginning");
                    threads[l].setPosition(l);
                    threads[l].start();
                }

                for (int l = 0; l < Main.THREAD_QUANTITY; l++) {
                    try {
                        threads[l].join();
                    }
                    catch (InterruptedException exception) {}
                }

                end = System.currentTimeMillis();
                sum += end - begin;
            }

            System.out.println("#Writers: " + i  + " - #Readers: " + (Main.THREAD_QUANTITY - i) + " - Média do Tempo de execução: " + (sum / Main.SAMPLE_SIZE));
        }
    }
}
