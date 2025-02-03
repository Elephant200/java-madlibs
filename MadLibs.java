import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class MadLibs
{
    public static String[][] partsOfSpeech;
    public static String[][] stories;
    public static int storyCount;
    
    public static void main(String[] args)
    {
        load();
        Scanner input = new Scanner(System.in);
        
        String question = "Please choose an option below.";
        for (int i = 0; i < storyCount; i++) {
            question += "\n[" + (i+1) + "] Story " + (i+1);
        }
        question += "\n[" + (storyCount + 1) + "] Make your own story\n[0] Quit Program\n";
        System.out.println(question);
        int answer = Integer.parseInt(input.nextLine());
        while (answer != 0) {
            if (answer > 0 && answer < storyCount + 1) {
                MadLib game = new MadLib(partsOfSpeech[answer - 1], stories[answer - 1]);
                game.play();
            }
            if (answer == storyCount + 1) {
                System.out.println("Thank you for choosing to contribute to the MadLibs game.\n");
                System.out.println("Please enter your MadLibs in the format that follows:");
                System.out.println("Story content __Part of Speech__ more story content __Part of Speech__ more content.");
                System.out.println("See story1.txt for a full example.");
                System.out.println("When you finish writing the story, write __END__ in its own line to signal this.");
                
                // Get story from the user
                String newStory = "";
                while (true) {
                    String line = input.nextLine();
                    if (line.contains("__END__")) {
                        break;
                    }
                    newStory += line;
                }
                
                // Write story 
                dump("story" + (storyCount + 1) + ".txt", newStory);
                updateCount();
                
                System.out.println("Please rerun the program to see your new story!");
                System.exit(0);
            }
            input.nextLine();
            clearScreen();
            System.out.println(question);
            answer = Integer.parseInt(input.nextLine());
        }
        input.close();
    }
    
    public static void clearScreen() { 
        System.out.print("\033[H\033[2J"); 
        System.out.flush(); 
    }
    
    public static void load() {
        try {
            Scanner in = new Scanner(new FileReader("stories.txt"));
            storyCount = Integer.parseInt(in.nextLine());
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.out.println("Fatal: could not reaad stories.txt");
            System.exit(1);
        }
        
        partsOfSpeech = new String[storyCount][];
        stories = new String[storyCount][];
        
        for (int i = 1; i <= storyCount; i++) {
            System.out.println(i);
            String story = "";
            String fileName = "story" + i + ".txt";
            try {
                Scanner in = new Scanner(new FileReader(fileName));
                while (in.hasNext()) {
                    story += in.nextLine() + "\n";
                }
                partsOfSpeech[i - 1] = StoryParser.getPartsOfSpeech(story);
                stories[i - 1] = StoryParser.getStory(story);
                System.out.println("Successfully loaded file.");
            } catch (Exception e) {
                System.out.println("File \"" + fileName + "\" not found.");
            }
        }
    }
    
    public static void dump(String file, String text) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            writer.close();
        } catch (Exception e) {
            System.out.println("Could not write to file " + file + ".");
        }
    }
    
    public static void updateCount() {
        try {
            Scanner scanner = new Scanner(new FileReader("stories.txt"));
            int number = scanner.nextInt();
            scanner.close();
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("stories.txt"));
            writer.write(Integer.toString(number + 1));
            writer.close();
        } catch (Exception e) {
            System.out.println("Could not update count in stories.txt.");
        }
    }
}