package edaii.gameoflife;

import java.util.List;

public class Program {
	public static void main(String[] args) throws InterruptedException {
		final GridWindow win = new GridWindow(1500, 1100);
		List<List<Cell>> grid = GameOfLife.createGrid(100, 80);
		while (true) {
			grid = GameOfLife.advance(grid);
			win.setGrid(GameOfLife.computeGridColors(grid));
			win.repaint();
			Thread.sleep(100);
		}
	}
}
