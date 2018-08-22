//package co.edu.icesi.minesweeper.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.edu.icesi.minesweeper.logic.Board;
import co.edu.icesi.minesweeper.logic.Cell;


/*
 * @author: Yesid Leonardo López Sierra
 */
public class CellTest {

	/*
	 * The cell used to test methods
	 */
	private Cell cell;

	/*
	 * Inicialize the cell
	 */
	public void scenario() {
		cell = new Cell();
	}

	
	/*
	 * This method verifies that the cell content is a mine
	 */
	@Test
	public void initializeMineCell() {
		scenario();
		cell.initializeAsMine();
		assertEquals("The content should be a mine", cell.getContent(), Board.MINE_CELL);
	}

	/*
	 * This method verifies that the cell content is a disabled
	 */
	@Test
	public void initializeDisabledCell() {
		scenario();
		cell.initializeAsEmpty();
		assertEquals("The content should be a disabled cell", cell.getContent(), Board.DISABLED_CELL);
	}

	/*
	 * This method verifies that the cell is a flag
	 */
	@Test
	public void markCell() {
		scenario();
		cell.setFlag(Board.FLAG);

		assertEquals("The content should be a flag", cell.isFlag(), Board.FLAG);
		assertEquals("The content should be hide", cell.isHide(), !Board.HIDE);
	}
	
	
	/*
	 * This method verifies that the cell content is shown
	 */
	@Test
	public void unhideCell() {
		scenario();
		cell.setHide(Board.HIDE);

		assertEquals("The content should not be a flag", cell.isFlag(), !Board.FLAG);
		assertEquals("The content should not be hide", cell.isHide(), Board.HIDE);
	}
}
