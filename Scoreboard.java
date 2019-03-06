package brick-clicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;


class ScoreboardButtonsPanel extends JPanel {                                                               // ---------------------------------------
    private GameFrame _gameFrame;                                                                           // Initialise variable for GameFrame.
    public GameFrame getGameFrame() { return _gameFrame; }                                                  // Get current value for GameFrame.
    public void setGameFrame(GameFrame gameFrame) { this._gameFrame = gameFrame; }                          // Update GameFrame within the JPanel.
                                                                                                            // ---------------------------------------
    private JButton _startGameButton;                                                                       // Initialise variable for startgameButton.
    public JButton getStartGameButton() { return _startGameButton; }                                        // Get current value for startgameButton.
    public void setStartGameButton(JButton startGameButton) { this._startGameButton = startGameButton; }    // Update value within the JPanel.
                                                                                                            // ---------------------------------------
    private JButton _exitGameButton;                                                                        // Initialise variable for exitGameButton.
    public JButton getExitGameButton() { return _exitGameButton; }                                          // Get current value for exitGameButton.
    public void setExitGameButton(JButton exitGameButton) { this._exitGameButton = exitGameButton; }        // Update exitGameButton within the JPanel.
                                                                                                            // ---------------------------------------

    private void initialiseButtons() {                               // ---------------------------------------
        this.setStartGameButton(new JButton("Start Game"));     // Set the 'Start Game' button.
        this.setExitGameButton(new JButton("Exit game"));       // Set the 'Exit Game' button.
                                                                     // ---------------------------------------

                                                                                                            // ---------------------------------------
        this.getStartGameButton().setFont(new Font(Constants.FONTTYPE, Font.PLAIN, Constants.FONTSIZE));    // Set font of the 'Start Game' button.
        this.getStartGameButton().addActionListener(new AppStartButtonListener(this.getGameFrame()));       // Add ActionListeners to the 'Start Game' button.
        this.getExitGameButton().setFont(new Font(Constants.FONTTYPE, Font.PLAIN, Constants.FONTSIZE));     // Set font of the 'Exit Game' button.
        this.getExitGameButton().addActionListener(new ActionListener() {                                   // Add ActionListeners to the 'Exit Game' button.
            @Override                                                                                       // ...
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }                           // ...
        });                                                                                                 // ---------------------------------------
                                                // ---------------------------------------
        this.add(this.getStartGameButton());    // Add the 'Start Game' button to the game.
        this.add(this.getExitGameButton());     // Add the 'Exit Game' button to the game.
    }                                           // ---------------------------------------

    private void initialisePanel(GameFrame gameFrame) {     // ---------------------------------------
        this.setGameFrame(gameFrame);                       // Set the GameFrame.
        this.setLayout(new GridLayout(1,2));     // Set the layout of the panel.
        this.initialiseButtons();                           // Initialise the buttons.
    }                                                       // ---------------------------------------

    public ScoreboardButtonsPanel(GameFrame gameFrame) {
        this.initialisePanel(gameFrame);
    }
}

class ScoreboardFileService {
    public static List<String> getAllLines(String path) {   // Retrieve all of the lines in the scores.txt file.
        List<String> result = new ArrayList<String>();
        try(Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(s -> result.add(s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean writeLine(String path, String line) { // Write a new line to the scores.txt file.
        try {
            Files.write(Paths.get(path), Arrays.asList(line), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}



class ScoreboardInfoPanel extends JPanel {
                                                                                                                                                        // ---------------------------------------
    private ScoreListPanel _allTimeScoreListPanel;                                                                                                      // Initialise variable for allTimeScoreListPanel.
    public ScoreListPanel getAllTimeScoreListPanel() { return _allTimeScoreListPanel; }                                                                 // Get current value for allTimeScoreListPanel.
    public void setAllTimeScoreListPanel(ScoreListPanel allTimeScoreListPanel) { this._allTimeScoreListPanel = allTimeScoreListPanel; }                 // Update allTimeScoreListPanel within the JPanel.
                                                                                                                                                        // ---------------------------------------
    private ScoreListPanel _mostRecentScoreListPanel;                                                                                                   // Initialise variable for mostRecentScoreListPanel.
    public ScoreListPanel getMostRecentScoreListPanel() { return _mostRecentScoreListPanel; }                                                           // Get current value for mostRecentScoreListPanel.
    public void setMostRecentScoreListPanel(ScoreListPanel mostRecentScoreListPanel) { this._mostRecentScoreListPanel = mostRecentScoreListPanel; }     // Update mostRecentScoreListPanel within the JPanel.
                                                                                                                                                        // ---------------------------------------
    private void initialisePanels() {                                           // ---------------------------------------
        this.setAllTimeScoreListPanel(new ScoreListPanel("All time"));          // Set panel for 'All time'.
        this.setMostRecentScoreListPanel(new ScoreListPanel("Most recent"));    // Set panel for 'Most Recent'.
                                                                                // ---------------------------------------
        this.add(this.getAllTimeScoreListPanel());                              // Add 'getAllTimeScoreListPanel' to current JPanel.
        this.add(this.getMostRecentScoreListPanel());                           // Add 'getMostRecentScoreListPanel' to current JPanel.
    }

    private void initialisePanel() {                       // ---------------------------------------
        this.setLayout(new GridLayout(1, 2));   // Set Layout of panel.
        this.initialisePanels();                           // Initialise the panel.
    }                                                      // ---------------------------------------

    public ScoreboardInfoPanel() {                         // ---------------------------------------
        this.initialisePanel();                            // Initialise the panel.
    }                                                      // ---------------------------------------
}

class ScoreboardPanel extends JPanel {                                                                                                              // ---------------------------------------
    private GameFrame _gameFrame;                                                                                                                   // Initialise variable for GameFrame.
    public GameFrame getGameFrame() { return _gameFrame; }                                                                                          // Get current value for GameFrame.
    public void setGameFrame(GameFrame gameFrame) { this._gameFrame = gameFrame; }                                                                  // Update GameFrame within the JPanel.
                                                                                                                                                    // ---------------------------------------
    private ScoreboardInfoPanel _highscoreInfoPanel;                                                                                                // Initialise variable for highscoreInfoPanel.
    public ScoreboardInfoPanel getScoreboardInfoPanel() { return _highscoreInfoPanel; }                                                             // Get current value for highscoreInfoPanel.
    public void setScoreboardInfoPanel(ScoreboardInfoPanel ScoreboardInfoPanel) { this._highscoreInfoPanel = ScoreboardInfoPanel; }                 // Update highscoreInfoPanel within the ScoreboardInfoPanel.
                                                                                                                                                    // ---------------------------------------
    private ScoreboardButtonsPanel _scoreboardButtonsPanel;                                                                                         // Initialise variable for scoreboardButtonsPanel.
    public ScoreboardButtonsPanel getHighscoreButtonsPanel() { return _scoreboardButtonsPanel; }                                                    // Get current value for scoreboardButtonsPanel.
    public void setScoreboardButtonsPanel(ScoreboardButtonsPanel ScoreboardButtonsPanel) { this._scoreboardButtonsPanel = ScoreboardButtonsPanel; } // Update scoreboardButtonsPanel within the ScoreboardInfoPanel.
                                                                                                                                                    // ---------------------------------------

    public void loadHighScores() throws ParseException {                                                        // ---------------------------------------
        this.getScoreboardInfoPanel().getAllTimeScoreListPanel().setScores(ScoreService.getAllTimeTop());       // Set all time highscores.
        this.getScoreboardInfoPanel().getAllTimeScoreListPanel().loadScores();                                  // Load all time highscores.
                                                                                                                // ---------------------------------------
        this.getScoreboardInfoPanel().getMostRecentScoreListPanel().setScores(ScoreService.getRecentTop());     // Set most recent highscores.
        this.getScoreboardInfoPanel().getMostRecentScoreListPanel().loadScores();                               // Load most recent highscores.
    }                                                                                                           // ---------------------------------------

    private void initialisePanels() {                                                       // ---------------------------------------
        this.setScoreboardInfoPanel(new ScoreboardInfoPanel());                             // Set ScoreboardInfoPanel.
        this.setScoreboardButtonsPanel(new ScoreboardButtonsPanel(this.getGameFrame()));    // Set ScoreboardButtonsPanel.
                                                                                            // ---------------------------------------
        this.add(this.getScoreboardInfoPanel(), BorderLayout.CENTER);                       // Add ScoreboardInfoPanel to current panel.
        this.add(this.getHighscoreButtonsPanel(), BorderLayout.SOUTH);                      // Add HighscoreButtonsPanel to current panel.
    }                                                                                       // ---------------------------------------

    private void initialiseLabel() {                                                                                // ---------------------------------------
        JLabel headerLabel = new JLabel("Highscores", SwingConstants.CENTER);                                  // Create new label.
        headerLabel.setFont(new Font(Constants.FONTTYPE, Font.BOLD, (int)Math.round(Constants.FONTSIZE * 1.4)));    // Set the font of the label.
        this.add(headerLabel, BorderLayout.NORTH);                                                                  // Add label to current panel.
    }                                                                                                               // ---------------------------------------

    private void initialisePanel(GameFrame gameFrame) { // ---------------------------------------
        this.setLayout(new BorderLayout());             // Set Layout.
        this.setGameFrame(gameFrame);                   // Set GameFrame.
                                                        // ---------------------------------------
        this.initialiseLabel();                         // Initialise labels.
        this.initialisePanels();                        // Initialise panels.
                                                        // ---------------------------------------

        try {           // Try to load the highscores.
            this.loadHighScores();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public ScoreboardPanel(GameFrame gameFrame) {
        this.initialisePanel(gameFrame);            // Initialise the panel.
    }
}
