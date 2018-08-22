package co.edu.icesi.minesweeper.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import co.edu.icesi.minesweeper.logic.Board;
import co.edu.icesi.minesweeper.logic.Cell;
import co.edu.icesi.minesweeper.logic.Minesweeper;

/*
 * @author: Yesid Leonardo López Sierra
 */
class MinesweeperTest {

	/*
	 * class used to test class methods
	 */
	private Minesweeper minesweeper;

	/*
	 * the first scenario has five rows, eight columns and one mine
	 */
	public void firstScenario() {
		minesweeper = new Minesweeper();
		minesweeper.loadGame(5, 8, 1);
	}

	/*
	 * the first scenario has six rows, three columns and nine mines
	 */
	public void secondScenario() {
		minesweeper = new Minesweeper();
		minesweeper.loadGame(6, 3, 9);
	}

	/*
	 * the first scenario has eight rows, fifty columns and one hundred twenty mines
	 */
	public void thirdScenario() {
		minesweeper = new Minesweeper();
		minesweeper.loadGame(8, 15, 120);
	}

	/*
	 * For the first scenario, verify that the cells that contains mines are the
	 * same cells that are marked.
	 */
	@Test
	public void verifyVictoryFirstScenario() {
		firstScenario();

		int width = minesweeper.getBoard().getWidth();
		int height = minesweeper.getBoard().getHeight();
		HashMap<String, Cell> cells = minesweeper.getBoard().getCells();

		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				if (cells.get(j + "," + i).getContent() == Board.MINE_CELL)
					minesweeper.play(j, i, Board.MARK);
			}
		}

		assertTrue(minesweeper.isVictory(), "It should be a victory");
	}

	/*
	 * For the second scenario, verify that the cells that contains mines are the
	 * same cells that are marked.
	 */
	@Test
	public void verifyVictorySecondScenario() {
		secondScenario();

		int width = minesweeper.getBoard().getWidth();
		int height = minesweeper.getBoard().getHeight();
		HashMap<String, Cell> cells = minesweeper.getBoard().getCells();

		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				if (cells.get(j + "," + i).getContent() == Board.MINE_CELL)
					minesweeper.play(j, i, Board.MARK);
			}
		}

		assertTrue(minesweeper.isVictory(), "It should be a victory");
	}

	/*
	 * For the third scenario, verify that the cells that contains mines are the
	 * same cells that are marked.
	 */
	@Test
	public void verifyVictoryThirdScenario() {
		thirdScenario();

		int width = minesweeper.getBoard().getWidth();
		int height = minesweeper.getBoard().getHeight();
		HashMap<String, Cell> cells = minesweeper.getBoard().getCells();

		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				if (cells.get(j + "," + i).getContent() == Board.MINE_CELL)
					minesweeper.play(j, i, Board.MARK);
			}
		}

		assertTrue(minesweeper.isVictory(), "It should be a victory");
	}

	/*
	 * For the first scenario, verify that there is no victory because despite of
	 * the number of mines is equals to the number of marked cells, the marked cells
	 * have to be the same that the mined cells.
	 */
	@Test
	public void verifyNoVictoryFirstScenario() {
		firstScenario();

		int width = minesweeper.getBoard().getWidth();
		int height = minesweeper.getBoard().getHeight();

		int count = 0;
		int minesAmount = minesweeper.getBoard().getMinesAmount();
		boolean stop = false;
		for (int i = 1; i <= width && !stop; i++) {
			for (int j = 1; j <= height && !stop; j++) {
				count++;
				minesweeper.play(j, i, Board.MARK);
				if (count == minesAmount)
					stop = true;
			}
		}

		assertFalse(minesweeper.isVictory(), "It should not be a victory");
	}

	/*
	 * For the second scenario, verify that there is no victory because despite of
	 * the number of mines is equals to the number of marked cells, the marked cells
	 * have to be the same that the mined cells.
	 */
	@Test
	public void verifyNoVictorySecondScenario() {
		secondScenario();

		int width = minesweeper.getBoard().getWidth();
		int height = minesweeper.getBoard().getHeight();

		int count = 0;
		int minesAmount = minesweeper.getBoard().getMinesAmount();
		boolean stop = false;
		for (int i = 1; i <= width && !stop; i++) {
			for (int j = 1; j <= height && !stop; j++) {
				count++;
				minesweeper.play(j, i, Board.MARK);
				if (count == minesAmount)
					stop = true;
			}
		}

		assertFalse(minesweeper.isVictory(), "It should not be a victory");
	}

	/*
	 * For the third scenario, verify that there is no victory because despite of
	 * the number of mines is equals to the number of marked cells, the marked cells
	 * have to be the same that the mined cells.
	 * 
	 * However, the third escenario is full of mines. It means that will be victory
	 * always.
	 */
	@Test
	public void verifyNoVictoryThirdScenario() {
		thirdScenario();

		int width = minesweeper.getBoard().getWidth();
		int height = minesweeper.getBoard().getHeight();

		int count = 0;
		int minesAmount = minesweeper.getBoard().getMinesAmount();
		boolean stop = false;
		for (int i = 1; i <= width && !stop; i++) {
			for (int j = 1; j <= height && !stop; j++) {
				count++;
				minesweeper.play(j, i, Board.MARK);
				if (count == minesAmount)
					stop = true;
			}
		}

		assertTrue(minesweeper.isVictory(), "It should not be a victory");
	}

}
