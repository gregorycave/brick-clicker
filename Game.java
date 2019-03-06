package brick-clicker;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class GameCountdown extends TimerTask {
    private GameFrame _gameFrame;
    public GameFrame getGameFrame() {
        return _gameFrame;
    }
    public void setGameFrame(GameFrame gameFrame) {
        this._gameFrame = gameFrame;
    }
    private int _seconds;
    public int getSeconds() {
        return _seconds;
    }
    public void setSeconds(int seconds) {
        this._seconds = seconds;
    }

    public GameCountdown(GameFrame gameFrame) {
        this.setGameFrame(gameFrame);           // Initialise properties for GameFrame.
        this.setSeconds(Constants.GAMETIME);    // Set the time within the game.
    }

    // Method for managing time within the game.
    @Override
    public void run() {
        // Update TimerLabel at top of frame.
        this.getGameFrame().getGamePanel().getGameInfoPanel().getTimerLabel().setText("Time: " + this.getSeconds());
        if (this.getSeconds() == 0) {
            // The time has run out (end the game).
            this.getGameFrame().endGame();
            return;
        }
        this.setSeconds(this.getSeconds() - 1);
    }
}
                                                                                                                    // ---------------------------------------
class GameInfoPanel extends JPanel {                                                                                // Initialise Variables:
                                                                                                                    // ---------------------------------------
    private GameFrame _gameFrame;                                                                                   // Initialise variable for GameFrame.
    private GameFrame getGameFrame() { return _gameFrame; }                                                         // Get current value for GameFrame.
    private void setGameFrame(GameFrame gameFrame) { this._gameFrame = gameFrame; }                                 // Update GameFrame within the JPanel.
                                                                                                                    // ---------------------------------------
    private JLabel _scoreLabel;                                                                                     // Initialise variable for ScoreLabel.
    private JLabel getScoreLabel() { return _scoreLabel; }                                                          // Get current value for ScoreLabel.
    private void setScoreLabel(JLabel scoreLabel) { this._scoreLabel = scoreLabel; }                                // Set the current score within the game.
    public void updateScoreLabel() { this.getScoreLabel().setText("Score: " + this.getGameFrame().getScore()); }    // Update ScoreLabel at top of frame.
                                                                                                                    // ---------------------------------------
    private JLabel _timerLabel;                                                                                     // Initialise variable for TimerLabel.
    public JLabel getTimerLabel() { return _timerLabel; }                                                           // Get current value for TimerLabel.
    private void setTimerLabel(JLabel timerLabel) { this._timerLabel = timerLabel; }                                // Update TimerLabel at top of frame.
                                                                                                                    // ---------------------------------------
    private void initialiseLabels() {                                                                               // Initialise Labels:
                                                                                                                    // ---------------------------------------
        this.setTimerLabel(new JLabel("Time: 60"));                                                            // Set TimerLabel and add to panel.
        this.setScoreLabel(new JLabel("Score: " + this.getGameFrame().getScore()));                            // Set ScoreLabel and add to panel.
                                                                                                                    // ---------------------------------------
        this.getTimerLabel().setFont(new Font(Constants.FONTTYPE, Font.BOLD, Constants.FONTSIZE));                  // Set FONTTYPE and FONTSIZE for TimerLabel.
        this.getScoreLabel().setFont(new Font(Constants.FONTTYPE, Font.BOLD, Constants.FONTSIZE));                  // Set FONTTYPE and FONTSIZE for ScoreLabel.
                                                                                                                    // ---------------------------------------
        this.add(this.getTimerLabel());                                                                             // Update the TimerLabel variable.
        this.add(this.getScoreLabel());                                                                             // Update the ScoreLabel variable.
    }                                                                                                               // ---------------------------------------

    // Method to set properties within the game.
    private void initialisePanel(GameFrame gameFrame) {     // ---------------------------------------
        this.setLayout(new GridLayout(0, 2));    // Set Layout.
        this.setGameFrame(gameFrame);                       // Set GameFrame.
        this.initialiseLabels();                            // Set Labels.
    }                                                       // ---------------------------------------

    public GameInfoPanel(GameFrame gameFrame) { initialisePanel(gameFrame); } // Set Panel within the frame
}

class GameFrame extends JFrame {                                                                                    // ---------------------------------------
    private static final String GAMEPANEL = "Main Game";                                                            // Title for 'Main Game' section.
    private static final String ScoreboardPanel = "Scoreboard";                                                     // Title for the 'Scoreboard' section.
                                                                                                                    // ---------------------------------------
    private ScoreboardPanel _highscorePanel;                                                                        // Initialise variable for ScoreboardPanel.
    public ScoreboardPanel getScoreboardPanel() { return _highscorePanel; }                                         // Get current value for ScoreboardPanel.
    public void setScoreboardPanel(ScoreboardPanel ScoreboardPanel) { this._highscorePanel = ScoreboardPanel; }     // Update ScoreboardPanel within the game.
                                                                                                                    // ---------------------------------------
    private GamePanel _gamePanel;                                                                                   // Initialise variable for GamePanel.
    public GamePanel getGamePanel() { return _gamePanel; }                                                          // Get current variable for GamePanel.
    public void setGamePanel(GamePanel gamePanel) { this._gamePanel = gamePanel; }                                  // Update GamePanel within the game.
                                                                                                                    // ---------------------------------------
    private int _score;                                                                                             // Initialise variable for Score.
    public int getScore() { return _score; }                                                                        // Get current value for Score.
    public void setScore(int score) { this._score = score; }                                                        // Update Score within the game.
                                                                                                                    // ---------------------------------------
    private Timer _countdownTimer;                                                                                  // Initialise variable for CountdownTimer.
    public Timer getCountdownTimer() { return _countdownTimer; }                                                    // Get current value for CountdownTimer.
    public void setCountdownTimer(Timer countdownTimer) { this._countdownTimer = countdownTimer; }                  // Update CountdownTimer within the game.
                                                                                                                    // ---------------------------------------
    private Timer _levelTimer;                                                                                      // Initialise variable for LevelTimer.
    public Timer getLevelTimer() { return _levelTimer; }                                                            // Get current value for LevelTimer.
    public void setLevelTimer(Timer levelTimer) { this._levelTimer = levelTimer; }                                  // Update LevelTimer within the game.
                                                                                                                    // ---------------------------------------
    private JPanel _cards;                                                                                          // Initialise variable for JPanel (cards).
    public JPanel getCards() { return _cards; }                                                                     // Get current value for JPanel (cards).
    public void setCards(JPanel cards) { this._cards = cards; }                                                     // Update the JPanel for the game.
                                                                                                                    // ---------------------------------------
    public void startGame() {           // ---------------------------------------
        this.updateScore(0);   // Reset the current score within the game.
        this.initialiseTimers();        // Initialise the timer for the game.
                                        // ---------------------------------------
        this.changeCard(GAMEPANEL);     // Change the card to be the contents of the GamePanel.
    }                                   // ---------------------------------------

    public void endGame() {                                             // ---------------------------------------
        this.getLevelTimer().cancel();                                  // Cancel the LevelTimer.
        this.getCountdownTimer().cancel();                              // Cancel the CountdownTimer.
                                                                        // ---------------------------------------
        ScoreService.addScore(new Score(this.getScore(), new Date()));  // Record the current score in the scoreboard.
        this.updateScore(0);                                   // Reset the current score (incase the user plays again).
                                                                        // ---------------------------------------

        try {                                           // ---------------------------------------
            this.getScoreboardPanel().loadHighScores(); // Load the highscores from the file.
        }                                               // ---------------------------------------
        catch (ParseException e) {                      // Scoreboard file can't be found...
            e.printStackTrace();                        // Return the StackTrace.
        }                                               // ---------------------------------------
        this.changeCard(ScoreboardPanel);               // Change the card to the ScoreboardPanel.
    }                                                   // ---------------------------------------

    public void updateScore(int addition) {                         // ---------------------------------------
        this.setScore(this.getScore() + addition);                  // Update Score within the game.
        this.getGamePanel().getGameInfoPanel().updateScoreLabel();  // Update the ScoreLabel.
    }                                                               // ---------------------------------------

    private void changeCard(String card) {
        CardLayout cards = (CardLayout)this.getCards().getLayout();
        cards.show(this.getCards(), card);
    }

    private void initialiseTimers() {   // Reset the timers and schedule tasks for the game.
        this.setCountdownTimer(new Timer());
        this.setLevelTimer(new Timer());
        this.getCountdownTimer().scheduleAtFixedRate(new GameCountdown(this), 0, 1000);
        this.getLevelTimer().scheduleAtFixedRate(new Level(this), 0, 3000);
    }

    private void initialisePanels() {
        // Set panel properties.
        this.setGamePanel(new GamePanel(this));
        this.setScoreboardPanel(new ScoreboardPanel(this));
        this.setCards(new JPanel(new CardLayout()));

        // Add Panel as a card (to cards panel).
        this.getCards().add(this.getGamePanel(), GAMEPANEL);
        this.getCards().add(this.getScoreboardPanel(), ScoreboardPanel);
        this.add(this.getCards());  // Add the card as a panel to the frame.
    }

    private void initialiseFrame() {                // ---------------------------------------
        this.setTitle("Richard Cave, 1704522");     // Set variable for Title.
        this.setSize(1000, 1000);     // Set variable for Size.
        this.initialisePanels();                    // Initialise the Panel.
    }                                               // ---------------------------------------

    public GameFrame() {            // ---------------------------------------
        this.initialiseFrame();     // Initialise the frame.
        this.startGame();           // Start the game.
    }                               // ---------------------------------------
}

class GamePanel extends JPanel {                                                            // ---------------------------------------
    private GameFrame _gameFrame;                                                           // Initialise variable for GameFrame.
    public GameFrame getGameFrame() { return _gameFrame; }                                  // Get current value for GameFrame.
    public void setGameFrame(GameFrame gameFrame) { this._gameFrame = gameFrame; }          // Update GameFrame within the JPanel.
                                                                                            // ---------------------------------------
    private LevelPanel _levelPanel;                                                         // Initialise variable for LevelPanel.
    public LevelPanel getLevelPanel() { return _levelPanel; }                               // Get current value for LevelPanel.
    public void setLevelPanel(LevelPanel levelPanel) { this._levelPanel = levelPanel; }     // Update LevelPanel within the game.
                                                                                            // ---------------------------------------
    private GameInfoPanel _infoPanel;                                                       // Initialise variable for GameInfoPanel.
    public GameInfoPanel getGameInfoPanel() { return _infoPanel; }                          // Get current value for GameInfoPanel.
    public void setInfoPanel(GameInfoPanel infoPanel) { this._infoPanel = infoPanel; }      // Update GameInfoPanel within the game.
                                                                                            // ---------------------------------------

    private void initialiseProperties(GameFrame gameFrame) {    // Set the properties of the GameFrame.
        this.setGameFrame(gameFrame);
        this.setLevelPanel(new LevelPanel(gameFrame));
        this.setInfoPanel(new GameInfoPanel(this.getGameFrame()));
    }

    private void initialiseLayout() {
        this.setLayout(new BorderLayout());                     // Set the layout.
        this.add(this.getGameInfoPanel(), BorderLayout.NORTH);  // Add north component.
        this.add(this.getLevelPanel(), BorderLayout.CENTER);    // Add center component.
    }

    private void initialisePanel(GameFrame gameFrame) {         // Initialise the panel
        this.initialiseProperties(gameFrame);
        this.initialiseLayout();
    }

    public GamePanel(GameFrame gameFrame) { this.initialisePanel(gameFrame); }
}

