package command2;

import java.util.ArrayList;

import dao.NodeDao;
import filesystem.Dir;
import filesystem.Node;

public class Delete implements Command{

	 private NodeDao<?>  dao;
	    private Node deleteDir;
	    private ArrayList<Node> backup;

	    public Delete(NodeDao<?> dao, Node deleteDir) {
	        this.dao = dao;
	        this.deleteDir = deleteDir;
	        backup = new ArrayList(((Dir)dao.getRoot()).getInsertedNode());

	    }

	    @Override
	    public void execute() {
	        dao.delete(deleteDir);
	    }
	    @Override
		public void undo() {
			dao.undo(backup);
		}
}
