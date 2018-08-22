package co.edu.icesi.minesweeper.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import co.edu.icesi.minesweeper.logic.Board;
import co.edu.icesi.minesweeper.logic.Cell;

/*
 * @author: Yesid Leonardo López Sierra
 */
public class BoardTest {
	
	/*
	 * Board
	 */
	private Board board;
	
	/*
	 * Load a board that has eight rows, fifteen cols and ten mines
	 * located randomly on the board
	 */
	public void firstScenario() {
		board = new Board(8, 15, 10);
	}
	
	/*
	 * Load a board that has one row, fifteen cols and one mine
	 * located randomly on the board
	 */
	public void secondScenario() {
		board = new Board(1, 15, 1);
	}
	
	/*
	 * Load a board that has five rows, four cols and twenty mines
	 * on the board
	 */
	public void thirdScenario() {
		board = new Board(5, 4, 20);
	}
	
	
	@Test
	public void testMinesAmount() {
		firstScenario();
		verifyMinesAmount();
		
		secondScenario();
		verifyMinesAmount();
		
		thirdScenario();
		verifyMinesAmount();
	}
	
	public void verifyMinesAmount() {
		board.fillBoard();
		Collection<Cell> cellsContent = board.getCells().values();
		int minesAmonut=0;
		for (Cell cell : cellsContent) {
			if(cell.getContent()==Board.MINE_CELL)
				minesAmonut++;
		}
		System.out.println(minesAmonut);
		assertEquals("There should be "+board.getMinesAmount()+" mines", board.getMinesAmount(), minesAmonut);
	}
	
}
