package Chess;

public class Piece extends ChessPiece {

	@Override
	public boolean capture() {
		return this.isCaptured = true;
	}

	public ChessPiece resolveAmbiguity(ChessPiece arr[], String position) {
		if (position.length() == 3) {
			for (ChessPiece r : arr) {
				if (r.position.contains(Character.toString(position.charAt(0))))
					return r;
			}
		}
		return null;
	}

	public int calcDifference(int newPos, int oldPos) {
		return newPos - oldPos;
	}

	@Override
	public ChessPiece move(ChessPiece arr[], String position) {
		return null;
	}

}
