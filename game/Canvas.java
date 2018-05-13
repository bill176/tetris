import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Canvas extends JPanel{

    public static final int SIDE_OF_SQUARE = 25;
    public static final int WIDTH = 14;
    public static final int HEIGHT = 22;

    private static final int NUM_OF_SHAPES = 7;
    private static final int NUM_OF_ROTATIONS = 4;
    private static final int REFRESH_RATE = 1000;

    private static final int ONE_SHAPE = 0;
    private static final int L_SHAPE = 1;
    private static final int J_SHAPE = 2;
    private static final int Z_SHAPE = 3;
    private static final int S_SHAPE = 4;
    private static final int SQUARE = 5;
    private static final int T_SHAPE = 6;

    public static Shape [][] shapes = new Shape[NUM_OF_SHAPES][NUM_OF_ROTATIONS];
    static{
        shapes[ONE_SHAPE] = new Shape[]{new Shape(Color.GREEN, new Coordinates(1,0), new Coordinates(0,0), new Coordinates(2,0), new Coordinates(3,0)),
                                        new Shape(Color.GREEN, new Coordinates(1,0), new Coordinates(1,1), new Coordinates(1,2), new Coordinates(1,3)),
                                        new Shape(Color.GREEN, new Coordinates(1,0), new Coordinates(0,0), new Coordinates(2,0), new Coordinates(3,0)),
                                        new Shape(Color.GREEN, new Coordinates(1,0), new Coordinates(1,1), new Coordinates(1,2), new Coordinates(1,3))};
        
        shapes[L_SHAPE] = new Shape[]{  new Shape(Color.CYAN,new Coordinates(0,1),new Coordinates(0,0),new Coordinates(0,2), new Coordinates(1,2)),
                                        new Shape(Color.CYAN,new Coordinates(-1,1),new Coordinates(0,1),new Coordinates(1,1), new Coordinates(1,0)),
                                        new Shape(Color.CYAN,new Coordinates(0,1),new Coordinates(0,0),new Coordinates(0,2), new Coordinates(-1,0)),
                                        new Shape(Color.CYAN,new Coordinates(0,1),new Coordinates(-1,1),new Coordinates(1,1), new Coordinates(-1,2))};

        shapes[J_SHAPE] = new Shape[]{  new Shape(Color.ORANGE, new Coordinates(1,0), new Coordinates(1,1), new Coordinates(1,2), new Coordinates(0,2)),
                                        new Shape(Color.ORANGE, new Coordinates(0,1), new Coordinates(1,1), new Coordinates(2,1), new Coordinates(2,2)),
					new Shape(Color.ORANGE, new Coordinates(1,0), new Coordinates(1,1), new Coordinates(1,2), new Coordinates(2,0)),
                                        new Shape(Color.ORANGE, new Coordinates(0,1), new Coordinates(1,1), new Coordinates(2,1), new Coordinates(0,0))};

        shapes[Z_SHAPE] = new Shape[]{  new Shape(Color.YELLOW, new Coordinates(0,1),new Coordinates(1,0),new Coordinates(1,1), new Coordinates(0,2)),
                                        new Shape(Color.YELLOW, new Coordinates(-1,0),new Coordinates(0,0),new Coordinates(1,1), new Coordinates(0,1)),
        				new Shape(Color.YELLOW, new Coordinates(0,1),new Coordinates(1,0),new Coordinates(1,1), new Coordinates(0,2)),
                                        new Shape(Color.YELLOW, new Coordinates(-1,0),new Coordinates(0,0),new Coordinates(1,1), new Coordinates(0,1))};
        
        shapes[S_SHAPE] = new Shape[]{  new Shape(Color.RED, new Coordinates(0,1),new Coordinates(0,0),new Coordinates(1,1), new Coordinates(1,2)),
                                        new Shape(Color.RED, new Coordinates(0,1),new Coordinates(0,0),new Coordinates(1,0), new Coordinates(-1,1)),
                                        new Shape(Color.RED, new Coordinates(0,1),new Coordinates(0,0),new Coordinates(1,1), new Coordinates(1,2)), 
                                        new Shape(Color.RED, new Coordinates(0,1),new Coordinates(0,0),new Coordinates(1,0), new Coordinates(-1,1))};

        shapes[SQUARE] = new Shape[]{   new Shape(Color.MAGENTA, new Coordinates(0,0),new Coordinates(0,1),new Coordinates(1,0), new Coordinates(1,1)),
                                        new Shape(Color.MAGENTA, new Coordinates(0,0),new Coordinates(0,1),new Coordinates(1,0), new Coordinates(1,1)),
                                        new Shape(Color.MAGENTA, new Coordinates(0,0),new Coordinates(0,1),new Coordinates(1,0), new Coordinates(1,1)),
                                        new Shape(Color.MAGENTA, new Coordinates(0,0),new Coordinates(0,1),new Coordinates(1,0), new Coordinates(1,1))};
        
        shapes[T_SHAPE] = new Shape[]{  new Shape(Color.PINK, new Coordinates(1,1), new Coordinates(0,1), new Coordinates(2,1), new Coordinates(1,0)),
                                        new Shape(Color.PINK, new Coordinates(1,1), new Coordinates(0,1), new Coordinates(1,2), new Coordinates(1,0)),
                                        new Shape(Color.PINK, new Coordinates(1,1), new Coordinates(0,1), new Coordinates(2,1), new Coordinates(1,2)),
                                        new Shape(Color.PINK, new Coordinates(1,1), new Coordinates(1,2), new Coordinates(2,1), new Coordinates(1,0)),};
    }

    public int[][] plane;
    private Random rand;
    private int currentShape;
    private int nextShape;
    private int currentRotation;
    private Coordinates currentCoordinate;
    private KeyListener theKeyListener;
    private Timer timer;
    private int score;
    private boolean gameActive;

    public Canvas(){
	score = 0;
	gameActive = true;
        plane = new int[WIDTH][HEIGHT];
        for(int i = 0; i < WIDTH; i++)
            for(int j = 0; j < HEIGHT; j++)
                if(i == 0 || i == WIDTH - 1 || j == HEIGHT - 1)
                    plane[i][j] = -2;
                else 
                    plane[i][j] = -1;

        ActionListener theActionListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
		if(gameActive)
	                moveDown();
            }
        };

        timer = new Timer(REFRESH_RATE, theActionListener);
        timer.start();

        rand = new Random();
        nextShape = rand.nextInt(7);
        generateShape();

        theKeyListener = new KeyListener(){
            public void keyTyped(KeyEvent e){}
            public void keyReleased(KeyEvent e){}
	    @Override
            public void keyPressed(KeyEvent e){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        if(gameActive)
				rotate();
                        break;
                    case KeyEvent.VK_DOWN:
                        if(gameActive)
                        	moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        if(gameActive)
                       		moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(gameActive)
                        	moveRight();
                        break;
                    case KeyEvent.VK_SPACE:
                        if(gameActive)
                        	moveButtom();
                        break;
		    case KeyEvent.VK_P:
		    	gameActive = false;
			break;
		    case KeyEvent.VK_R:
		    	gameActive = true;
                }
            }
        };
    }

    private void generateShape(){
        currentShape = nextShape;
        nextShape = rand.nextInt(7);

        currentRotation = 0;

        currentCoordinate = new Coordinates(6,0);

        for(Coordinates c : shapes[currentShape][currentRotation].getCoordinates())
            if(plane[currentCoordinate.getX() + c.getX()][currentCoordinate.getY() + c.getY()] != -1){
       		gameActive = false;
                JOptionPane.showMessageDialog(null, "Game Over!");
                break;
            }
        repaint();
    }

    private void resetShape(){
        for(Coordinates c : shapes[currentShape][currentRotation].getCoordinates())
            plane[currentCoordinate.getX() + c.getX()][currentCoordinate.getY() + c.getY()] = currentShape;
        
        generateShape();
    }

    private boolean canMove(Coordinates coordinate, int shape, int rotation){
        for(Coordinates c : shapes[shape][rotation].getCoordinates())
            if(coordinate.getX() + c.getX() >= WIDTH || coordinate.getX() + c.getX() <= 0 
                || coordinate.getY() + c.getY() >= HEIGHT - 1 || coordinate.getY() + c.getY() < 0
                || plane[coordinate.getX() + c.getX()][coordinate.getY() + c.getY()] != -1)
                    return false;
        return true;
    }

    private boolean rotate(){
        if(canMove(currentCoordinate, currentShape, (currentRotation + 1) % 4)){
            currentRotation = (currentRotation + 1) % 4;
            repaint();
            return true;
        }
        return false;
    }

    private boolean moveDown(){
	if(canMove(new Coordinates(currentCoordinate.getX(), currentCoordinate.getY() + 1), currentShape, currentRotation)){
        	currentCoordinate = new Coordinates(currentCoordinate.getX(), currentCoordinate.getY() + 1);
        	repaint();
	        return true;
	}
	resetShape();
	clearRow();
	return false;
    }

    private boolean moveLeft(){
        if(canMove(new Coordinates(currentCoordinate.getX() - 1, currentCoordinate.getY()), currentShape, currentRotation)){
            currentCoordinate = new Coordinates(currentCoordinate.getX() - 1, currentCoordinate.getY());
            repaint();
	    return true;
        }
	return false;
    }

    private boolean moveRight(){
        if(canMove(new Coordinates(currentCoordinate.getX() + 1, currentCoordinate.getY()), currentShape, currentRotation)){
            currentCoordinate = new Coordinates(currentCoordinate.getX() + 1, currentCoordinate.getY());
            repaint();
	    return true;
        }
        return false;
    }

    private void moveButtom(){
        while(moveDown());
    }

    private boolean isRowFilled(int y){
	for(int x = 1; x < WIDTH - 1; x++)
		if(plane[x][y] == -1)
			return false;
	return true;
    }

    private void clearRow(){
    	int i = HEIGHT - 2;
	while(i > 0){
		if(isRowFilled(i)){
			for(int x = 1; x < WIDTH - 1; x++){
				for(int y = i; y > 0; y--)
					plane[x][y] = plane[x][y - 1];
				plane[x][0] = -1;
			}
			score += 10;
		}else
			i--;
	}
	repaint();
    }

    public KeyListener getKeyListener(){
        return theKeyListener;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(Coordinates c : shapes[currentShape][currentRotation].getCoordinates())
            plane[currentCoordinate.getX() + c.getX()][currentCoordinate.getY() + c.getY()] = currentShape;

        for(int i = 0; i < WIDTH; i++)
            for(int j = 0; j < HEIGHT; j++)
                paintSquare(g, new Coordinates(i, j), plane[i][j]);

        for(Coordinates c : shapes[currentShape][currentRotation].getCoordinates())
            plane[currentCoordinate.getX() + c.getX()][currentCoordinate.getY() + c.getY()] = -1;

	g.setColor(Color.BLACK);
	g.drawString("Next shape", (WIDTH + 2) * SIDE_OF_SQUARE, SIDE_OF_SQUARE);
	
	for(Coordinates c : shapes[nextShape][0].getCoordinates())
		paintSquare(g, new Coordinates(c.getX() + WIDTH + 2, c.getY() + 3), nextShape);
	
	g.setColor(Color.BLACK);
	g.drawString("Score: " + String.valueOf(score), (WIDTH + 2) * SIDE_OF_SQUARE, 8 * SIDE_OF_SQUARE);
    }

    private void paintSquare(Graphics g, Coordinates topLeftPoint, int color){
        
        if(color == -1)
            return;
        
        g.setColor(Color.BLACK);
        g.drawRect(topLeftPoint.getX() * SIDE_OF_SQUARE, 
            topLeftPoint.getY() * SIDE_OF_SQUARE,
            SIDE_OF_SQUARE, 
            SIDE_OF_SQUARE);
        
        if(color >= 0 && color < NUM_OF_SHAPES)
            g.setColor(shapes[color][currentRotation].getColor());
        else if(color == -2)
            g.setColor(Color.WHITE);
        
        g.fillRect(topLeftPoint.getX() * SIDE_OF_SQUARE, 
            topLeftPoint.getY() * SIDE_OF_SQUARE,
            SIDE_OF_SQUARE, 
            SIDE_OF_SQUARE);
    }
}
