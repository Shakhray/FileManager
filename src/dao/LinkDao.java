package dao;

import java.util.ArrayList;

import filesystem.Dir;
import filesystem.Link;
import filesystem.Node;

public interface LinkDao {
	public Dir getRoot();
	public void setRoot(Dir root);
	public void undo(ArrayList<Node> backup);
	public void create(Link newlink);
	public void setCurrentDir(Dir node);
	public void delete(Link dellink);
	public void rename(Link dir, String renameto);
	public void copy(Link copylink, ArrayList<String> copyto);
	public void replace(Link replacelink, ArrayList<String> replaceto);
}
