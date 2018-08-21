//package co.icesi.edu.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

/*
 * @author Yesid Leonardo López Sierra
 */
public class Minesweeper {

	/*
	 * Integer that contains the current round.
	 */
	private int round;

	/*
	 * Current board.
	 */
	private Board board;

	/*
	 * Constructor of the game
	 */
	public Minesweeper(int height, int width, int minesAmount) {
		board = new Board(height, width, minesAmount);
		round=1;
		board.show();
	}

	/*
	 * Verify if the game ends. It ends if the amount of marked mines is equal
	 * to the amount of mines on the board.
	 * 
	 * @return boolean that represents if the player won the game.
	 */
	public boolean isVictory() {
		boolean victory = false;
		int markedMines = 0;
		int markedCells = 0;
		Collection<Cell> mines = board.getCells().values();
		for (Cell cell : mines) {
			if (cell.getContent() == Board.MINE_CELL && cell.isFlag())
				markedMines++;
			if (cell.isFlag())
				markedCells++;
		}

		if (markedMines == markedCells && markedCells == board.getMinesAmount()) {
			victory = true;
			System.out.println("Congratulations, You Won!");
		}

		return victory;
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
		isGameOver = board.play(row, col, action);
		if (isGameOver)
			System.out.println("Ops, you found a mine :c");
		board.show();
		return isGameOver;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int height = 0, width = 0, minesAmount = 0;
		try {
			System.out.println("Type the board height, width and amount of mines");
			String cad = bf.readLine();
			String[] split = cad.split(" ");
			height = Integer.parseInt(split[0]);
			width = Integer.parseInt(split[1]);
			minesAmount = Integer.parseInt(split[2]);

			Minesweeper ms = new Minesweeper(height, width, minesAmount);

			// to delete
			// Set<String> keys = ms.getBoard().getCells().keySet();
			// char[][] matriz = new char[width][height];
			// for (String key : keys) {
			// int row = Integer.parseInt(key.split(",")[0]);
			// int col = Integer.parseInt(key.split(",")[1]);
			// matriz[col - 1][row - 1] = ms.getBoard().getCells().get(key).getContent();
			// }
			//
			// for (int i = 0; i < width; i++) {
			// for (int j = 0; j < height; j++) {
			// System.out.print(matriz[i][j] + " ");
			// }
			// System.out.println();
			// }

			// delete
			while (!ms.isVictory()) {
				System.out.println("Type the row, col and the action");
				cad = bf.readLine();
				split = cad.split(" "); 
				int row = Integer.parseInt(split[0]);
				int col = Integer.parseInt(split[1]);
				char action = split[2].charAt(0);

				boolean isGameOver = ms.play(row, col, action);
				if (isGameOver)
					break;
			}
		} catch (Exception e) {
			System.out.println("Incorrect value typed");
			e.printStackTrace();
		}

	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
