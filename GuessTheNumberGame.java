//Assignment 4
//Guess the Number Game
//Angelo Kwak | ajk5825
package guessthenumbergame;

/**
 *
 * @author Angelo
 */
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;

public class GuessTheNumberGame extends JFrame {
    //Declare necessary variables for app
    private int number, guessCount;
    private int lastDistance;
    private JTextField guessInput;
    private JLabel text1, text2, message;
    private JButton newGame;
    private Color background;
    Container container;
    //Constructor
    public GuessTheNumberGame() {
        super("Guess the Number Game");
        guessCount = 0;
        background = Color.lightGray;
        text1 = new JLabel( "I have a number between 1 and 1000. Can you guess my number?" );
        text2 = new JLabel( "Please enter your first guess:" );
        guessInput = new JTextField(5);
        guessInput.addActionListener(new GuessHandler());
        message = new JLabel("(Hints to help you will appear here)");
        newGame = new JButton("New Game");
        //For when New Game button is pressed
        newGame.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent x) 
            {
                message.setText("(Hints to help you will appear here)");
                guessInput.setText("");
                guessInput.setEditable(true);
                background = Color.lightGray;
                numberGen();
                repaint();
            }
        }
            );
        //Adds fields to app window
        container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(text1);
        container.add(text2);
        container.add(guessInput);
        container.add(message);
        container.add(newGame);
        setSize(300,150);
        setVisible(true);
        numberGen();
        }
        //Generates random number between 1 and 1000
        public void numberGen() {
            number = (int) (Math.random() * 1000+1);
            System.out.println("My number is: "+number);
        }

        public void paint(Graphics g) {
            super.paint(g);
            container.setBackground(background);
        }
        //Handles guess input and changing of color
        public void react(int guess) {
            guessCount++;
            int currentDistance = 1000;
            //This is the first guess. No color changes for this one 
            if (guessCount == 1) {
                lastDistance = Math.abs(guess - number);
                
                //First guess was correct!
                if (guess == number) {
                    message.setText("Correct! Want to play again?");
                    //When correct answer is found, color changes to green (just for fun)
                    background = Color.green;
                    //Text field is no longer editable
                    guessInput.setEditable(false);
                    //The guess counter is set back to 0 for the new game
                    guessCount = 0;
                    
                }
                // Initial guess is too high
                else if (guess > number) {
                    message.setText("Too High. (Try a lower number.)");
                }
                //Initial guess is too low
                else {
                    message.setText("Too Low. (Try a higher number.)");
                }
            }
            //Guess count is now greater than 1. Color will now change (hot/cold)
            else {
                //This is the distance the user guess is from the correct number
                //Color of background will now change to indicate warmer(closer)
                //or colder(further) from correct answer
                currentDistance = Math.abs(guess-number);
                //The guess is too high
                if (guess > number) {
                    message.setText("Too High. (Try a lower number.)");
                    background = (currentDistance <= lastDistance) ?
                        Color.red : Color.cyan;
                    lastDistance = currentDistance;  
                }
                //The guess is too low
                else if (guess < number) {
                    message.setText("Too Low. (Try a higher number.)");
                    background = (currentDistance <= lastDistance) ?
                            Color.red : Color.cyan;
                    lastDistance = currentDistance;
                }
                
                //Guess was correct!
                else {
                    message.setText("Correct! Want to play again?");
                    //When correct answer is found, color changes to green (just for fun)
                    background = Color.green;
                    //Text field is no longer editable
                    guessInput.setEditable(false);
                    //The guess counter is set back to 0 for the new game
                    guessCount = 0;
                }
                repaint();
            }
        }
        
        class GuessHandler implements ActionListener {
            public void actionPerformed(ActionEvent x) {
                int guess = Integer.parseInt(guessInput.getText());
                react(guess);
            }
        }
    
    //Main function
    public static void main(String[] args) {
        GuessTheNumberGame app = new GuessTheNumberGame();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(500,200);
        app.setVisible(true);
    }   
}
