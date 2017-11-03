
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.String;
import java.lang.NoSuchFieldException;
import java.text.ParseException;

public class Main
{
    public static final String INPUT_FILEPATH = "../input/bd.txt";
    private Library library;

    public static void main(String[] args)
    {
        Library library = new Library();
        library.populate(Main.INPUT_FILEPATH);
        System.out.println("Total de entradas: " + library.getCount());
    }
}
