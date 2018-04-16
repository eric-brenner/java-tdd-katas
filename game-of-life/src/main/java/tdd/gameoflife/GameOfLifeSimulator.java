package tdd.gameoflife;

public class GameOfLifeSimulator {

	private Grid grid;
	private GridPrinter gridPrinter;

	public GameOfLifeSimulator(Grid grid, GridPrinter gridPrinter) {
		this.grid = grid;
		this.gridPrinter = gridPrinter;
	}

	public void run(int numberOfGenerations) {
		Grid currentGrid = this.grid;
		for (int i = 0; i < numberOfGenerations; i++) {
			gridPrinter.print(currentGrid);
			currentGrid = currentGrid.nextGeneration();
		}
	}
	
	public static void main(String[] args) {
		Grid grid = new Grid(10, 10);
		grid.addLivingCell(0, 0);
		grid.addLivingCell(0, 1);
		grid.addLivingCell(0, 2);
		grid.addLivingCell(1, 0);
		grid.addLivingCell(1, 1);
		grid.addLivingCell(2, 2);
		grid.addLivingCell(3, 3);
		
		GameOfLifeSimulator simulator = new GameOfLifeSimulator(grid, new GridPrinter());
		simulator.run(6);
	}
}
