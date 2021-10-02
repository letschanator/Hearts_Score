
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HeartsFrame extends JFrame{

    private JLabel infoLabel;
    private JTextField nameInput;
    private JButton submit;
    private Boolean gameStarted = false;
    private int numPlayers = 1;
    private ArrayList<String> names;
    private ArrayList<ArrayList<Integer>> scores;
    private JTextField[] scoreInputs;
    private JLabel[] nameDisplay;
    private JButton shootUp;
    private JButton shootDown;
    private JButton goBack;
    private String[] passOrder3 = new String[]{"Left","Right","no pass"};
    private String[] passOrder4 = new String[]{"Left","Right","Across", "No Pass"};
    private JLabel passDisplay;
    private int turn = 0;

    public HeartsFrame(){
        super("Hearts Score App");
        setLayout(new FlowLayout());

        infoLabel = new JLabel("Input all the names with a comma separating them");
        add(infoLabel);

        nameInput = new JTextField(30);
        add(nameInput);

        submit = new JButton("Submit");
        add(submit);

        names = new ArrayList<>();
        goBack = new JButton("undo");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameStarted){
                    gameStarted = true;
                    String name = nameInput.getText();
                    String current = "";
                    while (name.length() > 0){
                        if(name.substring(0,1).equals(",")){
                            names.add(current);
                            current = "";
                            numPlayers ++;
                        }else{
                            current = current + name.charAt(0);
                        }
                        name = name.substring(1);
                    }
                    names.add(current);
                    if (numPlayers > 4 || numPlayers < 3){
                        infoLabel.setText("Error: incorrect number of players");
                    }else if (numPlayers == 3){
                        scores = new ArrayList<>();
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(0); temp.add(0); temp.add(0);
                        scores.add(temp);
                        setUpThreePlayers();
                    }else{
                        scores = new ArrayList<>();
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(0); temp.add(0); temp.add(0); temp.add(0);
                        scores.add(temp);
                        setUpFourPlayers();
                    }
                }else {
                    infoLabel.setText("");
                    if (numPlayers == 3) {
                        int score1, score2, score3;
                        score1 = Integer.valueOf(scoreInputs[0].getText());
                        score2 = Integer.valueOf(scoreInputs[1].getText());
                        score3 = Integer.valueOf(scoreInputs[2].getText());
                        if (score1 + score2 + score3 != 26) {
                            infoLabel.setText("Error: scores do not add up to 26");
                        } else {
                            if (score1 == 26 || score2 == 26 || score3 == 26) {
                                infoLabel.setText("press one of the shoot the moon buttons instead");
                            } else {
                                if(turn == 2){
                                    turn = 0;
                                }else{
                                    turn++;
                                }
                                passDisplay.setText("Passing - " + passOrder3[turn]);
                                score1 = score1 + scores.get(scores.size() - 1).get(0);
                                score1 = checkScore(score1);
                                score2 = score2 + scores.get(scores.size() - 1).get(1);
                                score2 = checkScore(score2);
                                score3 = score3 + scores.get(scores.size() - 1).get(2);
                                score3 = checkScore(score3);
                                nameDisplay[0].setText(names.get(0) + " - " + score1);
                                nameDisplay[1].setText(names.get(1) + " - " + score2);
                                nameDisplay[2].setText(names.get(2) + " - " + score3);
                                ArrayList<Integer> temp = new ArrayList<>();
                                temp.add(score1);
                                temp.add(score2);
                                temp.add(score3);
                                scores.add(temp);
                            }
                        }

                    } else {
                        int score1, score2, score3, score4;
                        score1 = Integer.valueOf(scoreInputs[0].getText());
                        score2 = Integer.valueOf(scoreInputs[1].getText());
                        score3 = Integer.valueOf(scoreInputs[2].getText());
                        score4 = Integer.valueOf(scoreInputs[3].getText());
                        if (score1 + score2 + score3 + score4 != 26) {
                            infoLabel.setText("Error: scores do not add up to 26");
                        } else {
                            if (score1 == 26 || score2 == 26 || score3 == 26 || score4 == 26) {
                                infoLabel.setText("press one of the shoot the moon buttons instead");
                            } else {
                                if(turn == 3){
                                    turn = 0;
                                }else{
                                    turn++;
                                }
                                passDisplay.setText("Passing - " + passOrder4[turn]);
                                score1 = score1 + scores.get(scores.size() - 1).get(0);
                                score1 = checkScore(score1);
                                score2 = score2 + scores.get(scores.size() - 1).get(1);
                                score2 = checkScore(score2);
                                score3 = score3 + scores.get(scores.size() - 1).get(2);
                                score3 = checkScore(score3);
                                score4 = score4 + scores.get(scores.size() - 1).get(3);
                                score4 = checkScore(score4);
                                nameDisplay[0].setText(names.get(0) + " - " + score1);
                                nameDisplay[1].setText(names.get(1) + " - " + score2);
                                nameDisplay[2].setText(names.get(2) + " - " + score3);
                                nameDisplay[3].setText(names.get(3) + " - " + score4);
                                ArrayList<Integer> temp = new ArrayList<>();
                                temp.add(score1);
                                temp.add(score2);
                                temp.add(score3);
                                temp.add(score4);
                                scores.add(temp);
                            }
                        }

                    }
                }
            }
        });


    }
    public void setUpThreePlayers(){
        this.remove(infoLabel);
        this.remove(nameInput);
        this.remove(submit);
        nameDisplay = new JLabel[3];
        nameDisplay[0] = new JLabel(names.get(0) + " - 0");
        nameDisplay[1] = new JLabel(names.get(1) + " - 0");
        nameDisplay[2] = new JLabel(names.get(2) + " - 0");
        scoreInputs = new JTextField[3];
        scoreInputs[0] = new JTextField(5);
        scoreInputs[1] = new JTextField(5);
        scoreInputs[2] = new JTextField(5);
        add(nameDisplay[0]); add(scoreInputs[0]); add(nameDisplay[1]);
        add(scoreInputs[1]); add(nameDisplay[2]); add(scoreInputs[2]);
        shootUp = new JButton("add 26 to other players");
        shootDown = new JButton("subtract 26 from your score");
        passDisplay = new JLabel("Passing - " + passOrder3[0]);
        infoLabel.setText("");
        add(infoLabel);
        add(submit);
        add(shootUp);
        add(shootDown);
        add(goBack);
        add(passDisplay);
        this.revalidate();

        shootUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoLabel.setText("");
                int score1, score2, score3;
                score1 = Integer.valueOf(scoreInputs[0].getText());
                score2 = Integer.valueOf(scoreInputs[1].getText());
                score3 = Integer.valueOf(scoreInputs[2].getText());
                if (score1 + score2 + score3 != 26) {
                    infoLabel.setText("Error: scores do not add up to 26");
                } else {
                    if (score1 == 26 || score2 == 26 || score3 == 26) {
                        if(turn == 2){
                            turn = 0;
                        }else{
                            turn++;
                        }
                        passDisplay.setText("Passing - " + passOrder3[turn]);
                        if(score1 == 26){
                            score1 = scores.get(scores.size() - 1).get(0);
                            score2 = scores.get(scores.size() - 1).get(1) + 26;
                            score2 = checkScore(score2);
                            score3 = scores.get(scores.size() - 1).get(2) + 26;
                            score3 = checkScore(score3);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            scores.add(temp);
;                        }else if(score2 == 26){
                            score1 = scores.get(scores.size() - 1).get(0) + 26;
                            score1 = checkScore(score1);
                            score2 = scores.get(scores.size() - 1).get(1);
                            score3 = scores.get(scores.size() - 1).get(2) + 26;
                            score3 = checkScore(score3);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            scores.add(temp);
                        }else if(score3 == 26){
                            score1 = scores.get(scores.size() - 1).get(0) + 26;
                            score1 = checkScore(score1);
                            score2 = scores.get(scores.size() - 1).get(1) + 26;
                            score2 = checkScore(score2);
                            score3 = scores.get(scores.size() - 1).get(2);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            scores.add(temp);
                        }
                    }else{
                        infoLabel.setText("Press the submit button instead");
                    }
                }
            }
        });

        shootDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoLabel.setText("");
                int score1, score2, score3;
                score1 = Integer.valueOf(scoreInputs[0].getText());
                score2 = Integer.valueOf(scoreInputs[1].getText());
                score3 = Integer.valueOf(scoreInputs[2].getText());
                if (score1 + score2 + score3 != 26) {
                    infoLabel.setText("Error: scores do not add up to 26");
                } else {
                    if (score1 == 26 || score2 == 26 || score3 == 26) {
                        if(turn == 2){
                            turn = 0;
                        }else{
                            turn++;
                        }
                        passDisplay.setText("Passing - " + passOrder3[turn]);
                        if(score1 == 26){
                            score1 = scores.get(scores.size() - 1).get(0) - 26;
                            if (score1 < 0){
                                score1 = 0;
                            }
                            score2 = scores.get(scores.size() - 1).get(1);
                            score3 = scores.get(scores.size() - 1).get(2);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            scores.add(temp);
                        }else if(score2 == 26){
                            score1 = scores.get(scores.size() - 1).get(0);
                            score2 = scores.get(scores.size() - 1).get(1) - 26;
                            if (score2 < 0){
                                score2 = 0;
                            }
                            score3 = scores.get(scores.size() - 1).get(2);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            scores.add(temp);
                        }else if(score3 == 26){
                            score1 = scores.get(scores.size() - 1).get(0);
                            score2 = scores.get(scores.size() - 1).get(1);
                            score3 = scores.get(scores.size() - 1).get(2) - 26;
                            if (score3 < 0){
                                score3 = 0;
                            }
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            scores.add(temp);
                        }
                    }else{
                        infoLabel.setText("Press the submit button instead");
                    }
                }
            }
        });

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(turn == 0){
                    turn = 2;
                }else{
                    turn--;
                }
                passDisplay.setText("Passing - " + passOrder3[turn]);
                int score1,score2,score3;
                score1 = scores.get(scores.size() - 2).get(0);
                score2 = scores.get(scores.size() - 2).get(1);
                score3 = scores.get(scores.size() - 2).get(2);
                nameDisplay[0].setText(names.get(0) + " - " + score1);
                nameDisplay[1].setText(names.get(1) + " - " + score2);
                nameDisplay[2].setText(names.get(2) + " - " + score3);
                scores.remove(scores.size() - 1);
            }
        });
    }

    public void setUpFourPlayers(){
        this.remove(infoLabel);
        this.remove(nameInput);
        this.remove(submit);
        nameDisplay = new JLabel[4];
        nameDisplay[0] = new JLabel(names.get(0) + " - 0");
        nameDisplay[1] = new JLabel(names.get(1) + " - 0");
        nameDisplay[2] = new JLabel(names.get(2) + " - 0");
        nameDisplay[3] = new JLabel(names.get(3) + " - 0");
        scoreInputs = new JTextField[4];
        scoreInputs[0] = new JTextField(5);
        scoreInputs[1] = new JTextField(5);
        scoreInputs[2] = new JTextField(5);
        scoreInputs[3] = new JTextField(5);
        add(nameDisplay[0]); add(scoreInputs[0]); add(nameDisplay[1]);
        add(scoreInputs[1]); add(nameDisplay[2]); add(scoreInputs[2]);
        add(nameDisplay[3]); add(scoreInputs[3]);
        shootUp = new JButton("add 26 to other players");
        shootDown = new JButton("subtract 26 from your score");
        passDisplay = new JLabel("Passing - Left");
        infoLabel.setText("");
        add(infoLabel);
        add(submit);
        add(shootUp);
        add(shootDown);
        add(goBack);
        add(passDisplay);
        this.revalidate();

        shootUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoLabel.setText("");
                int score1, score2, score3, score4;
                score1 = Integer.valueOf(scoreInputs[0].getText());
                score2 = Integer.valueOf(scoreInputs[1].getText());
                score3 = Integer.valueOf(scoreInputs[2].getText());
                score4 = Integer.valueOf(scoreInputs[3].getText());
                if (score1 + score2 + score3 + score4 != 26) {
                    infoLabel.setText("Error: scores do not add up to 26");
                } else {
                    if (score1 == 26 || score2 == 26 || score3 == 26 || score4 == 26) {
                        if(turn == 3){
                            turn = 0;
                        }else{
                            turn++;
                        }
                        passDisplay.setText("Passing - " + passOrder4[turn]);
                        if(score1 == 26){
                            score1 = scores.get(scores.size() - 1).get(0);
                            score2 = scores.get(scores.size() - 1).get(1) + 26;
                            score2 = checkScore(score2);
                            score3 = scores.get(scores.size() - 1).get(2) + 26;
                            score3 = checkScore(score3);
                            score4 = scores.get(scores.size() - 1).get(3) + 26;
                            score4 = checkScore(score4);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            nameDisplay[3].setText(names.get(3) + " - " + score4);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            temp.add(score4);
                            scores.add(temp);
                        }else if(score2 == 26){
                            score1 = scores.get(scores.size() - 1).get(0) + 26;
                            score1 = checkScore(score1);
                            score2 = scores.get(scores.size() - 1).get(1);
                            score3 = scores.get(scores.size() - 1).get(2) + 26;
                            score3 = checkScore(score3);
                            score4 = scores.get(scores.size() - 1).get(3) + 26;
                            score4 = checkScore(score4);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            nameDisplay[3].setText(names.get(3) + " - " + score4);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            temp.add(score4);
                            scores.add(temp);
                        }else if(score3 == 26){
                            score1 = scores.get(scores.size() - 1).get(0) + 26;
                            score1 = checkScore(score1);
                            score2 = scores.get(scores.size() - 1).get(1) + 26;
                            score2 = checkScore(score2);
                            score3 = scores.get(scores.size() - 1).get(2);
                            score4 = scores.get(scores.size() - 1).get(3) + 26;
                            score4 = checkScore(score4);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            nameDisplay[3].setText(names.get(3) + " - " + score4);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            temp.add(score4);
                            scores.add(temp);
                        }else if(score4 == 26){
                            score1 = scores.get(scores.size() - 1).get(0) + 26;
                            score1 = checkScore(score1);
                            score2 = scores.get(scores.size() - 1).get(1) + 26;
                            score2 = checkScore(score2);
                            score3 = scores.get(scores.size() - 1).get(2) + 26;
                            score3 = checkScore(score3);
                            score4 = scores.get(scores.size() - 1).get(3);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            nameDisplay[3].setText(names.get(3) + " - " + score4);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            temp.add(score4);
                            scores.add(temp);
                        }
                    }else{
                        infoLabel.setText("Press the submit button instead");
                    }
                }
            }
        });

        shootDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoLabel.setText("");
                int score1, score2, score3, score4;
                score1 = Integer.valueOf(scoreInputs[0].getText());
                score2 = Integer.valueOf(scoreInputs[1].getText());
                score3 = Integer.valueOf(scoreInputs[2].getText());
                score4 = Integer.valueOf(scoreInputs[3].getText());
                if (score1 + score2 + score3 + score4 != 26) {
                    infoLabel.setText("Error: scores do not add up to 26");
                } else {
                    if (score1 == 26 || score2 == 26 || score3 == 26 || score4 == 26) {
                        if(turn == 3){
                            turn = 0;
                        }else{
                            turn++;
                        }
                        passDisplay.setText("Passing - " + passOrder4[turn]);
                        if(score1 == 26){
                            score1 = scores.get(scores.size() - 1).get(0) - 26;
                            if (score1 < 0){
                                score1 = 0;
                            }
                            score2 = scores.get(scores.size() - 1).get(1);
                            score3 = scores.get(scores.size() - 1).get(2);
                            score4 = scores.get(scores.size() - 1).get(3);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            nameDisplay[3].setText(names.get(3) + " - " + score4);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            temp.add(score4);
                            scores.add(temp);
                        }else if(score2 == 26){
                            score1 = scores.get(scores.size() - 1).get(0);
                            score2 = scores.get(scores.size() - 1).get(1) - 26;
                            if (score2 < 0){
                                score2 = 0;
                            }
                            score3 = scores.get(scores.size() - 1).get(2);
                            score4 = scores.get(scores.size() - 1).get(3);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            nameDisplay[3].setText(names.get(3) + " - " + score4);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            temp.add(score4);
                            scores.add(temp);
                        }else if(score3 == 26){
                            score1 = scores.get(scores.size() - 1).get(0);
                            score2 = scores.get(scores.size() - 1).get(1);
                            score3 = scores.get(scores.size() - 1).get(2) - 26;
                            if (score3 < 0){
                                score3 = 0;
                            }
                            score4 = scores.get(scores.size() - 1).get(3);
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            nameDisplay[3].setText(names.get(3) + " - " + score4);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            temp.add(score4);
                            scores.add(temp);
                        }else if(score4 == 26){
                            score1 = scores.get(scores.size() - 1).get(0);
                            score2 = scores.get(scores.size() - 1).get(1);
                            score3 = scores.get(scores.size() - 1).get(2);
                            score4 = scores.get(scores.size() - 1).get(3) - 26;
                            if (score4 < 0){
                                score4 = 0;
                            }
                            nameDisplay[0].setText(names.get(0) + " - " + score1);
                            nameDisplay[1].setText(names.get(1) + " - " + score2);
                            nameDisplay[2].setText(names.get(2) + " - " + score3);
                            nameDisplay[3].setText(names.get(3) + " - " + score4);
                            ArrayList<Integer> temp = new ArrayList<>();
                            temp.add(score1);
                            temp.add(score2);
                            temp.add(score3);
                            temp.add(score4);
                            scores.add(temp);
                        }
                    }else{
                        infoLabel.setText("Press the submit button instead");
                    }
                }
            }
        });

        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(turn == 0){
                    turn = 3;
                }else{
                    turn--;
                }
                passDisplay.setText("Passing - " + passOrder4[turn]);
                int score1,score2,score3,score4;
                score1 = scores.get(scores.size() - 2).get(0);
                score2 = scores.get(scores.size() - 2).get(1);
                score3 = scores.get(scores.size() - 2).get(2);
                score4 = scores.get(scores.size() - 2).get(3);
                nameDisplay[0].setText(names.get(0) + " - " + score1);
                nameDisplay[1].setText(names.get(1) + " - " + score2);
                nameDisplay[2].setText(names.get(2) + " - " + score3);
                nameDisplay[3].setText(names.get(3) + " - " + score4);
                scores.remove(scores.size() - 1);
            }
        });
    }

    public int checkScore(int score){
        if(score == 100){
            return 0;
        }
        return score;
    }

}
