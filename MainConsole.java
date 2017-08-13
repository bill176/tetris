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

        canvas = new Canvas();
        frame.addKeyListener(canvas.getKeyListener());
        frame.add(canvas,BorderLayout.CENTER);
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new MainConsole();
            }
        });
    }
}
