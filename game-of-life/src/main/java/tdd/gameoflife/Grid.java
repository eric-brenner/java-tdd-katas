package tdd.gameoflife;

public class Grid {

	public final int n;
	public final int m;
	private boolean[][] cells;

	public Grid(int n, int m) {
		this.n = n;
		this.m = m;
		this.cells = new boolean[n][m];
	}

	public boolean isCellAliveAt(int x, int y) {
		if (outOfBounds(x, y)) {
			return false;
		}
		return this.cells[x][y];
	}

	public void addLivingCell(int x, int y) {
		this.cells[x][y] = true;
	}

	public Grid nextGeneration() {
		Grid nextGeneration = new Grid(n, m);
		calculateNextCellStates(nextGeneration);
		return nextGeneration;
	}

	private void calculateNextCellStates(Grid nextGeneration) {
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				calculateNextCellState(nextGeneration, x, y);
			}
		}
	}

	private void calculateNextCellState(Grid nextGeneration, int x, int y) {
		int numberOfNeighbors = countNumberOfNeighbors(x, y);
		if (numberOfNeighbors == 2 || numberOfNeighbors == 3) {
			nextGeneration.addLivingCell(x, y);
		}
	}

	private int countNumberOfNeighbors(int x, int y) {
		int numberOfNeighbors = countLeftNeighbors(x, y);
		numberOfNeighbors += countRightNeighbors(x, y);
		numberOfNeighbors += countNorthAndSouthNeighbors(x, y);
		return numberOfNeighbors;
	}

	private int countLeftNeighbors(int x, int y) {
		int count = countNeighbor(x-1, y-1);
		count += countNeighbor(x-1, y);
		return count += countNeighbor(x-1, y+1);
	}
	
	private int countRightNeighbors(int x, int y) {
		int count = countNeighbor(x+1, y-1);
		count += countNeighbor(x+1, y);
		return count += countNeighbor(x+1, y+1);
	}
	
	private int countNorthAndSouthNeighbors(int x, int y) {
		int count = countNeighbor(x, y+1);
		return count += countNeighbor(x, y-1);
	}
	
	private boolean outOfBounds(int x, int y) {
		return (x < 0 || x > n-1 || y < 0 || y > m-1);
	}
	
	private int countNeighbor(int x, int y) {
		return isCellAliveAt(x, y) ? 1 : 0;
	}
}
