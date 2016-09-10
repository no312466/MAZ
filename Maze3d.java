package algorithms.mazeGenerators;

import java.util.Arrays;

public class Maze3d {
	
	private int [][][] maze3d;
	private int floor;
	private int row;
	private int column;
	
	private Position start;
	private Position goal;
	
	public static final int FREE = 0;
	public static final int WALL = 1;
	
	public Maze3d(int floor, int row, int column){
		this.floor = floor;
		this.row=row;
		this.column=column;
		maze3d=new int [floor][row][column];
	}
	public String [] getPossibleMoves (Position p)
	{
		String [] moves=new String [6];
		int count=0;
		if(maze3d[p.z+1][p.y][p.x]==FREE)
		{
			moves[count]="DOWN";
			count++;
		}
		if(maze3d[p.z-1][p.y][p.x]==FREE)
		{
			moves[count]="UP";
			count++;
		}
		if(maze3d[p.z][p.y+1][p.x]==FREE)
		{
			moves[count]="BACKWARD";
			count++;
		}
		if(maze3d[p.z][p.y-1][p.x]==FREE)
		{
			moves[count]="FORWARD";
			count++;
		}
		if(maze3d[p.z][p.y][p.x+1]==FREE)
		{
			moves[count]="RIGHT";
			count++;
		}
		if(maze3d[p.z][p.y][p.x-1]==FREE)
		{
			moves[count]="LEFT";
			count++;
		}
		String []posMove= Arrays.copyOf(moves, count);
		return posMove;
	}
	
	public int[][] getCrossSectionByY (int y)
	{
		int [][]temp=new int [floor][column];
		int[][][]maze3d=this.getMaze3d();
		if(y>=0&&y<row){
			for(int i=0;i<floor;i++)
				for(int j=0;j<column;j++)
					temp[i][j]=maze3d[i][y][j];
		}
		else{
			throw new IndexOutOfBoundsException();
		}
		
		return temp;
		
	}
	public int[][] getCrossSectionByX (int x)
	{
		int [][]temp=new int [floor][row];
		int[][][]maze3d=this.getMaze3d();
		if(x>=0&&x<column){
			for(int i=0;i<floor;i++)
				for(int j=0;j<row;j++)
					temp[i][j]=maze3d[i][j][x];
		}
		else{
			throw new IndexOutOfBoundsException();
		}
		
		return temp;
		
	}
	public int[][] getCrossSectionByZ (int z)
	{
		int [][]temp=new int [row][column];
		int[][][]maze3d=this.getMaze3d();
		if(z>=0&&z<floor){
			for(int i=0;i<row;i++)
				for(int j=0;j<row;j++)
					temp[i][j]=maze3d[z][i][j];
		}
		else{
			throw new IndexOutOfBoundsException();
		}
		
		return temp;
		
	}
	
	public void setFree(Position pos){
		maze3d[pos.z][pos.y][pos.x]=0 ;
	}
	public void setWall(Position pos){
		maze3d[pos.z][pos.y][pos.x]=1 ;
	}
	public int[][][] getMaze3d() {
		return maze3d;
	}
	public void setMaze3d(int[][][] maze3d) {
		this.maze3d = maze3d;
	}
	public int getfloor() {
		return floor;
	}
	public void setfloor(int floor) {
		this.floor = floor;
	}
	public int getrow() {
		return row;
	}
	public void setrow(int row) {
		this.row = row;
	}
	public int getcolumn() {
		return column;
	}
	public void setcolumn(int column) {
		this.column = column;
	}

	public Position getStart() {
		return start;
	}
	public void setStart(Position start) {
		this.start = start;
	}
	public Position getGoal() {
		return goal;
	}

	public void setGoal(Position goal) {
		this.goal = goal;
	}
	public void print()
	{
		for(int i=0;i<getfloor();i++)
		{
			for(int j=0;j<getrow();j++)
			{
				for(int k=0;k<getcolumn();k++)
				{
					if( i == start.z && j == start.y && k == start.x)
						System.out.print("E");
					else if(i == goal.z && j == goal.y && k == goal.x)
						System.out.print("X");
					else
					System.out.print(maze3d[i][j][k]);	
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
		
		
}

	