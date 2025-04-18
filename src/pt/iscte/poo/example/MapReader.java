package pt.iscte.poo.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.utils.Point2D;

public class MapReader {

	private static final String DIRECTORY = "rooms/";

	public static ArrayList<File> collectFiles(File dir) {
		ArrayList<File> files = new ArrayList<>();
		for (File f : dir.listFiles())
			files.add(f);
		return files;
	}

	public static void read(String r) {
		ArrayList<File> files = collectFiles(new File(DIRECTORY));
		if (Utils.check(files, f -> f.toString().contains(r + "_saved"))) {
			readObstacles(r + "_saved");
			readElements(r + "_saved");
		} else {
			readObstacles(r);
			readElements(r);
		}
	}

	public static void write(String r) {
		writeElements(r);
	}

	public static void delete(boolean onExit) {
		List<File> toDelete = (List<File>) Utils.select(collectFiles(new File(DIRECTORY)),f -> f.toString().contains("_saved"));
		toDelete.forEach(f -> {
			if (onExit)
				f.deleteOnExit();
			else
				f.delete();
		});
	}

	private static void readObstacles(String room) {
		try {
			Scanner sc = new Scanner(new File(DIRECTORY + room + ".txt"));
			String line = sc.nextLine();
			for (int y = 0; y != Engine.GRID_HEIGHT - 1; y++) {
				for (int x = 0; x != Engine.GRID_WIDTH; x++) {
					if (line.charAt(x) == '#') {
						Wall w = new Wall(new Point2D(x, y));
						Engine.getInstance().addElement("Obstacle",w);
						ImageMatrixGUI.getInstance().addImage(w);
					} else {
						Floor f = new Floor(new Point2D(x, y));
						ImageMatrixGUI.getInstance().addImage(f);
					}
				}
				line = sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void readElements(String room) {
		try {
			Scanner sc = new Scanner(new File(DIRECTORY + room + ".txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.startsWith("#") || line.startsWith(" ") || line.equals("")) {
					continue;
				} else {
					String[] info = line.split(",");
					ImageMatrixGUI.getInstance().addImage(GameElement.create(info[0], Arrays.copyOfRange(info, 1, info.length)));
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void writeElements(String room) {
		try {
			PrintWriter w = new PrintWriter(new File(DIRECTORY + room + "_saved.txt"));
			for (int y = 0; y != Engine.GRID_HEIGHT - 1; y++) {
				for (int x = 0; x != Engine.GRID_WIDTH; x++) {
					Point2D p = new Point2D(x, y);
					if (Engine.getInstance().hasObstacle(p)) {
						w.print('#');
					} else
						w.print(' ');
				}
				w.println();
			}
			w.println();
			Engine.getInstance().printLists(w);
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
