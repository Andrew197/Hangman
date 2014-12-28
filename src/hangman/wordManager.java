package hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew Pinion
 */
public class wordManager
{
    int numLines = 1;
    boolean fileExists;
    String [] words;
       
    public wordManager()
    {
        try
        {
            //count the number of lines in the file
            BufferedReader br = new BufferedReader(new FileReader("dict.txt"));
            numLines = 0;
            while (br.readLine() != null) numLines++;
            
            //reset the reader
            br = new BufferedReader(new FileReader("dict.txt"));
            words = new String[numLines];
            
            //read each line into the string array
            int temp = 0;
            while(temp < numLines)
            {
                words[temp] = br.readLine();
                String toLowerCase = words[temp].toLowerCase();
                words[temp] = toLowerCase;
                temp++;
            }
            fileExists = true;
        } 
        catch (IOException ex)
        {
            fileExists = false;
            ErrorMsg eMsg = new ErrorMsg("Dictionary file is missing!");
            Logger.getLogger(wordManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public boolean fileExists() { return fileExists; }

    //when a new game is requested, send it a random word.
    public String getRandomWord()
    {
        int randomInt = randInt(0, numLines - 1);
        return words[randomInt];
    }
    
    //utility function to determine which random word to get
    public static int randInt(int min, int max) 
    {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
