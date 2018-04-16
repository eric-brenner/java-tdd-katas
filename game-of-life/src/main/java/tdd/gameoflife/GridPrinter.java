package tdd.gameoflife;

public class GridPrinter {

	public void print(Grid grid) {
		boolean[][] cells = grid.getCells();
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				printCell(cells[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private void printCell(boolean isLivingCell) {
		char charToPrint = isLivingCell ? '#' : '.';
		System.out.print(charToPrint + " ");
	}
}
