package co.edu.icesi.minesweeper.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;

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
	 * Load a board that has eight rows, fifteen cols and ten mines located randomly
	 * on the board. It is a normal case.
	 */
	public void firstScenario() {
		board = new Board(8, 15, 10);
	}

	/*
	 * Load a board that has one row, fifteen cols and one mine located randomly on
	 * the board. It is when there is just one mine on the board.
	 */
	public void secondScenario() {
		board = new Board(1, 15, 1);
	}

	/*
	 * Load a board that has five rows, four cols and twenty mines on the board. It
	 * is when the board is full of mines.
	 */
	public void thirdScenario() {
		board = new Board(5, 4, 20);
	}

	/*
	 * verify how if the amount of mines selected is equals to the amount of mines
	 * on the board using the three scenarios.
	 */
	@Test
	public void testMinesAmount() {
		firstScenario();
		verifyMinesAmount();

		secondScenario();
		verifyMinesAmount();

		thirdScenario();
		verifyMinesAmount();

	}

	/*
	 * In the first scenario, search a mine and uncover it. When the mine is
	 * uncovered, the game is over.
	 */
	@Test
	public void uncoverMineFirstScenario() {
		firstScenario();
		HashMap<String, Cell> cells = board.getCells();
		int width = board.getWidth();
		int height = board.getHeight();

		boolean stop = false;
		for (int i = 1; i <= width && !stop; i++) {
			for (int j = 1; j <= height && !stop; j++) {
				if (cells.get(j + "," + i).getContent() == Board.MINE_CELL)
					if (board.play(j, i, Board.UNCOVER))
						stop = true;
			}
		}

		assertTrue("The game should be over", stop);
	}

	/*
	 * In every scenario, we mark the first cell then the flag and hide
	 * attribute must be true.
	 */
	@Test
	public void markCell() {
		firstScenario();
		board.play(1, 1, Board.MARK);

		Cell cell = board.getCells().get("1,1");

		assertTrue("The cell should have the flag", cell.isFlag());
		assertTrue("The cell should be hidden", cell.isHide());

		secondScenario();
		board.play(1, 1, Board.MARK);

		cell = board.getCells().get("1,1");

		assertTrue("The cell should have the flag", cell.isFlag());
		assertTrue("The cell should be hidden", cell.isHide());

		thirdScenario();
		board.play(1, 1, Board.MARK);

		cell = board.getCells().get("1,1");

		assertTrue("The cell should have the flag", cell.isFlag());
		assertTrue("The cell should be hidden", cell.isHide());

	}

	/*
	 * In the second scenario, search a mine and uncover it. When the mine is
	 * uncovered, the game is over.
	 */
	@Test
	public void uncoverMineSecondScenario() {
		firstScenario();
		HashMap<String, Cell> cells = board.getCells();
		int width = board.getWidth();
		int height = board.getHeight();

		boolean stop = false;
		for (int i = 1; i <= width && !stop; i++) {
			for (int j = 1; j <= height && !stop; j++) {
				if (cells.get(j + "," + i).getContent() == Board.MINE_CELL)
					if (board.play(j, i, Board.UNCOVER))
						stop = true;
			}
		}

		assertTrue("The game should be over", stop);
	}

	/*
	 * In the first scenario, search a mine and uncover it. When the mine is
	 * uncovered, the game is over.
	 */
	@Test
	public void uncoverMineThirdScenario() {
		thirdScenario();
		HashMap<String, Cell> cells = board.getCells();
		int width = board.getWidth();
		int height = board.getHeight();

		boolean stop = false;
		for (int i = 1; i <= width && !stop; i++) {
			for (int j = 1; j <= height && !stop; j++) {
				if (cells.get(j + "," + i).getContent() == Board.MINE_CELL)
					if (board.play(j, i, Board.UNCOVER))
						stop = true;
			}
		}

		assertTrue("The game should be over", stop);
	}

	/*
	 * verify how if the amount of mines selected is equals to the amount of mines
	 * on the board
	 */
	public void verifyMinesAmount() {
		board.fillBoard();
		Collection<Cell> cellsContent = board.getCells().values();
		int minesAmonut = 0;
		for (Cell cell : cellsContent) {
			if (cell.getContent() == Board.MINE_CELL)
				minesAmonut++;
		}
		assertEquals("There should be " + board.getMinesAmount() + " mines", board.getMinesAmount(), minesAmonut);
	}

	/*
	 * Verify if the amount of adjacent mines for cells is equals to the number of
	 * the board correctly calculated for the first Scenario.
	 */
	@Test
	public void verifyAdjacencyFirstScenario() {
		firstScenario();
		board.fillBoard();
		HashMap<String, Cell> adjacents = calculateAdjacency();

		int width = board.getWidth();
		int height = board.getHeight();
		HashMap<String, Cell> cells = board.getCells();

		boolean fail = false;
		for (int i = 1; i <= width && !fail; i++) {
			for (int j = 1; j <= height && !fail; j++) {
				if (cells.get(j + "," + i).getContent() != Board.MINE_CELL
						&& cells.get(j + "," + i).getContent() != Board.DISABLED_CELL
						&& cells.get(j + "," + i).getContent() != adjacents.get(j + "," + i).getContent())
					fail = true;
			}
		}

		assertFalse("The amount of adjacent is different in one cell for the first scenario", fail);
	}

	/*
	 * Verify if the amount of adjacent mines for cells is equals to the number of
	 * the board correctly calculated for the Second Scenario.
	 */
	@Test
	public void verifyAdjacencySecondScenario() {
		secondScenario();
		board.fillBoard();
		HashMap<String, Cell> adjacents = calculateAdjacency();

		int width = board.getWidth();
		int height = board.getHeight();
		HashMap<String, Cell> cells = board.getCells();

		boolean fail = false;
		for (int i = 1; i <= width && !fail; i++) {
			for (int j = 1; j <= height && !fail; j++) {
				if (cells.get(j + "," + i).getContent() != Board.MINE_CELL
						&& cells.get(j + "," + i).getContent() != Board.DISABLED_CELL
						&& cells.get(j + "," + i).getContent() != adjacents.get(j + "," + i).getContent())
					fail = true;
			}
		}

		assertFalse("The amount of adjacent is different in one cell for the first scenario", fail);

	}

	/*
	 * Verify if the amount of adjacent mines for cells is equals to the number of
	 * the board correctly calculated for the third Scenario.
	 */
	@Test
	public void verifyAdjacencyThirdScenario() {
		thirdScenario();
		board.fillBoard();
		HashMap<String, Cell> adjacents = calculateAdjacency();

		int width = board.getWidth();
		int height = board.getHeight();
		HashMap<String, Cell> cells = board.getCells();

		boolean fail = false;
		for (int i = 1; i <= width && !fail; i++) {
			for (int j = 1; j <= height && !fail; j++) {
				if (cells.get(j + "," + i).getContent() != Board.MINE_CELL
						&& cells.get(j + "," + i).getContent() != Board.DISABLED_CELL
						&& cells.get(j + "," + i).getContent() != adjacents.get(j + "," + i).getContent())
					fail = true;
			}
		}

		assertFalse("The amount of adjacent is different in one cell for the first scenario", fail);

	}

	/*
	 * Count how many mines are adjacent to a cell
	 * 
	 * @param HashMap that contains in each cell the adjacent value.
	 */
	private HashMap<String, Cell> calculateAdjacency() {
		HashMap<String, Cell> adjacents = new HashMap<String, Cell>();
		HashMap<String, Cell> cells = board.getCells();
		int width = board.getWidth();
		int height = board.getHeight();

		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				Cell cell = new Cell();
				cell.initializeAsEmpty();
				cell.setContent('0');
				adjacents.put((j + "," + i), cell);
			}
		}

		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				if (cells.get(j + "," + i).getContent() == Board.MINE_CELL) {
					/*
					 * increments the amount of adjacent mines over and below the mine position
					 */
					if (j > 1) {
						adjacents.get((j - 1) + "," + i).incrementAdjacentMines();
						if (j < height)
							adjacents.get((j + 1) + "," + i).incrementAdjacentMines();
					} else {
						if (j < height)
							adjacents.get((j + 1) + "," + i).incrementAdjacentMines();
					}

					/*
					 * increments the amount of adjacent mines on left and right of the mine
					 * position
					 */
					if (i > 1) {
						adjacents.get(j + "," + (i - 1)).incrementAdjacentMines();
						if (i < width)
							adjacents.get(j + "," + (i + 1)).incrementAdjacentMines();
					} else {
						if (i < width)
							adjacents.get(j + "," + (i + 1)).incrementAdjacentMines();
					}

					/*
					 * increments the amount of adjacent mines on diagonals to mine position
					 */
					if (i > 1 && j > 1)
						adjacents.get((j - 1) + "," + (i - 1)).incrementAdjacentMines();
					if (i < width && j < height)
						adjacents.get((j + 1) + "," + (i + 1)).incrementAdjacentMines();
					if (i > 1 && j < height)
						adjacents.get((j + 1) + "," + (i - 1)).incrementAdjacentMines();
					if (i < width && j > 1)
						adjacents.get((j - 1) + "," + (i + 1)).incrementAdjacentMines();

				}
			}
		}

		return adjacents;
	}

}
