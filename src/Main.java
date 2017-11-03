
import java.lang.String;
import java.lang.Thread;

public class Main
{
    public static final String INPUT_FILEPATH = "../input/bd.txt";
    public static final int THREAD_QUANTITY = 100;
    public static final int SAMPLE_SIZE = 50;
    private Library library;

    public static void main(String[] args)
    {
        RandomGenerator.init();
        RandomGenerator randomizer = RandomGenerator.getInstance();
        Library library = Library.getInstance();
        library.populate(Main.INPUT_FILEPATH);
        System.out.println("Total de entradas: " + library.size());

        outerloop:
        for (int i = 0; i <= Main.THREAD_QUANTITY; i++) {
            for (int j = 0; j < Main.SAMPLE_SIZE; j++) {
                Thread [] threads = new Thread[Main.THREAD_QUANTITY];
                for (int k = 0; k < Main.THREAD_QUANTITY; k++) {
                    int position = randomizer.nextInt(Main.THREAD_QUANTITY);
                    if (k >= i) {
                        if (threads[position] != null) {
                            System.out.println("ERRO! Posição já ocupada!");
                            break outerloop;
                        }

                        threads[position] = new Reader();
                    } else {
                        if (threads[position] != null) {
                            System.out.println("ERRO! Posição já ocupada!");
                            break outerloop;
                        }

                        threads[position] = new Writer();
                    }
                }

                // Inicia o temporizador

                for (int k = 0; k < Main.THREAD_QUANTITY; k++) {
                    threads[k].run();
                }

                // Encerra o temporizador
            }
        }
    }
}
