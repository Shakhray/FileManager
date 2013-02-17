package dao;

import java.util.ArrayList;

import filesystem.Dir;
import filesystem.File;
import filesystem.Node;

public interface FileDao {
	public Dir getRoot();
	public void setRoot(Dir root);
	public void undo(ArrayList<Node> backup);
	public void create(File newfile);
	public void setCurrentDir(Dir node);
	public void delete(File delfile);
	public void rename(File dir, String renameto);
	public void copy(File copyfile, ArrayList<String> copyto);
	public void replace(File replacefile, ArrayList<String> replaceto);
}
