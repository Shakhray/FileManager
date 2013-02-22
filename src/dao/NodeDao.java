package dao;

import java.util.ArrayList;

import filesystem.Dir;
import filesystem.Node;

public interface NodeDao<PK> {
    void update(Node node);
    PK create(Node node);
    void delete(Node node);
    void setRoot(Dir root);

    public Dir getRoot();
    public void setCurrentDir(Dir currentDir);
    public Dir getCurrentDir();

	public void undo(ArrayList<Node> backup);

	public void copy(Node copynode, ArrayList<String> copyto);
	public void copy(Node copynode, Integer id);
	public void replace(Node replacenode, ArrayList<String> replaceto);
	public void replace(Node replacenode, Integer id);
}
