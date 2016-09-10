package algorithms.mazeGenerators;

public abstract class Maze3dGeneratorBase implements Maze3dGenerator {
	
	public String measureAlgorithmTime(int floor, int row, int column){
		long time= System.currentTimeMillis ();
		this.generate(floor, row, column);
		time=System.currentTimeMillis()- time;
		return String.valueOf(time);
	}
	
}
