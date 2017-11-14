
import java.lang.Integer;
import java.util.Random;
import java.util.ArrayList;

public class RandomGenerator
{
    protected Random randomizer;
    protected ArrayList<Integer> usedNumbers;

    public RandomGenerator()
    {
        this.randomizer = new Random();
        this.usedNumbers = new ArrayList<Integer>();
    }

    public int nextInt(int limit)
    {
        boolean numberChose = false;
        Integer number = null;
        while (!numberChose) {
            number = new Integer(this.randomizer.nextInt(limit));

            if (!this.usedNumbers.contains(number)) {
                this.usedNumbers.add(number);
                numberChose = true;
                break;
            }
        }

        return number.intValue();
    }
}
