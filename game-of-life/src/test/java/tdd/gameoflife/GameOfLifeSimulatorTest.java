package tdd.gameoflife;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameOfLifeSimulatorTest {

	@InjectMocks
	private GameOfLifeSimulator simulator;
	
	@Mock private Grid grid;
	@Mock private GridPrinter gridPrinter;
	
	@Test
	public void runsOneGeneration() {
		simulator.run(1);
		
		verify(grid).nextGeneration();
	}
	
	@Test
	public void printsOneGeneration() {
		simulator.run(1);
		
		verify(gridPrinter).print(grid);
	}
	
	@Test
	public void runsMulitpleGenerations() {
		int numberOfTimesToRun = 10;
		when(grid.nextGeneration()).thenReturn(grid);
		
		simulator.run(numberOfTimesToRun);
		
		verify(grid, times(numberOfTimesToRun)).nextGeneration();
	}
	
	@Test
	public void printsMultipleGenerations() {
		when(grid.nextGeneration()).thenReturn(grid);
		int numberOfTimesToRun = 10;
		
		simulator.run(numberOfTimesToRun);
		
		verify(gridPrinter, times(numberOfTimesToRun)).print(grid);
	}
}
