
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
    ArrayList<LibraryNode> library;

    public Library()
    {
        this.library = new ArrayList<LibraryNode>();
    }

    protected void populate(String filePath)
    {
        try {
            File inputFile = new File(filePath);
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));

            String word = inputReader.readLine();
            while (word != null) {
                LibraryNode wordNode = new LibraryNode(word);
                this.library.add(wordNode);
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

    public int getCount()
    {
        return this.library.size();
    }
}
