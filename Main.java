package assignment2;

class Constants {                                                                       // ---------------------------------------
    public static final int BLOCKHEIGHT = 100;                                          // Declare variable for BLOCKHEIGHT.
    public static final int BLOCKWIDTH = 100;                                           // Declare variable for BLOCKWIDTH.
    public static final int GAMETIME = 60;                                              // Declare variable for GAMETIME.
    public static final String FILEPATH = "src/assignments/assignment2/score.txt";      // Declare variable for FILEPATH.
                                                                                        // ---------------------------------------
    public static final String FONTTYPE = "Serif";                                      // Declare variable for FONTTYPE.
    public static final int FONTSIZE = 50;                                              // Declare variable for FONTSIZE.
}                                                                                       // ---------------------------------------

public class Main {                                 // ---------------------------------------
    public static void main(String[] args) {        // Function to start the main game.
        GameFrame gameFrame = new GameFrame();      // Create a new instance of the GameFrame.
        gameFrame.setVisible(true);                 // Make the GameFrame visible to the user.
    }                                               // ---------------------------------------
}

