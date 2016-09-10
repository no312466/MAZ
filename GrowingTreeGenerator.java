package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrowingTreeGenerator extends Maze3dGeneratorBase {

	private Random rand = new Random();
	@Override
	public Maze3d generate(int floor, int row, int column) {
		Maze3d maze3d = new Maze3d(floor, row, column);
		
		// Place walls in every cell
		initalize(maze3d);
		// List of positions
		List<Position> cells = new ArrayList<Position>();
		// Choose random start position and mark it as free
		Position startPos = getRandomPosition(maze3d);
		cells.add(startPos);
		maze3d.setStart(startPos);
		//maze3d.setFree(startPos);
		
		// TODO: Allow the user to chose the method for selecting the cell (random cell or last cell)
		// Currently choosing the last cell 
		while (!cells.isEmpty()) {
			Position p = cells.get(cells.size() - 1);
		
			// Get all unvisited neighbors
			List<Position> neighbors = getUnvisitedNeighbours(maze3d, p);
			if(neighbors.size() > 0) { // There are unvisited neighbors
				// Choose a random neighbor
				int idx = rand.nextInt(neighbors.size());
				Position neighbor = neighbors.get(idx);
			
				createPassageBetweenCells(maze3d,p,neighbor);
				cells.add(neighbor);
			}
			else {
				cells.remove(p);
			}
		}
		
		Position goalPosition = getRandomGoalPosition(maze3d);
		maze3d.setGoal(goalPosition);

		return maze3d;
	}
	
	public Maze3d RandomGenerate(int floor, int row, int column) {
		Maze3d maze3d = new Maze3d(floor, row, column);
		
		// Place walls in every cell
		initalize(maze3d);
		// List of positions
		List<Position> cells = new ArrayList<Position>();
		// Choose random start position and mark it as free
		Position startPos = getRandomPosition(maze3d);
		cells.add(startPos);
		maze3d.setStart(startPos);
		//maze3d.setFree(startPos);
		
		// TODO: Allow the user to chose the method for selecting the cell (random cell or last cell)
		while (cells.size()>0) {
			// Currently choosing the random cell 
			int c = rand.nextInt(cells.size());
			Position p = cells.get(c);
		
			// Get all unvisited neighbors
			List<Position> neighbors = getUnvisitedNeighbours(maze3d, p);
			if(neighbors.size() > 0) { // There are unvisited neighbors
				// Choose a random neighbor
				int idx = rand.nextInt(neighbors.size());
				Position neighbor = neighbors.get(idx);
			
				createPassageBetweenCells(maze3d,p,neighbor);
				cells.add(neighbor);
			}
			else {
				cells.remove(p);
			}
		}
		
		Position goalPosition = getRandomGoalPosition(maze3d);
		maze3d.setGoal(goalPosition);

		return maze3d;
	}
	
	private void initalize(Maze3d maze3d){
		// Initialize maze with walls
		int [][][] temp = maze3d.getMaze3d();
		for (int i = 0 ; i < maze3d.getfloor(); i++) 
			for(int j = 0 ; j < maze3d.getrow();j++) 
				for(int k = 0; k < maze3d.getcolumn(); k++) {
					//p= new Position (i,j,k);
					//maze3d.setWall(p);
					temp[i][j][k] = 1 ;
				}
	}
	private Position getRandomPosition(Maze3d maze3d)
	{
		// Method for (even) random position in the maze
		//int z = (int) ((Math.random()*(maze3d.getfloor()-1-1+1))+1);
		int z = rand.nextInt(maze3d.getfloor()-2)+1;
				
		while(z % 2 == 0) {
		     z = rand.nextInt(maze3d.getfloor()-2)+1;  
		}
		
		int y = rand.nextInt(maze3d.getrow()-2)+1; 
		while(y % 2 == 0) {
			y = rand.nextInt(maze3d.getrow()-2)+1;
		}
			
		int x = rand.nextInt(maze3d.getcolumn()-2)+1; 
		while(x % 2 == 0) {
			x = rand.nextInt(maze3d.getcolumn()-2)+1;; 
		}
		return new Position (z,y,x);
	}
	private Position getRandomGoalPosition(Maze3d maze3d){ 
		// Method for random goal position
		int[][][] maze = maze3d.getMaze3d();
		//Position p=maze3d.getStart();
		
		int z = rand.nextInt(maze3d.getfloor());
		int y = rand.nextInt(maze3d.getrow());
		int x = rand.nextInt(maze3d.getcolumn());
		
		while (maze[z][y][x] == Maze3d.WALL)
		{
			z = rand.nextInt(maze3d.getfloor());
			y = rand.nextInt(maze3d.getrow());
			x = rand.nextInt(maze3d.getcolumn());
		}
		return new Position (z,y,x);
	}
	private List<Position> getUnvisitedNeighbours (Maze3d maze3d, Position p) {
		//Method for unvisited neighbors
		int[][][] maze = maze3d.getMaze3d();
		List<Position> neighbors = new ArrayList<Position>();
	
		if(p.x - 2 > 0) {
			if(maze[p.z][p.y][p.x-2] == Maze3d.WALL) {
				neighbors.add(new Position(p.z,p.y,p.x-2));
			}
		}
		if(p.x + 2 < maze3d.getcolumn()-1) {
			if(maze[p.z][p.y][p.x+2] == Maze3d.WALL) {
				neighbors.add(new Position(p.z,p.y,p.x+2));
				}
		}
		if(p.y - 2 > 0) {
			if(maze[p.z][p.y-2][p.x] == Maze3d.WALL) {
				neighbors.add(new Position(p.z,p.y-2,p.x));
			}
		}
		if(p.y + 2 < maze3d.getrow()-1) {
			if(maze[p.z][p.y+2][p.x] == Maze3d.WALL) {
				neighbors.add(new Position(p.z,p.y+2,p.x));
			}
		}
		if(p.z - 1 > 0) {
			if(maze[p.z-1][p.y][p.x] == Maze3d.WALL) {
				neighbors.add(new Position(p.z-1,p.y,p.x));
			}
		}
		if(p.z + 1 < maze3d.getfloor()-1) {
			if(maze[p.z+1][p.y][p.x] == Maze3d.WALL) {
				neighbors.add(new Position(p.z+1,p.y,p.x));
			}
		}
		return neighbors;
	}

	private void createPassageBetweenCells(Maze3d maze3d, Position p, Position neighbor) {
		
		if (neighbor.x == p.x - 2) { // Left neighbor 
			maze3d.setFree(new Position(p.z,p.y,p.x - 1));
			maze3d.setFree(new Position(p.z,p.y,p.x - 2));
		}
		else if (neighbor.x == p.x + 2) { // Right neighbor 
			maze3d.setFree(new Position(p.z,p.y,p.x + 1));
			maze3d.setFree(new Position(p.z,p.y,p.x + 2));
		}
		if (neighbor.z == p.z - 2) { // Left neighbor 
			maze3d.setFree(new Position(p.z-1,p.y,p.x));
			maze3d.setFree(new Position(p.z-2,p.y,p.x));
		}
		else if (neighbor.z == p.z + 2) { // Right neighbor 
			maze3d.setFree(new Position(p.z+1,p.y,p.x));
			maze3d.setFree(new Position(p.z+2,p.y,p.x ));
		}
		else if (neighbor.y == p.y - 2) { // Forward neighbor 
			maze3d.setFree(new Position(p.z,p.y - 1,p.x));
			maze3d.setFree(new Position(p.z,p.y - 2,p.x));
		}
		else if (neighbor.y == p.y + 2) { // Backward neighbor 
			maze3d.setFree(new Position(p.z,p.y + 1,p.x));
			maze3d.setFree(new Position(p.z,p.y + 2,p.x));
			
		}
	}
}
