# Percolation Simulation Project

This Java project simulates percolation using a union-find data structure to model physical phenomena such as material porosity or network connectivity. This simulation is particularly useful for studies in computational science and engineering fields.

## Getting Started

These instructions will help you to set up and run the Percolation simulation project in Visual Studio Code (VS Code).

### Prerequisites

- **Java Development Kit (JDK)**: JDK 11 or higher is recommended.
- **Visual Studio Code**: Ensure VS Code is installed on your machine.
- **Java Extension Pack**: This can be installed directly from the Visual Studio Code marketplace.

### Installation

1. **Clone or download the project**:
   - If using Git, clone the project using:
     ```
     git clone https://example.com/your-repository-url.git
     ```
   - Alternatively, download the ZIP file and extract it.

2. **Open the project in VS Code**:
   - Launch VS Code, go to `File > Open Folder...` and select the project directory.

3. **Install the Java Extension Pack**:
   - Open the Extensions view by clicking the square icon on the sidebar or pressing `Ctrl+Shift+X`.
   - Search for "Extension Pack for Java" and click Install.

## Folder Structure

The project follows a typical Java project structure used in VS Code:

- `src`: Contains all the Java source files.
  - `Percolation.java`: Manages the percolation model.
  - `PercolationStats.java`: Calculates statistics on the percolation data.
  - `PercolationVisualizer.java`: Visualizes the percolation process.
  - `PercolationSimulation.java`: Runs the simulation using the models and visualizer.
- `lib`: Stores the project dependencies, notably the `algs4.jar` for the union-find structure.
- `bin`: Default directory for compiled output files.

> **Note**: You can customize this structure in `.vscode/settings.json`.

## Dependency Management

Dependencies are managed through the `JAVA PROJECTS` view in VS Code, where you can easily add or update project libraries.

### Adding a Dependency

- To add a library, right-click on the `Java Projects` section, choose `Add Library`, and navigate to the `.jar` files you wish to include.

## Running the Application

To run the application:

1. **Open the `src` directory** in the VS Code explorer.
2. **Right-click** on `PercolationStats.java`, `PercolationVisualizer.java`, or `PercolationSimulation.java`.
3. Choose **Run Java** from the context menu to execute the program.

## Versioning

Version control is handled using Git. For the available versions, see the [tags on this repository](https://example.com/your-repository-url/tags).

## Authors

- **Your Name** - *Initial development*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- Thanks to the Princeton University Algorithms course for providing the inspiration and resources necessary for this project.
- Appreciation to all contributors who participate in project maintenance and enhancements.
