
import java.lang.Thread;

public class MyThread extends Thread
{
    protected int instanceNumber;

    public void setPosition(int instanceNumber)
    {
        this.instanceNumber = instanceNumber;
    }
}
