public class StoryParser {
    public static String[] getPartsOfSpeech(String text) {
        String[] tokens = text.split("__");
        if (tokens.length % 2 != 1) {
            throw new Error("Invalid story. Expected an even number of *; got " + (tokens.length - 1) + " instead.");
        }
        String[] partsOfSpeech = new String[tokens.length / 2];
        for (int i = 0; i < tokens.length; i++) {
            if (i % 2 == 1) {
                partsOfSpeech[i / 2] = tokens[i];
            }
        }
        return partsOfSpeech;
    }
    
    public static String[] getStory(String text) {
        String[] tokens = text.split("__");
        if (tokens.length % 2 != 1) {
            throw new Error("Invalid story. Expected an even number of *; got " + (tokens.length - 1) + " instead.");
        }
        String[] story = new String[tokens.length / 2 + 1];
        for (int i = 0; i < tokens.length; i++) {
            if (i % 2 == 0) {
                story[i / 2] = tokens[i];
            }
        }
        return story;
    }
}