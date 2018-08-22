//package co.icesi.edu.logic;

/*
 * @author Yesid Leonardo López Sierra
 */
public class Cell {

	/*
	 * the content can be disabled, a number that represents the adjacent mines or
	 * if it is a mine.
	 */
	private char content;

	/*
	 * if the mine is uncovered and unmarked
	 */
	private boolean hide;

	/*
	 * if the mine is marked
	 */
	private boolean flag;

	/*
	 * Default constructor
	 */
	public Cell() {

	}


	/* 
	 * When a cell is created the content is disabled, it is hidden and 
	 * the flag is not marked
	 */
	public void initializeAsEmpty() {
		content = Board.DISABLED_CELL;
		hide = Board.HIDE;
		flag = !Board.FLAG;
	}
	
	/* 
	 * When a cell is created the content is a mine, it is hidden and 
	 * the flag is not marked
	 */
	public void initializeAsMine() {
		content = Board.MINE_CELL;
		hide = Board.HIDE;
		flag = !Board.FLAG;
	}

	/*
	 * Increments the amount of adjacent mines
	 */
	public void incrementAdjacentMines() {
		if(content!='*') {
			if(content=='-')
				content='1';
			else
				content++;
		}
	}
	
	public char getContent() {
		return content;
	}


	public void setContent(char content) {
		this.content = content;
	}


	public boolean isHide() {
		return hide;
	}


	public void setHide(boolean hide) {
		this.hide = hide;
	}


	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
}
