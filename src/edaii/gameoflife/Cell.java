package edaii.gameoflife;

public class Cell {
	private final int row;
	private final int column;
	private final boolean alive;
	
	public Cell(final int row, final int column, final boolean alive) {
		this.row = row;
		this.column = column;
		this.alive = alive;
	}
	public int getRow() {
		return this.row;
	}
	public int getColumn() {
		return this.column;
	}
	public int isAlive() {
		return this.alive ? 1 : 0;
	}
	public boolean equals(final Object obj) {
		try {
			final Cell cell = (Cell) obj;
			return this.row == cell.row && this.column == cell.column && this.alive == cell.alive;
		} catch (ClassCastException ex) {
			throw ex;
		}
	}
	public String toString() {
		return "Cell -> row: " + this.row + ", col: " + this.column + ", " + (this.alive ? "alive" : "dead");
	}
}
