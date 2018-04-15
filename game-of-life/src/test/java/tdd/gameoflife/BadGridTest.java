package tdd.gameoflife;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BadGridTest {

	private BadGrid grid;
	
	private final int nDimension = 20;
	private final int mDimension = 20;
	
	@Before
	public void initializeGrid() {
		grid = new BadGrid(nDimension, mDimension);
	}
	
	@Test
	public void gridIsCreatedWithTwoDimensions() {
		assertEquals(nDimension, grid.n);
		assertEquals(mDimension, grid.m);
	}
	
	@Test
	public void liveCellCanBeSeededIntoGrid() {
		addLivingCellToGrid(0, 1);
		
		boolean result = grid.livingCellExistsAt(0, 1);
		
		assertTrue("Expected live cell, but didn't find one", result);
	}

	@Test
	public void defaultStateOfCellInGridIsDead() {
		boolean result = livingCellExistsAt(0, 0);
		
		assertFalse("Expected dead cell but found live", result);
	}
	
	@Test
	public void multipleLivingCellsCanBeSeededIntoGrid() {
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(10, 10);
		
		boolean result = livingCellExistsAt(0, 1) && livingCellExistsAt(10, 10);
		
		assertTrue("Failure seeding grid with multiple live cells", result);
	}
	
	@Test
	public void livingCellWithNoNeighborsDiesOnNextGeneration() {
		addLivingCellToGrid(0, 0);

		createNextGeneration();
		
		boolean result = livingCellExistsAt(0, 0);
		assertFalse("Expected cell to be dead, but wasn't", result);
	}
	
	@Test
	public void livingCellWithOneNeighborDiesOnNextGeneration() {
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(0, 2);
		
		createNextGeneration();
		
		boolean result = livingCellExistsAt(0, 1);
		assertFalse("Expected cell to be dead, but wasn't", result);
	}

	@Test
	public void livingCellWithTwoNeighborsLivesInTheNextGeneration() {
		addLivingCellToGrid(0, 0);
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(1, 0);
		
		createNextGeneration();
		
		boolean result = livingCellExistsAt(0, 0);
		assertTrue("Expected cell to live, but died", result);
	}
	

	private void addLivingCellToGrid(int n, int m) {
		grid.addLivingCell(n, m);
	}
	
	private boolean livingCellExistsAt(int n, int m) {
		return grid.livingCellExistsAt(n, m);
	}

	private void createNextGeneration() {
		grid.nextGeneration();
	}
}
