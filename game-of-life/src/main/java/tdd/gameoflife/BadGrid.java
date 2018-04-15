package tdd.gameoflife;

import java.util.Arrays;

public class BadGrid {

	public final int n;
	public final int m;
	
	private final char LIVE_CELL_MARKER = 'O';
	private final char DEAD_CELL_MARKER = '.';
	
	private char[][] cellPositions;
	
	public BadGrid(int n, int m) {
		this.n = n;
		this.m = m;
		
		this.cellPositions = new char[n][m];
	}

	public void addLivingCell(int n, int m) {
		this.cellPositions[n][m] = LIVE_CELL_MARKER;
	}

	public boolean livingCellExistsAt(int n, int m) {
		return LIVE_CELL_MARKER == this.cellPositions[n][m];
	}

	public void nextGeneration() {
		
	}

	private void keepCellsAlive() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				killOrKeepCell(i, j);
			}
		}
	}

	private void killOrKeepCell(int x, int y) {
		int numberOfLivingNeighbors = countNumberOfLivngNeighbors(x, y);
		if (numberOfLivingNeighbors < 2) {
			killCell(x, y);
		}
	}

	private void killCell(int x, int y) {
		this.cellPositions[x][y] = DEAD_CELL_MARKER;
	}
	
	private int countNumberOfLivngNeighbors(int x, int y) {
		int count = countLeftNeighbors(x, y);
		count += countNorthAndSouthNeighbors(x, y);
		return count += countRightNeighbors(x, y);
	}

	private int countNorthAndSouthNeighbors(int x, int y) {
		int count = countNeighbor(x, y-1);
		return count += countNeighbor(x, y+1);
	}

	private int countRightNeighbors(int x, int y) {
		int count = countNeighbor(x+1, y-1);
		count += countNeighbor(x+1, y);
		return count += countNeighbor(x+1, y+1);
	}

	private int countLeftNeighbors(int x, int y) {
		int count = countNeighbor(x-1, y-1);
		count += countNeighbor(x-1, y);
		return count += countNeighbor(x-1, y+1);
	}
	
	private int countNeighbor(int x, int y) {
		return livingCellExistsAt(x, y) ? 1 : 0;
	}
}
