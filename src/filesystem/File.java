package filesystem;

import java.util.ArrayList;

public class File extends Node{

	private double size = 0;
	//private File file;
	
	public File(String fileName, double size, ArrayList<String> path){
		super(fileName, path);
		this.size = size;
	}
	
	public File(String fileName, ArrayList<String> path){
		super(fileName, path);
	}

	public void setName(String fileName){
		super.setName(fileName);
	}
	public double size() {
		return size;
	}

	public boolean isDir() {
		return false;
	}

	public boolean isFile() {
		return true;
	}

	public boolean isLink() {
		return false;
	}
}
