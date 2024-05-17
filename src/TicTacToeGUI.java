import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class TicTacToeGUI implements ActionListener {
    //GUI Properties\\
    JFrame frame;
    JPanel buttPanel;
    JPanel textPanel;
    JButton[][] buttons; // 'JButton[][]' Creates a 2D array of buttons, so we can have a grid
    JLabel textLabel;

    //Properties\\
    int gridSize;
    int turn = 1; //Which player's turn it is
    ArrayList<Integer> xPosition; //Grid's X Axis
    ArrayList<Integer> yPosition; //Grid's Y Axis

    int turnCount = 0;

    /////////////////////////////////////////////////////////////METHODS\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public TicTacToeGUI()
    {   //Variables\\
        xPosition = new ArrayList<>();
        yPosition = new ArrayList<>(); //Instantiates Arraylists for coordinates
        gridSize = 3; //For a standard 3x3 Grid

        //Frame Setup\\
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Will Close application on exit
        frame.pack(); //Will fit window to size of contents
        frame.setVisible(true); //So we can see it
        frame.setLayout(new BorderLayout()); //We want a border layout

        //Text Panel Setup\\
        textPanel = new JPanel();
        textPanel.setVisible(true);
        textPanel.setBackground(Color.DARK_GRAY);

        //Button Panel Setup\\
        buttPanel = new JPanel();
        buttPanel.setVisible(true);
        buttPanel.setLayout(new GridLayout(gridSize, gridSize)); //Creates a Grid according to the size given by the gridSize Parameter (3x3)

        //Text Label Setup\\
        textLabel = new JLabel();
        textLabel.setHorizontalAlignment(JLabel.CENTER); //Text of button when pressed, e.g. will show how many bombs are next to button.
        textLabel.setFont(new Font("MV Bold", Font.BOLD, 20));
        textLabel.setForeground(Color.WHITE);

        //Buttons Setup\\
        buttons = new JButton[gridSize][gridSize];
        for (int i=0; i < buttons.length; i++)
        {
            for(int j=0; j < buttons.length; j++) //This will allow us to go through every button in the array, going through each column of a row before moving onto the next row
            {
                buttons[i][j] = new JButton(); //Instantiates a button for each array position
                buttons[i][j].setFocusable(false); //Cannot be navigated to via keyboard
                buttons[i][j].setFont(new Font("MV Bold", Font.BOLD, 12));
                buttons[i][j].setText(" "); //So the button is empty when not clicked
                buttons[i][j].addActionListener(this); //Will trigger the actionPerformed method when a button is pressed
                buttPanel.add(buttons[i][j]); //Adds Button to the panel
            }
        }
        //Combining elements\\
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(buttPanel);

        frame.setSize(570,570);
        frame.revalidate(); //Refreshes GUI so that all elements of the GUI have their assigned properties
        frame.setLocationRelativeTo(null); //Frame will be in center of the screen
    }

    ///////////////CHECK FOR WIN/DRAW\\\\\\\\\\\\\\\\
    public void check (int x, int y)
    {

        int counter = 0; //Counter for how many in a row

        //If Player 1
        if (turn == 1){
            //Setting the Chosen Button\\
            buttons[x][y].setText("X"); //Will change text and button colour. Text will now display an X
            buttons[x][y].setBackground(Color.GRAY);
            buttons[x][y].setForeground(Color.RED);

            //FOR HORIZONTAL MATCH 3\\
            for (JButton[] button : buttons) { //Determines row (i)
                for (int j = 0; j < buttons[0].length; j++) { //Goes through Each element on the row
                    if (button[j].getText().equals("X")) { //Add 1 for every 1 found in a row
                        counter = counter + 1;
                    }
                }
                if (counter == 3) //We need to put a seperate if statement for the counter in every for loop, as otherwise it would count the same placement multiple times if we were to just put this at the end of the method
                {
                    GameOver(true); //Win will be declared if after checking a horizontal row, 3 of the same Letter are found (3 in a row)
                }
                counter = 0; //We need to reset the counter after each directional check. This needs to be done before i increments so we the counter doesnt carry over and cause a player to win once 3 buttons are pressed
            }

            //FOR VERTICAL MATCH 3\\
            for (int i = 0; i < buttons[0].length; i++) { //Determines Determine column
                for (JButton[] button : buttons) { //Goes through Each element int the column (j)
                    if (button[i].getText().equals("X")) { //Add 1 for every 1 found in a row. j and i are switched as we are incrementing j each time not i
                        counter = counter + 1;
                    }
                }
                if (counter == 3)
                {
                    GameOver(true); //Win will be declared if after checking a horizontal row, 3 of the same Letter are found (3 in a row)
                }
                counter = 0; //Reset Counter
            }

            //FOR DIAGONAL MATCH 3\\
            if (buttons[1][1].getText().equals("X")) //Checks if middle has been designated as "X". A Diagonal match is only possible if the middle has been pressed
            {
                if (buttons[0][2].getText().equals("X") && buttons[2][0].getText().equals("X"))
                {
                    GameOver(true); //Win will be declared if after checking a horizontal row, 3 of the same Letter are found (3 in a row)
                }

                //Opposite Diagonal\\
                if (buttons[0][0].getText().equals("X") && buttons[2][2].getText().equals("X"))
                {
                    GameOver(true); //Win will be declared if after checking a horizontal row, 3 of the same Letter are found (3 in a row)
                }
            }
        }

        //If Player 2
        if (turn == 2){
            //Setting the Chosen Button\\
            buttons[x][y].setText("O"); //Will change text and button colour. Text will now display a nought
            buttons[x][y].setBackground(Color.GRAY);
            buttons[x][y].setForeground(Color.GREEN);

            //FOR HORIZONTAL MATCH 3\\
            for (JButton[] button : buttons) { //Determines row (i)
                for (int j = 0; j < buttons[0].length; j++) { //Goes through Each element on the row
                    if (button[j].getText().equals("O")) { //Add 1 for every 1 found in a row
                        counter = counter + 1;
                    }
                }
                if (counter == 3) //We need to put a seperate if statement for the counter in every for loop, as otherwise it would count the same placement multiple times if we were to just put this at the end of the method
                {
                    GameOver(true); //Win will be declared if after checking a horizontal row, 3 of the same Letter are found (3 in a row)
                }
                counter = 0; //Reset Counter
            }


            //FOR VERTICAL MATCH 3\\
            for (int i = 0; i < buttons[0].length; i++) { //Determines Determine column
                for (JButton[] button : buttons) { //Goes through Each element int the column (j)
                    if (button[i].getText().equals("O")) { //Add 1 for every 1 found in a row. j and i are switched as we are incrementing j each time not i
                        counter = counter + 1;
                    }
                }
                if (counter == 3) //We need to put a seperate if statement for the counter in every for loop, as otherwise it would count the same placement multiple times if we were to just put this at the end of the method
                {
                    GameOver(true); //Win will be declared if after checking a horizontal row, 3 of the same Letter are found (3 in a row)
                }
                counter = 0; //Reset Counter
            }

            //CHECK FOR DIAGONAL MATCH\\
            if (buttons[1][1].getText().equals("O")) //Checks if middle has been designated as "O". A Diagonal match is only possible if the middle has been pressed
            {
                if (buttons[0][2].getText().equals("O") && buttons[2][0].getText().equals("O"))
                {
                    GameOver(true); //Win will be declared if after checking a horizontal row, 3 of the same Letter are found (3 in a row)
                }
                //Opposite Diagonal\\
                if (buttons[0][0].getText().equals("O") && buttons[2][2].getText().equals("O"))
                {
                    GameOver(true); //Win will be declared if after checking a horizontal row, 3 of the same Letter are found (3 in a row)
                }

            }
        }

        //DRAW DECIDED THIS GOES AT THE END OF THE METHOD TO ENSURE IF VICTORY IS MADE ON 9th TURN IT WILL BE REGISTERED\\
        if  (turnCount == 9) //As only 9 buttons will be present, we can end game as a draw when all 9 have been picked
        {
            GameOver(false); //Will run GameOver Method, telling it a draw has been found
        }
    }

    ///////////////CHANGE PLAYER TURN\\\\\\\\\\\\\\\
    public void changePlayer ()
    {
        if (turn == 1) {
            turn = 2;   //Will Switch player turns every time a button is pressed
        }
        else {
            turn = 1;
        }
    }

    ///////////////GAME OVER\\\\\\\\\\\\\\\
    public void GameOver(boolean condition) {
        //If User Wins\\
        if (condition) {
            if (turn == 1) {
                textLabel.setForeground(Color.RED);
                textLabel.setText("Player 1 Wins!!!"); //Title becomes Green and congratulates user 1 for winning
            }
            else {
                textLabel.setForeground(Color.GREEN);
                textLabel.setText("Player 2 Wins!!!"); //Title becomes Red and congratulates user 2 for winning
            }
        }

        //If Amount of squares pressed = 9 and no winner is found\\
        if (!condition) {
            textLabel.setForeground(Color.WHITE);
            textLabel.setText("Draw!"); //Title becomes red and says "Draw!"
        }

        //Disable Buttons//
        for (JButton[] button : buttons)
        {
            for (int j = 0; j < buttons[0].length; j++)
            {
                button[j].setEnabled(false);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                if (e.getSource() == buttons[i][j]) { //Will Check which button was pressed and perform the designated output when the button the action was performed on is found
                    turnCount = turnCount + 1; //Adds 1 to the turn counter after each turn
                    check(i, j); //Passes through the x and y-axis, so they can be used for a check to see if a winner has been made. As well as this, The coordinates will be marked as that player's side
                    changePlayer(); //Next Player's Turn.
                    buttons[i][j].removeActionListener(this); //This will disable the button from being clicked again. We are using this instead of setdisabled as setdisabled will grey out the button
                }
            }
        }
    }
}
