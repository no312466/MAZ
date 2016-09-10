package algorithms.mazeGenerators;


public interface Maze3dGenerator {
	public Maze3d generate(int floor, int row, int column);
	public String measureAlgorithmTime(int floor, int row, int column); 

}
