

public class Rook extends Piece {

	public Rook(int x, int y, Side side, Board board) {
        // TODO: Call super constructor
		super(x, y, side, board);
    }

   // @Override
    public boolean canMove(int destX, int destY) {
    	if((this.x == destX || this.y == destY)){
            //TODO: Check piecerules.md for the movement rule for this piece :)
        		return true;
        	}else {
            return false;
        	}
        }

    //@Override
    public String getSymbol() {
        return this.getSide() == Side.BLACK ? "♜" : "♖";
    }
}
