import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;

public class MainClass {
	
	public static char[][] ConvertImagetoChar(File imagefile) throws IOException{
		BufferedImage image = ImageIO.read(imagefile);
		int width = image.getWidth();
		int height = image.getHeight();
		
		char[][] mazeArray = new char[height][width];
		
		
		for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                // Simple binarization: if close to white, it's a path, else a wall
                if ((pixel & 0xFFFFFF) > 0x808080) { // Check if pixel is relatively light
                    mazeArray[y][x] = ' '; // Path
                } else {
                    mazeArray[y][x] = '#'; // Wall
                }
            }
        }
        return mazeArray;
	}
	
	public static void sendtoOutputFile(char[][] maze) throws IOException{
		PrintWriter writer = new PrintWriter("/Users/darrenweng/git/MazeSolver_AI/Maze_solver_AI/src/ConvertedImage.txt");
		
		for (char[] row : maze) {
            writer.println(new String(row));
		}
		writer.flush();
		writer.close();
	}
	
	public static void main(String args []) throws IOException {
		File mazefile = new File("/Users/darrenweng/git/MazeSolver_AI/Maze_solver_AI/src/maze-line-black-icon-png_309317.jpg");	
		
		
		char[][] maze = ConvertImagetoChar(mazefile);
		sendtoOutputFile(maze);

	}
}
