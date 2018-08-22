//package co.edu.icesi.minesweeper.logic;

import java.io.BufferedReader;
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
	public Minesweeper() {

	}

	public static void main(String[] args) {
		Minesweeper game = new Minesweeper();
		game.startGame();
	}

	/*
	 * The most important method. This start the entire game and verifies after
	 * every movement if the player wins or lose the game.
	 */
	public void startGame() {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("WELCOME TO THE GAME: MINESWEEPER\n"
					+ "First, type the board height, width and amount of mines. Each number separed by a blank space (For example:8 15 10)");
			String cad = bf.readLine();
			
			catchData(cad);
			
			boolean keepPlaying = true;
			while (keepPlaying) {
				boolean victory = false;
				boolean gameOver = false;
				while (!victory) {
					System.out.println("\nType the row, col and the action. Each value separed by a blank space (For example:1 1 U)");
					cad = bf.readLine();
					String[] split = cad.split(" ");
					int row = Integer.parseInt(split[0]);
					int col = Integer.parseInt(split[1]);
					char action = split[2].charAt(0);

					boolean isGameOver = play(row, col, action);
					if (isGameOver) {
						restart();
						gameOver = true;
						break;
					}
					if (isVictory()) {
						victory = true;
						nextRound();
					}
				}
				System.out.println("¿Do you want to keep playing to the level " + getRound() + "? (y/n)\n");
				String answer = bf.readLine();
				if (answer.toUpperCase().equals("N")) {
					System.out.println("I hope you'll have a beautiful day");
					keepPlaying = false;
				} else {
					if (answer.toUpperCase().equals("Y") && gameOver)
						loadGame(board.getHeight(), board.getWidth(), board.getMinesAmount());
				}

			}
		} catch (Exception e) {
			System.out.println("The game has been stoped");
		}

	}

	/*
	 * Catch the data typed by the user. This data is the width, height and
	 * the amount of mines of the board.
	 * @param: cad, String that contains the width, height and the amount 
	 * of mines
	 */
	private void catchData(String cad) throws Exception {
		int height = 0, width = 0, minesAmount = 0;
		
		String[] split = cad.split(" ");
		try {
			height = Integer.parseInt(split[0]);
			width = Integer.parseInt(split[1]);
			minesAmount = Integer.parseInt(split[2]);

			if (minesAmount > height * width)
				throw new Exception("There are more mines than cells on the board");

			loadGame(height, width, minesAmount);

		} catch (NumberFormatException e) {
			System.out.println("You have to type numbers separed by blank spaces");
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			throw new Exception();
		}
	}

	/*
	 * set the width and heigh and the amount of mines
	 * 
	 * @param: heightParam, it represents the board's height.
	 * 
	 * @param: widthParam, it represents the board's width.
	 * 
	 * @param: minesAmountParam, it represents the amount of mines on the board.
	 */
	public void loadGame(int height, int width, int minesAmount) {
		board = new Board(height, width, minesAmount);
		restart();
		System.out.println("ROUND #" + round);
		board.show();
	}

	/*
	 * Verify if the game ends. It ends if the amount of marked mines is equal to
	 * the amount of mines on the board.
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
	 * @param: col, it represents the columns on the board
	 * 
	 * @param: action, it represents the action to do. It can mark or uncover a
	 * hidden cell.
	 * 
	 * @return: boolean that represents if the game is over.
	 */
	public boolean play(int row, int col, char action) {
		boolean isGameOver = false;
		isGameOver = board.play(row, col, action);
		board.show();
		if (isGameOver)
			System.out.println("Ops, you found a mine :c");
		return isGameOver;
	}

	/*
	 * Increments the number of the round and creates a new game with the same
	 * dimensions
	 */
	public void nextRound() {
		board = new Board(board.getHeight(), board.getWidth(), board.getMinesAmount());
		System.out.print("\n-----This is the new Game-----");
		board.show();
		System.out.println();
		round++;
	}

	/*
	 * Restart the round to one
	 */
	public void restart() {
		round = 1;
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
