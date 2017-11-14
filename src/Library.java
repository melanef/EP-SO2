
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Library
{
    private ArrayList<String> library;
    private Lock defaultLock;
    private ReadWriteLock readWriteLock;
    private String lockType;

    private static Library instance = null;

    protected Library()
    {
        this.library = new ArrayList<String>();
        this.setDefaultLock();
        this.defaultLock = new ReentrantLock();
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public void setDefaultLock()
    {
        this.lockType = "default";
    }

    public void setReadWriteLock()
    {
        this.lockType = "read-write";
    }

    public String getLockType()
    {
        return this.lockType;
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

    public void set(int position, String word)
    {
        this.library.set(position, word);
    }

    public void readLock()
    {
        if (this.lockType.equals("default")) {
            this.defaultLock.lock();
        } else if (this.lockType.equals("read-write")) {
            this.readWriteLock.readLock().lock();
        }
    }

    public void readUnlock()
    {
        if (this.lockType.equals("default")) {
            this.defaultLock.unlock();
        } else if (this.lockType.equals("read-write")) {
            this.readWriteLock.readLock().unlock();
        }
    }

    public void writeLock()
    {
        if (this.lockType.equals("default")) {
            this.defaultLock.lock();
        } else if (this.lockType.equals("read-write")) {
            this.readWriteLock.writeLock().lock();
        }
    }

    public void writeUnlock()
    {
        if (this.lockType.equals("default")) {
            this.defaultLock.unlock();
        } else if (this.lockType.equals("read-write")) {
            this.readWriteLock.writeLock().unlock();
        }
    }
}
