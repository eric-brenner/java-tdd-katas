package tdd.gameoflife;

public class GridPrinter {

	private static final char LIVE_CELL_MARKER = '#';
	private static final char DEAD_CELL_MARKER = '.';
	
	public void print(Grid grid) {
		boolean[][] cells = grid.getCells();
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				printCell(cells[i][j]);
			}
			printNewLine();
		}
		printNewLine();
	}

	private void printCell(boolean isLivingCell) {
		char cellMarker = isLivingCell ? LIVE_CELL_MARKER : DEAD_CELL_MARKER;
		System.out.print(cellMarker + " ");
	}
	
	private void printNewLine() {
		System.out.println();
	}
}
