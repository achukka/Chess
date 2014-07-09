package Chess;

public class King extends Piece {

	King(String pos, int index) {
		this.position = pos;
		this.index = index;
	}

	private boolean isConstraintSatisfied(int diffX, int diffY) {
		return (diffX == 1 || diffY == 1);
	}

	private boolean isValidMove(String position) {
		char[] newPos = position.toCharArray();
		char[] oldPos = this.position.toCharArray();
		int diffX = calcDifference(newPos[0], oldPos[0]);
		int diffY = calcDifference(newPos[1], oldPos[1]);
		if (isConstraintSatisfied(diffX, diffY)) {
			return true;
		}
		return false;
	}

	public void move(String position) {
		if (this.isCaptured == false) {
			if (isValidMove(position)) {
				this.position = position;
			}
		}
	}
}
