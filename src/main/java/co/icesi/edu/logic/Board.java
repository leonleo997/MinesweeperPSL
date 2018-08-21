//package co.icesi.edu.logic;

import java.util.HashMap;
import java.util.Random;

/*
 * @author Yesid Leonardo López Sierra
 */
public class Board {

	// Content of the cells

	/*
	 * The cell is unselected. It can be uncovered or marked
	 */
	public static final char UNSELECTED_CELL = '.';
	/*
	 * The cell is disabled. It cannot be modified by the user.
	 */
	public static final char DISABLED_CELL = '-';
	/*
	 * The cell contains a mine
	 */
	public static final char MINE_CELL = '*';
	/*
	 * The cell is marked with a flag. It is used when the user marks a cell (use
	 * your imagination, it’s a flag)
	 */
	public static final boolean FLAG = true;
	/*
	 * The cell is hidden
	 */
	public static final boolean HIDE = true;

	// Player actions

	/*
	 * The cell is uncovered
	 */
	public static final char UNCOVER = 'U';

	/*
	 * The cell is uncovered
	 */
	public static final char MARK = 'M';

	/*
	 * This dictionary contains a collection of cells
	 */
	private HashMap<String, Cell> cells;

	/*
	 * The board's height
	 */
	private int height;

	/*
	 * The board's width
	 */
	private int width;
	/*
	 * The amount of mines
	 */
	private int minesAmount;

	/*
	 * The constructor fill the board
	 * 
	 * @param: heightParam, it represents the board's height.
	 * 
	 * @param: widthParam, it represents the board's width.
	 * 
	 * @param: minesAmountParam, it represents the amount of mines on the board.
	 */
	public Board(int height, int width, int minesAmount) {
		super();
		this.height = height;
		this.width = width;
		this.minesAmount = minesAmount;
		this.cells = new HashMap<String, Cell>();

		fillBoard();
	}

	/*
	 * Play on the board. The player can mark or uncover a hidden cell. If a cell
	 * contains a mine and it is uncovered, the game is over.
	 * 
	 * @param: row, it represents the rows on the board
	 * 
	 * @param: col, it represents the cols on the board
	 * 
	 * @param: action, it represents the action to do. It can mark or uncover a
	 * hidden cell.
	 * 
	 * @return: boolean that represents if the game is over.
	 */
	public boolean play(int row, int col, char action) {
		boolean isGameOver = false;
		Cell cell = cells.get(row + "," + col);
		if (action == UNCOVER) {

			switch (cell.getContent()) {
			case MINE_CELL:
				cell.setHide(!HIDE);
				cell.setFlag(!FLAG);
				isGameOver = true;
				break;

			case DISABLED_CELL:
				uncoverAdjacents(row, col);
			default:
				cell.setHide(!HIDE);
				cell.setFlag(!FLAG);
				break;
			}
		} else if (action == MARK && !cell.isFlag() && cell.isHide() == HIDE) {
			cell.setFlag(FLAG);
		}

		return isGameOver;
	}

	/*
	 * Recursive method that uncover the adjacent cells.
	 * 
	 * @Param: row, cell row to be uncovered.
	 * 
	 * @Param: col, cell col to be uncovered.
	 */
	private void uncoverAdjacents(int row, int col) {
		Cell cell = cells.get(row + "," + col);
		if (cell.getContent() > '0' && cell.getContent() < '8' && !cell.isFlag())
			cell.setHide(!HIDE);
		else {
			if (cell.getContent() == DISABLED_CELL && !cell.isFlag() && cell.isHide()) {
				cell.setHide(!HIDE);
				// uncover cells that are over and below
				if (row > 1) {
					uncoverAdjacents(row - 1, col);
					if (row < height)
						uncoverAdjacents(row + 1, col);
				} else {
					if (row < height)
						uncoverAdjacents(row + 1, col);
				}
				// uncover cells that are on the left and right
				if (col > 1) {
					uncoverAdjacents(row, col - 1);
					if (col < width)
						uncoverAdjacents(row, col + 1);
				} else {
					if (col < width)
						uncoverAdjacents(row, col + 1);
				}

				// uncover cells that are on diagonals
				if (row > 1 && col > 1)
					uncoverAdjacents(row - 1, col - 1);
				if (row < height && col < width)
					uncoverAdjacents(row + 1, col + 1);
				if (row > 1 && col < width)
					uncoverAdjacents(row - 1, col + 1);
				if (row < height && col > 1)
					uncoverAdjacents(row + 1, col - 1);
			}
		}

	}

	/*
	 * Get the board that is showed to the player
	 * 
	 * @post: it returns a matrix that contains the board representation.
	 */
	public void show() {
		for (int i = 1; i <= width; i++) {
			for (int j = 1; j <= height; j++) {
				Cell content = cells.get(j + "," + i);
				if (content.isFlag())
					System.out.print("P ");
				else {
					if (content.isHide())
						System.out.print(". ");
					else
						System.out.print(content.getContent() + " ");
				}

			}
			System.out.println();
		}
	}

	/*
	 * Fill the board cells with disabled, digit (represent the amount of adjacent
	 * mines) and mines.
	 */
	public void fillBoard() {
		fillBlankCells();
		fillMinesOnBoard();
	}

	/*
	 * Fill the board with the disabled cells that were not filled.
	 */
	private void fillBlankCells() {
		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				Cell emptyCell = new Cell();
				emptyCell.initializeAsEmpty();
				cells.put(i + "," + j, emptyCell);
			}
		}
	}

	/*
	 * Fill the board with mines in random cells.
	 */
	private void fillMinesOnBoard() {
		Random r = new Random();
		for (int i = 0; i < minesAmount; i++) {
			int randomRow = r.nextInt(height) + 1;
			int randomCol = r.nextInt(width) + 1;
			boolean continuar = true;
			do {
				if (cells.get(randomRow + "," + randomCol).getContent() == MINE_CELL) {
					randomRow = r.nextInt(height) + 1;
					randomCol = r.nextInt(width) + 1;
				} else {
					addMine(randomRow, randomCol);
					continuar = false;
				}
			} while (continuar);
		}
	}

	/*
	 * It adds a mine in a cell and increments the digit inside the adjacent cells.
	 * 
	 * @param: randomRow, it represents the cell row where the mine is.
	 * 
	 * @param: randomCol, it represents the cell col where the mine is.
	 */
	private void addMine(int randomRow, int randomCol) {
		Cell mine = new Cell();
		mine.initializeAsMine();
		cells.put(randomRow + "," + randomCol, mine);

		/*
		 * increments the amount of adjacent mines over and below the mine position
		 */
		if (randomRow > 1) {
			cells.get((randomRow - 1) + "," + randomCol).incrementAdjacentMines();
			if (randomRow < height)
				cells.get((randomRow + 1) + "," + randomCol).incrementAdjacentMines();
		} else {
			if (randomRow < height)
				cells.get((randomRow + 1) + "," + randomCol).incrementAdjacentMines();
		}

		/*
		 * increments the amount of adjacent mines on left and right of the mine position
		 */
		if (randomCol > 1) {
			cells.get(randomRow + "," + (randomCol - 1)).incrementAdjacentMines();
			if (randomCol < width)
				cells.get(randomRow + "," + (randomCol + 1)).incrementAdjacentMines();
		} else {
			if (randomCol < width)
				cells.get(randomRow + "," + (randomCol + 1)).incrementAdjacentMines();
		}

		/*
		 * increments the amount of adjacent mines on diagonals to mine position
		 */
		if (randomCol > 1 && randomRow > 1)
			cells.get((randomRow - 1) + "," + (randomCol - 1)).incrementAdjacentMines();
		if (randomCol < width && randomRow < height)
			cells.get((randomRow + 1) + "," + (randomCol + 1)).incrementAdjacentMines();
		if (randomCol > 1 && randomRow < height)
			cells.get((randomRow + 1) + "," + (randomCol - 1)).incrementAdjacentMines();
		if (randomCol < width && randomRow > 1)
			cells.get((randomRow - 1) + "," + (randomCol + 1)).incrementAdjacentMines();
	}

	public HashMap<String, Cell> getCells() {
		return cells;
	}

	public void setCells(HashMap<String, Cell> cells) {
		this.cells = cells;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getMinesAmount() {
		return minesAmount;
	}

	public void setMinesAmount(int minesAmount) {
		this.minesAmount = minesAmount;
	}

}
