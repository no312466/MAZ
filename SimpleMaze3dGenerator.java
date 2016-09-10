package algorithms.mazeGenerators;

import java.util.Random;


public class SimpleMaze3dGenerator extends Maze3dGeneratorBase {
	
	private Random rand = new Random();
	//private static final float WALL_RATIO=0.5F;
	
	private Maze3d maze3d;
	
	public Maze3d generate(int floor, int row, int column)
	{
		Maze3d maze3d = new Maze3d (floor,row,column);
		initalize(maze3d);
		maze3d.setStart(getRandomPosition(maze3d));
		maze3d.setGoal(setSimpleMaze(maze3d));
		
		return maze3d;	
	}

	private Position setSimpleMaze(Maze3d maze3d)
	{
		int [][][]temp=maze3d.getMaze3d();
		Position p=maze3d.getStart();
		int i;
		for(i=p.z;i<maze3d.getfloor();i++){
			temp[i][p.y][p.x+1]=0;
		}
		return new Position (i-1,p.y,p.x+1);
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
	
	private Position getRandomPosition(Maze3d maze3d){ 
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
	}
}