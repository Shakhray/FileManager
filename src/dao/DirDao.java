package dao;

import java.util.ArrayList;

import exception.NodeAlreadyExistsException;
import exception.NodeNotFoundException;
import exception.OperationNotSupportedException;
import filesystem.Dir;
import filesystem.Node;

public interface DirDao {
	public Dir getRoot();
	public Dir getCurrentDir();
	public void setRoot(Dir root);
	public void undo(ArrayList<Node> backup);
	public void create(Dir newdir);
	public void setCurrentDir(Dir node);
	public void delete(Dir deldir);
	public void rename(Dir dir, String renameto);
	public void copy(Dir copynode, ArrayList<String> copyto);
	public void replace(Dir replacenode, ArrayList<String> replaceto);
	
}
