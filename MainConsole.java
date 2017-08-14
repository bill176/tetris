package tetris.game;

import java.awt.*;
import javax.swing.*;

public class MainConsole{

    private JFrame frame;
    private Canvas canvas;

    public MainConsole(){
        frame = new JFrame("Tetris");
        frame.setSize((Canvas.WIDTH + 7) * Canvas.SIDE_OF_SQUARE,
            (Canvas.HEIGHT + 1) * Canvas.SIDE_OF_SQUARE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
	frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        frame.addKeyListener(canvas.getKeyListener());
        frame.add(canvas,BorderLayout.CENTER);
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
		JOptionPane.showMessageDialog(null, 	"Press [up] key to rotate the blocks\n" + 
							"Press [down] key to move the blocks down\n" + 
							"Press [left] key to move the blocks to the left\n" +
							"Press [right] key to move the blocks to the right\n" +
							"Press [space] key to move the blocks all the way down\n" + 
							"Press [P] key to pause the game\n" +
							"Press [R] key to resume the game\n");
                new MainConsole();
            }
        });
    }
}
