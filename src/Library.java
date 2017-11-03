
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;

public class Library
{
    private ArrayList<String> library;
    private boolean locked;

    private static Library instance = null;

    protected Library()
    {
        this.library = new ArrayList<String>();
        this.locked = false;
    }

    public static Library getInstance() {
      if(instance == null) {
         instance = new Library();
      }

      return instance;
   }

    protected void populate(String filePath)
    {
        try {
            File inputFile = new File(filePath);
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));

            String word = inputReader.readLine();
            while (word != null) {
                this.library.add(word);
                word = inputReader.readLine();
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("Arquivo de entrada não encontrado.");
        }
        catch (IOException exception) {
            System.out.println("Falha na leitura do arquivo de entrada.");
        }
    }

    public int size()
    {
        return this.library.size();
    }

    public String get(int position)
    {
        return this.library.get(position);
    }

    public boolean isLocked()
    {
        return this.locked;
    }

    public void lock()
    {
        this.locked = true;
    }

    public void unlock()
    {
        this.locked = false;
    }
}
