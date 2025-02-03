import java.util.Scanner;

public class MadLib {
    private String[] partsOfSpeech;
    private String[] story;
    private Scanner input;

    public MadLib(String[] parts, String[] stor) {
        partsOfSpeech = parts;
        story = stor;
        input = new Scanner(System.in);
    }
    
    public void play() {
        System.out.println("Welcome to the MadLibs!");
        printStory(getInputs());
    }
    
    // reads inputs
    public String[] getInputs() {
        String[] inputs = new String[partsOfSpeech.length];
        System.out.println("Please fill in the blanks with the words called for.");
        for (int i = 0; i < partsOfSpeech.length; i++) {
            System.out.print(partsOfSpeech[i] + ": ");
            inputs[i] = input.nextLine();
        }
        return inputs;
    }
    
    // Prints out the story
    public void printStory(String[] inputs) {
        if (story.length - 1 != inputs.length) {
            throw new Error("Invalid story: story.length must be 1 greater to inputs.length. Found: story.length = " + story.length + ", inputs.length = " + inputs.length + " instead.");
        }
        for (int i = 0; i < story.length - 1; i++) {
            System.out.print(story[i]);
            System.out.print(inputs[i]);
        }
        System.out.print(story[story.length - 1]);
    }
}