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
		assertEquals(nDimension, grid.width);
		assertEquals(mDimension, grid.height);
	}
	
	@Test
	public void defaultStateOfCellIsDead() {
		grid = new Grid(1, 1);
		
		boolean result = grid.isCellAliveAt(0, 0);
		
		assertFalse("Expected cell to be dead, but was alive", result);
	}
	
	@Test
	public void livingCellCanBeSeededIntoGrid() {
		addLivingCellToGrid(0, 0);
		
		boolean result = grid.isCellAliveAt(0, 0);
		
		assertTrue("Expected cell to be alive, but wasn't", result);
	}
	
	@Test
	public void multipleLivingCellsCanBeSeededIntoGrid() {
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(0, 2);
		
		boolean result = grid.isCellAliveAt(0, 1) && grid.isCellAliveAt(0, 2);
		
		assertTrue("Failed to seed multiple live cells into grid", result);
	}
	
	@Test
	public void newGridIsCreatedWhenNextGenerationIsCalculated() {
		Grid result = grid.nextGeneration();
		
		assertNotSame(result, grid);
		assertEquals(result.height, grid.height);
		assertEquals(result.width, grid.width);
	}
	
	@Test
	public void livingCellWithNoNeighborsDiesOnNextGeneration() {
		addLivingCellToGrid(4, 4);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(4, 4);
		assertFalse("Cell with no neighbors should have died", result);
	}
	
	@Test
	public void livingCellWithOneNeighborDiesOnNextGeneration() {
		addLivingCellToGrid(0, 0);
		addLivingCellToGrid(0, 1);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 1);
		assertFalse("Cell with one neighbor should have died", result);
	}
	
	@Test
	public void livingCellWithTwoNeighborsLivesOnNextGeneration() {
		addLivingCellToGrid(0, 0);
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(1, 0);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 0);
		assertTrue("Cell with two neighbors should have lived", result);
	}
	
	@Test
	public void livingCellWithThreeNeighborsLivesInNextGeneration() {
		addLivingCellToGrid(0, 0);
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(1, 0);
		addLivingCellToGrid(1, 1);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 0);
		assertTrue("Cell with three neighbors should have lived, but died", result);
	}
	
	@Test
	public void livingCellWithFourNeighborsDiesInNextGeneration() {
		addLivingCellToGrid(0, 0);
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(0, 2);
		addLivingCellToGrid(1, 0);
		addLivingCellToGrid(1, 1);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(1, 1);
		assertFalse("Cell with four neighbors should have died, but didn't", result);
	}
	
	@Test
	public void deadCellWithOneNeighborRemainsDeadInNextGeneration() {
		addLivingCellToGrid(0, 1);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 0);
		assertFalse("Dead cell should have remained dead", result);
	}
	
	@Test
	public void deadCellWithTwoNeighborsRemainsDeadInNextGeneration() {
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(1, 0);
		
		Grid nextGenerationGrid = grid.nextGeneration();
		
		boolean result = nextGenerationGrid.isCellAliveAt(0, 0);
		assertFalse("Dead cell should have remained dead", result);
	}
	
	@Test
	public void deadCellWIthThreeNeighborsBecomesLiveCellInNextGeneration() {
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(1, 0);
		addLivingCellToGrid(1, 1);
		
		Grid nextGeneration = grid.nextGeneration();
		
		boolean result = nextGeneration.isCellAliveAt(0, 0);
		assertTrue("Dead cell should have become alive", result);
	}
	
	@Test
	public void deadCellWithMoreTHanThreeNeighborsRemainsDeadInNextGeneration() {
		addLivingCellToGrid(0, 0);
		addLivingCellToGrid(0, 1);
		addLivingCellToGrid(0, 2);
		addLivingCellToGrid(1, 0);
		addLivingCellToGrid(1, 1);
		
		Grid nextGeneration = grid.nextGeneration();
		
		boolean result = nextGeneration.isCellAliveAt(1, 1);
		assertFalse("Dead cell should have remained dead", result);
	}
	
	private void addLivingCellToGrid(int x, int y) {
		grid.addLivingCell(x, y);
	}
}
