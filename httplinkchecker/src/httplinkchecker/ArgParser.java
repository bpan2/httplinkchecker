package httplinkchecker;

import java.io.File;
import java.util.ArrayList;

public class ArgParser {
	private ArrayList<String> dirPaths;
	private ArrayList<String> filePaths;
	private ArrayList<String> fileNames;
	private ArrayList<String> options;
	private Message msg;

	public ArgParser() {
		this.dirPaths = null;
		this.options = null;
		this.filePaths = null;
	};

	public ArgParser(String[] args) {
		msg = Message.getInstance();

		if (args.length == 0) {
			this.msg.displyMessage("ErrMsg", 1, null, 1);
		}

		parseArgs(args);

		if (!options.isEmpty()) {
			for (String op : options) {
				switch (op) {
				case "h":
				case "help":
					this.msg.displyMessage("Instruction", 1, null, 1);
					break;
				case "v":
					this.msg.displyMessage("Instruction", 2, null, 1);
					break;
				/*
				 * default: System.out.println("Unknown Option");
				 */
				}
			}
		}
	}

	private void parseArgs(String[] args) {
		dirPaths = new ArrayList<>();
		filePaths = new ArrayList<>();
		fileNames = new ArrayList<>();
		options = new ArrayList<>();

		for (String arg : args) {
			if (arg.startsWith("-")) {
				this.options.add(arg.substring(1).toLowerCase());
			} else {
				File f = new File(arg);

				if (f.exists()) {
					if (f.isDirectory()) {
						this.dirPaths.add(arg);
					} else if (f.isFile()) {
						this.fileNames.add(arg);
					}
				}
				else {
					System.out.println("Invalid argument input: " + "\"" + arg + "\"");
				}
			}
		}

		for (String dirPath : this.dirPaths) {
			this.filePaths.addAll(retrieveAllAbsoluteFilePaths(dirPath));
		}
	}


	public ArrayList<String> retrieveAllAbsoluteFilePaths(String path) {
		File f = new File(path);
		File[] files = f.listFiles();

		ArrayList<String> filePaths = new ArrayList<>();

		for (File file : files) {
			filePaths.add(file.getAbsolutePath());
		}

		return filePaths;
	}

	public ArrayList<String> getDirPaths() {
		return new ArrayList<>(this.dirPaths);
	}

	public ArrayList<String> getFileNames() {
		return new ArrayList<>(this.fileNames);
	}
	
	public ArrayList<String> getOptions() {
		return new ArrayList<>(this.options);
	}
}

