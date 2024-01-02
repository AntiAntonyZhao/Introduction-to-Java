

public class Pawn extends Piece {
	public Pawn(int x, int y, Side side, Board board) {
		// TODO: Call super constructor
		super(x, y, side, board);	
	}

	// @Override
	public boolean canMove(int destX, int destY) {
		//TODO: Check piecerules.md for the movement rule for this piece :)

		int blackP = (destY-this.y); // 2-1.. go down
		int whiteP = (this.y-destY); // 6-5.. go up

		boolean isB = (this.getSide() == Side.BLACK);
		boolean isW = (this.getSide() == Side.WHITE);
		boolean movY1Forward = (blackP<2 && blackP>0 && isB) || (whiteP<2 && whiteP>0 && isW); // moving Y for 1 unit forward


		boolean firstMove = false;
		if ((this.y==1 & isB) || (this.y==6 & isW)) {
			firstMove = true;
		}

		boolean diagonallyCapture = false;
		if(movY1Forward && // moving 1 forward
				(Math.abs(destX-this.x)==1) && // the right/left of the destination
				(board.get(destX, destY) != null) && // has some piece
				this.getSide() != board.get(destX, destY).getSide()){ // that piece is in opponent side
			diagonallyCapture = true; // able to capture diagonally
		}

		// main loop
		if(((blackP<=2 && blackP>0 && isB && firstMove) || (whiteP<=2 && whiteP>0 && isW && firstMove))&& // B/W 's first move forward
				destX==this.x && board.get(destX, destY) == null) { // no piece ahead
			return true; // go 1/2 steps forward, never go back, nothing ahead, first move,strictly forward
			
		}else if(movY1Forward && board.get(destX, destY) == null && destX==this.x) {
			return true; // go 1 steps forward, never go back, nothing a head, strictly forward
			
		}else if (diagonallyCapture) { // able to capture diagonally
			return true;
			
		}else {
		return false;
		}
	}

	//@Override
	public String getSymbol() {
		return this.getSide() == Side.BLACK ? "♟" : "♙";
	}
}

