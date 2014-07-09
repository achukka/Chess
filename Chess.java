package Chess;

import java.io.*;
import java.util.*;

public class Chess {
	private ChessBoard board;
	private ChessPiece blackPiece[];
	private ChessPiece whitePiece[];
	private ChessPiece blackPawn[];
	private ChessPiece whitePawn[];

	Chess() {
		board = new ChessBoard();
		initializePawn();
		initializePiece();
	}

	public void start(String s) {
		processInput(s);
	}

	public void initializePiece() {
		blackPiece = new ChessPiece[8];
		whitePiece = new ChessPiece[8];
		placeBlackPiece();
		placeWhitePiece();
	}

	public void initializePawn() {
		blackPawn = new ChessPiece[8];
		whitePawn = new ChessPiece[8];
		placeBlackPawn();
		placeWhitePawn();
	}

	public void placeBlackPiece() {
		blackPiece[0] = new Rook("A8", 0);
		blackPiece[1] = new Knight("B8", 1);
		blackPiece[2] = new Bishop("C8", 2);
		blackPiece[3] = new Queen("D8", 3);
		blackPiece[4] = new King("E8", 4);
		blackPiece[5] = new Bishop("F8", 5);
		blackPiece[6] = new Knight("G8", 6);
		blackPiece[7] = new Rook("H8", 7);
	}

	public void placeWhitePiece() {
		whitePiece[0] = new Rook("A1", 0);
		whitePiece[1] = new Knight("B1", 1);
		whitePiece[2] = new Bishop("C1", 2);
		whitePiece[3] = new Queen("D1", 3);
		whitePiece[4] = new King("E1", 4);
		whitePiece[5] = new Bishop("F1", 5);
		whitePiece[6] = new Knight("G1", 6);
		whitePiece[7] = new Rook("H1", 7);
	}

	public void placeBlackPawn() {
		int i = 48;
		for (int j = 0; j < 8; j++) {
			blackPawn[j] = new Pawn(board.squares[i++], j);
		}
	}

	public void placeWhitePawn() {
		int i = 8;
		for (int j = 0; j < 8; j++) {
			whitePawn[j] = new Pawn(board.squares[i++], j);
		}
	}

	ChessPiece[] getRooks(ChessPiece[] c) {
		ChessPiece Pieces[] = new ChessPiece[2];
		Pieces[0] = c[0];
		Pieces[1] = c[7];
		return Pieces;
	}

	void moveRook(ChessPiece c[], String pos) {
		ChessPiece pieces[] = new ChessPiece[2];
		pieces = getRooks(c);
		Rook obj = new Rook(null, 0);
		obj.move(pieces, pos);
		c[obj.index] = obj;
	}

	ChessPiece[] getKnights(ChessPiece[] c) {
		ChessPiece Pieces[] = new ChessPiece[2];
		Pieces[0] = c[1];
		Pieces[1] = c[6];
		return Pieces;
	}

	void moveKnight(ChessPiece c[], String pos) {
		ChessPiece pieces[] = new ChessPiece[2];
		pieces = getKnights(c);
		Knight obj = new Knight(null, 0);
		obj.move(pieces, pos);
		c[obj.index] = obj;
	}

	ChessPiece[] getBishops(ChessPiece[] c) {
		ChessPiece Pieces[] = new ChessPiece[2];
		Pieces[0] = c[2];
		Pieces[1] = c[5];
		return Pieces;
	}

	void moveBishop(ChessPiece c[], String pos) {
		ChessPiece pieces[] = new ChessPiece[2];
		pieces = getBishops(c);
		Bishop obj = new Bishop(null, 0);
		obj.move(pieces, pos);
		c[obj.index] = obj;
	}

	void moveQueen(ChessPiece c[], String pos) {
		Queen q = (Queen) c[3];
		q.move(pos);
	}

	void moveKing(ChessPiece c[], String pos) {
		King k = (King) c[3];
		k.move(pos);
	}

	void movePawn(ChessPiece c[], String pos) {
		Pawn obj = new Pawn(null, 0);
		obj.move(c, pos);
		c[obj.index] = obj;
	}

	void whiteMove(char ch, String pos) {
		if (ch == 'R')
			moveRook(whitePiece, pos);
		else if (ch == 'N')
			moveKnight(whitePiece, pos);
		else if (ch == 'B')
			moveKnight(whitePiece, pos);
		else if (ch == 'Q')
			moveQueen(whitePiece, pos);
		else if (ch == 'K')
			moveKing(whitePiece, pos);
		else
			movePawn(whitePawn, pos);
	}

	void blackMove(char ch, String pos) {
		if (ch == 'R')
			moveRook(blackPiece, pos);
		else if (ch == 'N')
			moveKnight(blackPiece, pos);
		else if (ch == 'B')
			moveKnight(blackPiece, pos);
		else if (ch == 'Q')
			moveQueen(blackPiece, pos);
		else if (ch == 'K')
			moveKing(blackPiece, pos);
		else
			movePawn(blackPawn, pos);
	}

	void capturePieces(boolean capture, String pos, ChessPiece pieces[],
			ChessPiece pawns[]) {
		for (int i = 0; i < 8; i++) {
			if (pieces[i].position.equals(pos))
				pieces[i].isCaptured = capture;
			if (pawns[i].position.equals(pos))
				pawns[i].isCaptured = capture;
		}
	}

	void moveTo(int i, char ch, boolean capture, String pos) {
		if (i == 0) {
			whiteMove(ch, pos);
			capturePieces(capture, pos, blackPiece, blackPawn);
		} else {
			blackMove(ch, pos);
			capturePieces(capture, pos, whitePiece, whitePawn);
		}
	}

	public void makeMove(String move, int color) {
		char piece = 0;
		String moveToPos = null;
		boolean capture = false;
		;

		for (int i = 0; i < move.length(); i++) {
			char c = move.charAt(i);
			if (Character.isUpperCase(c)) {
				// R,N,B,Q,K
				piece = c;
			} else if (c == 'x') {
				capture = true;
			} else if (Character.isLowerCase(c)) {
				// move pawn
				char c1 = move.charAt(++i);
				StringBuilder sb = new StringBuilder();
				sb.append(c);
				sb.append(c1);
				moveToPos = sb.toString();
			}

		}

		moveTo(color, piece, capture, moveToPos);

	}

	public void processCurrMove(String currMove) {
		String[] moves = currMove.split(" ");
		String white = moves[0];
		String black = moves[1];

		makeMove(white, 0);
		makeMove(black, 1);
	}

	public void processInput(String fileName) {
		File input = new File(fileName);
		try {
			Scanner scanner = new Scanner(input);
			while (scanner.hasNextLine()) {
				String moves = scanner.nextLine();
				if (!moves.isEmpty()) {
					processCurrMove(moves);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
