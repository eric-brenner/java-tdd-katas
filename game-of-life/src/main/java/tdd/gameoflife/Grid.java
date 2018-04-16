package tdd.gameoflife;

public class Grid {

	public final int width;
	public final int height;
	private boolean[][] cells;
	
	private Grid nextGeneration;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.cells = new boolean[width][height];
	}

	public boolean isCellAliveAt(int x, int y) {
		if (outOfBounds(x, y)) {
			return false;
		}
		return this.cells[x][y];
	}
	
	private boolean isCellDeadAt(int x, int y) {
		return this.cells[x][y] == false;
	}

	public void addLivingCell(int x, int y) {
		this.cells[x][y] = true;
	}

	public Grid nextGeneration() {
		this.nextGeneration = new Grid(width, height);
		calculateNextCellStates();
		return nextGeneration;
	}

	private void calculateNextCellStates() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				calculateNextCellState(x, y);
			}
		}
	}

	private void calculateNextCellState(int x, int y) {
		int numberOfNeighbors = countNumberOfNeighbors(x, y);
		if (isCellDeadAt(x, y)) {
			calculateNextStateForDeadCell(x, y, numberOfNeighbors);
		}
		else if (numberOfNeighbors == 2 || numberOfNeighbors == 3) {
			nextGeneration.addLivingCell(x, y);
		}
	}

	private void calculateNextStateForDeadCell(int x, int y, int numberOfNeighbors) {
		if (numberOfNeighbors == 3) {
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
		return (x < 0 || x > width-1 || y < 0 || y > height-1);
	}
	
	private int countNeighbor(int x, int y) {
		return isCellAliveAt(x, y) ? 1 : 0;
	}
	
	public boolean[][] getCells() {
		return cells;
	}
}
