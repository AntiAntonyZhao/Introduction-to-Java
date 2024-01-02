

import java.io.*;

public class Maze {
	private final int dimension = 15;
	public int counter = 0;

	// Checks if we can move to (x,y)
	boolean canMove(char maze[][], boolean found, int x, int y) {
		if(found) { // 找到了，可以走回头路0
			return (x >= 0 && x < dimension && y >= 0 && y < dimension && (maze[x][y] == '.' || maze[x][y] == '0'));
		} else { // 没找到。。found = false
			return (x >= 0 && x < dimension && y >= 0 && y < dimension && (maze[x][y] == '.' || maze[x][y] == 'k'));
		}
	}


	boolean solveMaze(char maze[][]) {

		if (!solveMazeUtil(maze, false, 0, 1)) {
			System.out.print("Solution doesn't exist\n");
			return false;
		}
		return true;
	}

	// A recursive function to solve Maze problem
	boolean solveMazeUtil(char maze[][], boolean found, int x, int y) {
		// please do not delete/modify the next line!
		counter++;
		
		if(maze[14][13]=='1') {
			return true;
		}else if(canMove(maze,found,x,y)) { //查当下坐标xy
			if(maze[x][y]=='k') {  // 遇到k前后标点情况
				found =true;
			}
			if(found) {
				if(maze[x][y]!='k')maze[x][y]='1';
			}else {
				maze[x][y]='0';
			}
			
			//...尝试周围的四个点，进入recursive。。
			if (solveMazeUtil(maze, found, x+1, y)) { 
				return true; 
			}
			if (solveMazeUtil(maze, found, x-1, y)) {
				return true;
			}
			if (solveMazeUtil(maze, found, x, y+1)) {
				return true;
			}
			if (solveMazeUtil(maze, found, x, y-1)) {
				return true;
			}
		}
		return false;
	}






	//Loads maze from text file
	char[][] loadMaze(String directory) throws IOException{
		char[][] maze = new char[dimension][dimension];

//				String base = System.getProperty("user.dir");
//				directory = base+"/src/A3_M/mazes/m3.txt";
		//		m0 = solver.loadMaze(base+"/src/A3_M/mazes/m0.txt");
		//		m1 = solver.loadMaze(base+"/src/A3_M/mazes/m1.txt");
		//		m2 = solver.loadMaze(base+"/src/A3_M/mazes/m2.txt");
		//		m3 = solver.loadMaze(base+"/src/A3_M/mazes/m3.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(directory))) {
			String line;
			int row = 0;
			while ((line = br.readLine()) != null) {
				for (int col = 0; col < line.length(); col++){
					maze[row][col] = line.charAt(col);
				}
				row++;
			}
		}
		return maze;

	}

	//Prints maze
	private static void printMaze(char[][] maze) {
		for (int i = 0; i < maze[0].length; i++) {
			for (int j = 0; j < maze[0].length; j++)
				System.out.print(" " + maze[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}


	public static void main(String args[]) {
		Maze m = new Maze();
		for (int i = 0; i < 4; i++) {
			try {
				char[][] myMaze = m.loadMaze("mazes/m"+i+".txt");
				System.out.println("\nMaze "+i);
				Maze.printMaze(myMaze);
				if(m.solveMaze(myMaze)){
					Maze.printMaze(myMaze);
				}
			} catch (Exception e){
				System.out.print("File was not found.");
			}

		}
	}
}
