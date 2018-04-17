package tdd.gameoflife;

import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GridPrinterTest {

	private final boolean LIVING_CELL_INDICATOR = true;
	private final boolean DEAD_CELL_INDICATOR = false;
	private final String LIVING_CELL_MARKER = "#";
	private final String DEAD_CELL_MARKER = ".";
	
	private ByteArrayOutputStream sysOutContent = new ByteArrayOutputStream();
	
	@InjectMocks
	private GridPrinter printer;
	
	@Mock private Grid grid;
	
	@Before
	public void captureContentWrittenToSystemOut() {
		PrintStream printStream = new PrintStream(sysOutContent);
		System.setOut(printStream);
	}
	
	@Test
	public void printsHashMarkToRepresentLivingCell() {
		boolean[][] cells = {{LIVING_CELL_INDICATOR}};
		when(grid.getCells()).thenReturn(cells);
		
		printGrid();
		
		boolean result = sysOutContent.toString().startsWith(LIVING_CELL_MARKER);
		assertTrue("Failed to print representation for living cell", result);
	}
	
	@Test
	public void printsPeriodToRepresentDeadCell() {
		boolean[][] cells = {{DEAD_CELL_INDICATOR}};
		when(grid.getCells()).thenReturn(cells);
		
		printGrid();
		
		boolean result = sysOutContent.toString().startsWith(DEAD_CELL_MARKER);
		assertTrue("Failed to print representation for dead cell", result);
	}
	
	@Test
	public void printedCellsAreSeparatedBySingleSpace() {
		boolean[][] cells = {{DEAD_CELL_INDICATOR, DEAD_CELL_INDICATOR}};
		when(grid.getCells()).thenReturn(cells);
		
		printGrid();
		
		String result = sysOutContent.toString();
		assertTrue(result.startsWith(". ."));
	}
	
	@Test
	public void printedRowEndsWithLineSeparator( ) {
		boolean[][] cells = {{DEAD_CELL_INDICATOR, DEAD_CELL_INDICATOR}};
		when(grid.getCells()).thenReturn(cells);
		
		printGrid();
		
		String result = sysOutContent.toString();
		assertTrue(result.endsWith(System.getProperty("line.separator")));
	}
	
	@Test 
	public void twoRowsAreSeparatedByLineSeparator() {
		boolean[][] cells = {{DEAD_CELL_INDICATOR, DEAD_CELL_INDICATOR}, {DEAD_CELL_INDICATOR, DEAD_CELL_INDICATOR}};
		when(grid.getCells()).thenReturn(cells);
		
		printGrid();
		
		String result = sysOutContent.toString();
		String expectedResult = System.getProperty("line.separator") + System.getProperty("line.separator");
		assertTrue(result.endsWith(expectedResult));
	}
	
	private void printGrid() {
		printer.print(grid);
	}
}
