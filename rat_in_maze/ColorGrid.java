/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rat_in_maze;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;


public class ColorGrid {
    private static JPanel[][] panels; // Declare as a class variable

    public static void main(String[] args) {
        JFrame frame = new JFrame("Color Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create a panel to hold the grid
        JPanel panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(40, 40)); // Adjust the grid size as per your requirement

        panels = new JPanel[40][40]; // Initialize the panels array

        // Initialize the panels and add mouse listeners
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j] = new JPanel();
                panels[i][j].setBackground(Color.WHITE); // Initial color of panels
                Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
                panels[i][j].setBorder(border);

                final int row = i;
                final int col = j;
                panels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (panels[row][col].getBackground() == Color.CYAN) {
                            panels[row][col].setBackground(Color.WHITE);
                        } else {
                            panels[row][col].setBackground(Color.CYAN); // Color when mouse enters the panel
                        }
                    }
                });

                panelGrid.add(panels[i][j]);
            }
        }

        frame.add(panelGrid, BorderLayout.CENTER);

        // Create and add the Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertPanelGridToMatrix(panels);
            }
        });
        frame.add(submitButton, BorderLayout.SOUTH);

        frame.setSize(800, 800); // Adjust the frame size as per your requirement
        frame.setVisible(true);
    }

    private static void convertPanelGridToMatrix(JPanel[][] panelGrid) {
        int rows = panelGrid.length;
        int cols = panelGrid[0].length;

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Color panelColor = panelGrid[i][j].getBackground();
                if (panelColor.equals(Color.CYAN)) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = 1;
                }
            }
        }

        // Print the matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++)
                System.out.print(matrix[i][j]);
            System.out.print("\n");
        }
        RatMaze rat = new RatMaze();
        rat.solveMaze(matrix);
    }
    
}
