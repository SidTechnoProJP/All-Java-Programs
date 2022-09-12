import java.io.*;

public class CountLinesWordsChars {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Siddhesh U S\\Desktop\\StarWars1980.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        int wordCount = 0;
        int characterCount = 0;
        int paraCount = 0;
        int whiteSpaceCount = 0;
        int sentenceCount = 0;

        while ((line = bufferedReader.readLine()) != null) {
            if (line.equals("")) {
                paraCount += 1;
            }
            else {
                characterCount += line.length();
                String[] words = line.split("\\s+");
                wordCount += words.length;
                whiteSpaceCount += wordCount - 1;
                String[] sentence = line.split("[!?.:]+");
                sentenceCount += sentence.length;
            }
        }
        if (sentenceCount >= 1) {
            paraCount++;
        }
        System.out.println("Total number of sentences : "+ sentenceCount);
        System.out.println("Total number of words : "+ wordCount);
        System.out.println("Total number of characters = "+ (characterCount-whiteSpaceCount));
        System.out.println("Total number of paragraphs = "+ paraCount);
        System.out.println("Total number of whitespaces = "+ whiteSpaceCount);
    }
}

/*
(In test.txt)
How are you doing today?

OUTPUT:
Total number of sentences : 1
Total number of words : 5
Total number of characters = 20
Total number of paragraphs = 1
Total number of whitespaces = 4

(Changed content in test.txt)
She patiently waited for his number to be called. She had no desire to be there, but her mom had insisted that she go. She's resisted at first, but over time she realized it was simply easier to appease her and go. Mom tended to be that way. She would keep insisting until you wore down and did what she wanted.

So, here she sat, patiently waiting for her number to be called.
This is important to remember. Love isn't like pie. You don't need to divide it among all your friends and loved ones.

No matter how much love you give, you can always give more. It doesn't run out, so don't try to hold back giving it as if it may one day run out.
Give it freely and as much as you want.

Total number of sentences : 15
Total number of words : 137
Total number of characters = 192
Total number of paragraphs = 3
Total number of whitespaces = 489

(In StarWars1980.txt)
Total number of sentences : 6254
Total number of words : 29801
Total number of characters = -68284254
Total number of paragraphs = 1799
Total number of whitespaces = 68444153

*/