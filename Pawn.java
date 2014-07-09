package Chess;

public class Pawn extends ChessPiece {

	String position;
	int index;
	boolean isCaptured;

	public Pawn(String pos, int n) {
		this.position = pos;
		this.index = n;
	}

	private ChessPiece getChesssPiece(ChessPiece arr[], String position) {
		if (position.length() == 2) {
			for (ChessPiece r : arr) {
				if (r.position.contains(Character.toString(position.charAt(0))))
					return r;
			}
		}
		return null;
	}

	private boolean isConstraintSatisfied(int diffX, int diffY) {
		return (diffY == 1 || (diffX == 1 && diffY == 1) || diffY == 2);
	}

	public int calcDifference(int newPos, int oldPos) {
		return newPos - oldPos;
	}

	private boolean isValidMove(String position, ChessPiece pawn) {
		char[] newPos = position.toCharArray();
		char[] oldPos = pawn.position.toCharArray();
		int diffX = calcDifference(newPos[0], oldPos[0]);
		int diffY = calcDifference(newPos[1], oldPos[1]);
		if (isConstraintSatisfied(diffX, diffY)) {
			return true;
		}
		return false;
	}

	@Override
	public Pawn move(ChessPiece arr[], String position) {
		ChessPiece chessPiece = getChesssPiece(arr, position);
		if (chessPiece != null) {
			chessPiece.position = position;

			return (Pawn) chessPiece;
		} else {
			for (ChessPiece b : arr) {
				if (this.isCaptured == false) {
					for (int i = 0; i < 8; i++) {
						if (isValidMove(position, b)) {
							b.position = position;
							return (Pawn) b;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean capture() {
		// TODO Auto-generated method stub
		return false;
	}

}
