 package es.ucm.fdi.tp.practica5.view;

import java.awt.Color;

import es.ucm.fdi.tp.basecode.bgame.control.Controller;
import es.ucm.fdi.tp.basecode.bgame.control.Player;
import es.ucm.fdi.tp.basecode.bgame.model.GameObserver;
import es.ucm.fdi.tp.basecode.bgame.model.Observable;
import es.ucm.fdi.tp.basecode.bgame.model.Piece;
import es.ucm.fdi.tp.practica5.view.board.RectBoardComponent;


public abstract class RectBoardSwingView extends SwingView{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private FiniteRectBoardComponent boardComponent;
	//private BoardComponent boardComponent;
	
	public RectBoardSwingView(Observable<GameObserver> game, Controller c, Piece localPiece, Player randPlayer, Player aiPlayer) {
		super(game, c, localPiece, randPlayer, aiPlayer);
	}

	@SuppressWarnings("serial")
	@Override
	protected void initBoardGui(Controller ctrl, Observable<GameObserver> game) {
		
		//this.boardComponent = new FiniteRectBoardComponent(ctrl, game, this.getPieceColors(), this.getBoard());
		boardComponent = new RectBoardComponent(game, board){

			
	protected void mouseClicked(int row, int col, int clickcounter, int mouseButton){
		handelMouseClick(row, col, clickcounter, mouseButton);
	}

	
	@Override
	protected Color getPieceColor (Piece piece){
		return RectBoardSwingView.this.getPieceColor(piece);
	}

	@Override
	protected Tipes getPieceTipe(Piece piece) {
		Tipes PieceTipe = null;
		if(getPieces().contains(piece))
			PieceTipe = Tipes.PIECE;
		else if(piece != null && piece.getId().equals("+"))
			PieceTipe = Tipes.OBSTACLE;
		else
			PieceTipe = Tipes.CELL;

		return PieceTipe;
	}
	
	};
	setBoardArea(boardComponent);
	}
		
	@Override
	protected void activateBoard() {
		this.inPlay = true;
		this.inMove = false;
	}

	@Override
	protected void deActivateBoard() {	
		this.inPlay = false;
		this.inMove = true;
	}

	@Override
	protected void redrawBoard() {
		this.boardComponent.repaint();
	}
		
	
	protected abstract void handelMouseClick(int row, int col, int clickcounter, int mouseButton);
}
