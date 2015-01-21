package hangout;

/**
 *
 * @author Andrew Pinion
 */
public class Hangout
{
    public static void main(String[] args)
    {
        gameFrame gameWindow;
        gameWindow = new gameFrame();
        if(!gameWindow.errors()) gameWindow.setVisible(true);
        gameWindow.setVisible(true);
    }
}
