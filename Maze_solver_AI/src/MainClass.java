import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.PrintWriter;

public class MainClass {
	
	public static char[][] ConvertImagetoChar(File imagefile) throws IOException{
		BufferedImage image = ImageIO.read(imagefile);
		BufferedImage ToScaleImage = reSizeImage(image);
		
		int width = ToScaleImage.getWidth();
		int height = ToScaleImage.getHeight();
		
		char[][] mazeArray = new char[height][width];
		
		
		for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = ToScaleImage.getRGB(x, y);
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
	
		public static BufferedImage reSizeImage(BufferedImage imageFile){
			int newheight = 80;
			int newwidth = 80;
			
			BufferedImage resizedImage = new BufferedImage(newwidth, newheight, imageFile.getType());
			
			Graphics2D g2d = resizedImage.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			
			g2d.drawImage(imageFile,0,0,newwidth, newheight, null);
			g2d.dispose();
			
			return resizedImage;
			//File mazefile = new File("/Users/darrenweng/git/MazeSolver_AI/Maze_solver_AI/src/maze-line-black-icon-png_309317.jpg");	
		}
	
	
	public static void main(String args []) throws IOException {
		File mazefile = new File("/Users/darrenweng/git/MazeSolver_AI/Maze_solver_AI/src/maze-line-black-icon-png_309317.jpg");	
		//reSizeImage();
		
		char[][] maze = ConvertImagetoChar(mazefile);
		sendtoOutputFile(maze);

	}
}
