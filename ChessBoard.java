package Chess;

public class ChessBoard {
	public String[] squares;

	ChessBoard() {
		squares = new String[65];
		int index;
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				index = col * 8 + row;
				squares[index] = Character.toString((char) (row + 65))
						+ Integer.toString(col + 1);
			}
		}
	}

	public String display() {
		String output = "";
		for (int row = 0; row < 64; row++) {
			output += squares[row] + "\n";

		}
		return output;
	}

	public static void main(String[] args) {
		ChessBoard board = new ChessBoard();
		board.display();
	}
}