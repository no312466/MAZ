package algorithms.mazeGenerators;

public class Position {
	public int x;
	public int y;
	public int z;
	
	public Position(int z, int y, int x){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	@Override
	public String toString() {
		return "(" + z + "," + y + "," + x + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		Position pos = (Position)obj;
		return (this.z == pos.z && this.y == pos.y && this.x == pos.x);
	}

}
