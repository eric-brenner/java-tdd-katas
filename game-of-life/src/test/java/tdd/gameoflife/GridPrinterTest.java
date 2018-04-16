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
		boolean[][] cells = {{true}, {}};
		when(grid.getCells()).thenReturn(cells);
		
		printer.print(grid);
		
		boolean result = sysOutContent.toString().startsWith("#");
		assertTrue("Failed to print representation for living cell", result);
	}
	
	@Test
	public void printsPeriodToRepresentDeadCell() {
		boolean[][] cells = {{false}, {}};
		when(grid.getCells()).thenReturn(cells);
		
		printer.print(grid);
		
		boolean result = sysOutContent.toString().startsWith(".");
		assertTrue("Failed to print representation for dead cell", result);
	}

}
