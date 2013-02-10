package dao;

import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;
import filesystem.Node;

public interface DirDao {
	public Node getRoot();
	public Node getCurrentDir();
	public void undo(Node backup);
	public void create(String name) throws OperationNotSupportedException, NodeAlreadyExistsException;
	public void setCurrentDir(Node node);
}
