package Chess;

public class Knight extends Piece {

	int[] rowDiff = { 2, 2, -2, -2, 1, 1, -1, -1 };
	int[] colDiff = { 1, -1, 1, -1, 2, -2, 2, -2 };

	Knight(String pos, int index) {
		this.position = pos;
		this.index = index;
	}

	private boolean isConstraintSatisfied(int diffX, int diffY, int index) {
		return (diffX == rowDiff[index] && diffY == colDiff[index]);
	}

	private boolean isValidMove(String position, ChessPiece knight, int i) {
		char[] newPos = position.toCharArray();
		char[] oldPos = knight.position.toCharArray();
		int diffX = calcDifference(newPos[0], oldPos[0]);
		int diffY = calcDifference(newPos[1], oldPos[1]);
		
		if (isConstraintSatisfied(diffX, diffY, i)) {
			return true;
		}
		return false;
	}

	public Knight move(ChessPiece arr[], String position) {
		ChessPiece pieceToMove = resolveAmbiguity(arr, position);
		if (pieceToMove != null) {
			pieceToMove.position = position;
			return (Knight) pieceToMove;
		} else {
			for (ChessPiece b : arr) {
				if (this.isCaptured == false) {
					for (int i = 0; i < 8; i++) {
						if (isValidMove(position, b, i)) {
							b.position = position;
							return (Knight) b;
						}
					}
				}
			}
			return null;
		}
	}
}
