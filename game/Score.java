package assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Score implements Comparable {                                                                  // ---------------------------------------
    private final static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");   // Set the date to be in the correct format.
                                                                                                            // ---------------------------------------
    private double _value;                                                                                  // Initialise variable for 'value'.
    public double getValue() { return _value; }                                                             // Get current value for 'value'.
    public void setValue(double value) { this._value = value; }                                             // Update 'value' within the game.
                                                                                                            // ---------------------------------------
    private Date _date;                                                                                     // Initialise variable for date.
    public Date getDate() { return _date; }                                                                 // Get current value for date.
    public void setDate(Date date) { this._date = date; }                                                   // Update date within the game.
                                                                                                            // ---------------------------------------
    public Score(double value, Date date) {     // ---------------------------------------
        this.setValue(value);                   // Set the current value.
        this.setDate(date);                     // Set the current date.
    }                                           // ---------------------------------------

    public static Score parse(String score) throws ParseException {
        String[] scoreArr = score.split(", ");
        double value = Double.parseDouble(scoreArr[0]);
        Date date = DATEFORMAT.parse(scoreArr[1]);
        return new Score(value, date);
    }

    public static String serialize(Score score) {
        return score.getValue() + ", " + DATEFORMAT.format(score.getDate());
    }

    @Override
    public int compareTo(Object o) {
        Score s = (Score)o;
        return this.getValue() == s.getValue() ? 0 : this.getValue() > s.getValue() ? -1 : 1;
    }

    @Override
    public String toString() {
        return "" + this.getValue();
    }
}

class ScoreListPanel extends JPanel {                                                                                   // ---------------------------------------
    private List<Score> _scores;                                                                                        // Initialise variable for scores.
    public List<Score> getScores() { return _scores; }                                                                  // Get current value for scores.
    public void setScores(List<Score> scores) { this._scores = scores; }                                                // Update the scores within the game.
                                                                                                                        // ---------------------------------------
    private JList<DefaultListModel<String>> _scoreList;                                                                 // Initialise variable for scoreList.
    public JList<DefaultListModel<String>> getScoreList() { return _scoreList; }                                        // Get current value for scoreList.
    public void setScoreList(JList<DefaultListModel<String>> scoreList) { this._scoreList = scoreList; }                // Update the scoreList within the game.
                                                                                                                        // ---------------------------------------
    private DefaultListModel<String> _scoreListItems;                                                                   // Initialise variable for scoreListItems.
    public DefaultListModel<String> getScoreListItems() { return _scoreListItems; }                                     // Get current value for scoreListItems.
    public void setScoreListItems(DefaultListModel<String> scoreListItems) { this._scoreListItems = scoreListItems; }   // Update the scoreListItems within the game.
                                                                                                                        // ---------------------------------------
    public void loadScores() {  // Map the scores to ScoreListItems.
        for (Score score: this.getScores()) {
            this.getScoreListItems().addElement(score.toString());
        }
    }

    private void initialiseLabel(String label) {    // Create a new label and add it to current panel.
        JLabel lbl =  new JLabel(label);
        lbl.setFont(new Font(Constants.FONTTYPE, Font.BOLD, Constants.FONTSIZE));
        this.add(lbl, BorderLayout.NORTH);
    }

    private void initialiseProperties() {   // Set properties for the score list.
        this.setScores(new ArrayList<Score>());
        this.setScoreListItems(new DefaultListModel<String>());
        this.setScoreList(new JList(this.getScoreListItems()));
    }

    private void initialiseScrollPane() {   // Initialise a new JScrollPane and add it to current panel.
        GridBagConstraints c = new GridBagConstraints();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setFont(new Font(Constants.FONTTYPE, Font.BOLD, Constants.FONTSIZE / 2));
        scrollPane.setViewportView(this.getScoreList());
        this.loadScores();
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void initialisePanel(String label) {    // ---------------------------------------
        this.setLayout(new BorderLayout());         // Set layout.
        this.initialiseLabel(label);                // Initialise label.
        this.initialiseProperties();                // Initialise properties.
        this.initialiseScrollPane();                // Initialise scrollpane.
    }                                               // ---------------------------------------
    public ScoreListPanel(String label) {           // ---------------------------------------
        this.initialisePanel(label);                // Initialise panel.
    }                                               // ---------------------------------------
}

class ScoreService {
    private final static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private final static String PATH = (Constants.FILEPATH);

    public static List<Score> getAllTimeTop() throws ParseException {   // Get the top ten results from all highscores.
        return getTopTen(getAll());
    }

    public static List<Score> getRecentTop() throws ParseException {    // Filter all highscores between the last 24 hours.
        List<Score> scoresWithinDay = getAll()
                .stream()
                .filter(s -> s.getDate().getTime() > (System.currentTimeMillis() - (24 * 60 * 60 * 1000)))
                .collect(Collectors.toList());
        return getTopTen(scoresWithinDay);  // Get the top ten results from all highscores.
    }

    public static boolean addScore(Score score) {   // Add the score to the scores.txt file.
        return ScoreboardFileService.writeLine(PATH, Score.serialize(score));
    }

    private static List<Score> getAll() throws ParseException {

        List<Score> result = new ArrayList<Score>();            // Retrieve all entries in the scores.txt file.
        for (String line : ScoreboardFileService.getAllLines(PATH)) {   // Add the score to the results.
            result.add(Score.parse(line));
        }
        return result;  // Return the list of highscores.
    }

    private static List<Score> getTopTen(List<Score> list) {
        return list.stream().sorted((s1, s2) -> s1.compareTo(s2)).limit(10).collect(Collectors.toList());   // Return the top ten entries from the scores.txt file.
    }
}

