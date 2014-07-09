package Chess;

public class Rook extends Piece {
	Rook(String pos, int index) {
		this.position = pos;
		this.index = index;
	}

	private boolean isConstraintSatisfied(int diffX, int diffY) {
		return (diffX == 0 || diffY == 0);
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

	public Rook move(ChessPiece arr[], String position) {
		ChessPiece pieceToMove = resolveAmbiguity(arr, position);
		if (pieceToMove != null) {
			pieceToMove.position = position;
			return (Rook) pieceToMove;
		} else {
			for (ChessPiece b : arr) {
				if (this.isCaptured == false) {
					if (isValidMove(position, b)) {
						b.position = position;
						return (Rook) b;
					}
				}
			}
		}
		return null;
	}
}
