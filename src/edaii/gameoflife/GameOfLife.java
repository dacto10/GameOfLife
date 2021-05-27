package edaii.gameoflife;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameOfLife {
	
	public static List<List<Cell>> createGrid(int rows, int columns) {
		return (List<List<Cell>>) IntStream.range(0, rows)
				.mapToObj(row -> IntStream.range(0, columns)
						.mapToObj(col -> new Cell(row, col, Math.random() > 0.5))
						.collect(Collectors.toList()))
				.collect(Collectors.toList());
	}

	public static List<List<Cell>> advance(final List<List<Cell>> grid) {
		return (List<List<Cell>>) grid.stream().map(list -> {
			return (List<Cell>) list.stream().map(cell -> {
				final int aliveNeighbours = countAliveNeighbours(grid, cell.getRow(), cell.getColumn());
				if (cell.isAlive() == 1) {
					if (aliveNeighbours < 2 || aliveNeighbours > 3) return new Cell(cell.getRow(), cell.getColumn(), false);
					else return cell;
				} else {
					if (aliveNeighbours == 3) return new Cell(cell.getRow(), cell.getColumn(), true);
					else return cell;
				}
			}).collect(Collectors.toList());
		}).collect(Collectors.toList());
	}
 
	public static int countAliveNeighbours(final List<List<Cell>> grid, final int row, final int column) {
		return recursiveCountAliveNeighbours(grid, row, column, 0, 0);
	}

	private static int recursiveCountAliveNeighbours(final List<List<Cell>> grid, final int row, final int column, final int totalChecked, final int totalAlive) {
		if (
				(row != 0 && totalChecked == 0 && grid.get(row-1).get(column).isAlive() == 1) || 
				(row != grid.size()-1 && totalChecked == 1 && grid.get(row+1).get(column).isAlive() == 1) || 
				(column != 0 && totalChecked == 2 && grid.get(row).get(column-1).isAlive() == 1) || 
				(column != grid.get(row).size()-1 && totalChecked == 3 && grid.get(row).get(column+1).isAlive() == 1) || 
				(row != 0 && column != 0 && totalChecked == 4 && grid.get(row-1).get(column-1).isAlive() == 1) || 
				(row != grid.size()-1 && column != grid.get(row).size()-1 && totalChecked == 5 && grid.get(row+1).get(column+1).isAlive() == 1) || 
				(row != 0 && column != grid.get(row).size()-1 && totalChecked == 6 && grid.get(row-1).get(column+1).isAlive() == 1) || 
				(row != grid.size()-1 && column != 0 && totalChecked == 7 && grid.get(row+1).get(column-1).isAlive() == 1)
			) return recursiveCountAliveNeighbours(grid, row, column, totalChecked + 1, totalAlive + 1);
		else if (totalChecked == 8) return totalAlive;
		else return recursiveCountAliveNeighbours(grid, row, column, totalChecked + 1, totalAlive);
	}
	
	public static List<List<Color>> computeGridColors(final List<List<Cell>> grid) {
		return (List<List<Color>>) grid.stream(
				).map(list -> (List<Color>) list.stream()
						.map(cell -> {
							final int neighbours = countAliveNeighbours(grid, cell.getRow(), cell.getColumn());
							final int numDarker = (neighbours == 8) ? 0 : (neighbours == 6 || neighbours == 7) ? 1 : (neighbours == 4 || neighbours == 5) ? 2 : (neighbours == 2 || neighbours == 3) ? 3 : (neighbours == 1 || neighbours ==0) ? 4 : -1;
							if (cell.isAlive() == 1) {
								return IntStream.range(0, numDarker).mapToObj(el -> Color.white).reduce(Color.white, (acc, el) -> {
									return acc.darker();	
								});
							} else {
								return Color.black;
							}
						})
						.collect(Collectors.toList()))
				.collect(Collectors.toList());
	}

}
