package tdd.gameoflife;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GridTest {

	private Grid grid;
	private int nDimension = 10;
	private int mDimension = 10;
	
	@Before
	public void initializeGrid() {
		grid = new Grid(nDimension, mDimension);
	}
	
	@Test
	public void gridIsCreatedWithTwoDimensions() {
		assertEquals(nDimension, grid.n);
		assertEquals(mDimension, grid.m);
	}
	
	@Test
	public void defaultStateOfCellIsDead() {
		boolean result = grid.isCellAliveAt(0, 0);
		
		assertFalse("Expected cell to be dead, but was alive", result);
	}
	
	@Test
	public void livingCellCanBeSeededIntoGrid() {
		grid.addLivingCell(0, 0);
		
		boolean result = grid.isCellAliveAt(0, 0);
		
		assertTrue("Expected cell to be alive, but wasn't", result);
	}
	
	@Test
	public void multipleLivingCellsCanBeSeededIntoGrid() {
		grid.addLivingCell(0, 1);
		grid.addLivingCell(0, 2);
		
		boolean result = grid.isCellAliveAt(0, 1) && grid.isCellAliveAt(0, 2);
		
		assertTrue("Failed to seed multiple live cells into grid", result);
	}
	
	@Test
	public void newGridIsCreatedWhenNextGenerationIsCalculated() {
		Grid result = grid.nextGeneration();
		
		assertNotSame(result, grid);
		assertEquals(result.m, grid.m);
		assertEquals(result.n, grid.n);
	}
	
	@Test
	public void livingCellWithNoNeighborsDiesOnNextGeneration() {
		grid.addLivingCell(0, 0);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 0);
		assertFalse("Cell with no neighbors should have died", result);
	}
	
	@Test
	public void livingCellWithOneNeighborDiesOnNextGeneration() {
		grid.addLivingCell(0, 0);
		grid.addLivingCell(0, 1);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 1);
		assertFalse("Cell with one neighbor should have died", result);
	}
	
	@Test
	public void livingCellWithTwoNeighborsLivesOnNextGeneration() {
		grid.addLivingCell(0, 0);
		grid.addLivingCell(0, 1);
		grid.addLivingCell(1, 0);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 0);
		assertTrue("Cell with two neighbors should have lived", result);
	}
	
	@Test
	public void livingCellWithThreeNeighborsLivesOnNextGeneration() {
		grid.addLivingCell(0, 0);
		grid.addLivingCell(0, 1);
		grid.addLivingCell(1, 0);
		grid.addLivingCell(1, 1);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 0);
		assertTrue("Cell with three neighbors should have lived, but died", result);
	}
	
	@Test
	public void livingCellWithFourNeighborsDiesOnNextIteration() {
		grid.addLivingCell(0, 0);
		grid.addLivingCell(0, 1);
		grid.addLivingCell(0, 2);
		grid.addLivingCell(1, 0);
		grid.addLivingCell(1, 1);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(1, 1);
		assertFalse("Cell with four neighbors should have died, but didn't", result);
	}

}
