package Chess;

public class Bishop extends Piece {
	Bishop(String pos, int index) {
		this.position = pos;
		this.index = index;
	}

	private boolean isConstraintSatisfied(int diffX, int diffY) {
		return (Math.abs(diffX) == Math.abs(diffY));
	}

	private boolean isValidMove(String position, ChessPiece rook) {
		char[] newPos = position.toCharArray();
		char[] oldPos = rook.position.toCharArray();
		int diffX = calcDifference(newPos[0], oldPos[0]);
		int diffY = calcDifference(newPos[1], oldPos[1]);
		if (isConstraintSatisfied(diffX, diffY)) {
			return true;
		}
		return false;
	}

	public Bishop move(ChessPiece arr[], String position) {
		ChessPiece pieceToMove = resolveAmbiguity(arr, position);
		if (pieceToMove != null) {
			pieceToMove.position = position;
			return (Bishop) pieceToMove;
		} else {
			for (ChessPiece b : arr) {
				if (this.isCaptured == false) {
					if (isValidMove(position, b)) {
						b.position = position;
						return (Bishop) b;
					}
				}
			}
		}
		return null;
	}

}
