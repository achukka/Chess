package Chess;

public abstract class ChessPiece {
	public String position = null;
	public boolean isCaptured = false;
	public int index = 0;

	public abstract ChessPiece move(ChessPiece arr[],String position);
	public abstract boolean capture();
	
	
}
