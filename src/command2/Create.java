package command2;

import java.util.ArrayList;

import dao.NodeDao;
import filesystem.Dir;
import filesystem.Node;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 21.02.13
 * Time: 0:51
 * To change this template use File | Settings | File Templates.
 */
public class Create implements Command {

	 private NodeDao<?>  dao;
	    private Node createDir;
	    private ArrayList<Node> backup;

	    public Create(NodeDao<?> dao, Node createDir) {
	        this.dao = dao;
	        this.createDir = createDir;
	        backup = new ArrayList(((Dir)dao.getRoot()).getInsertedNode());

	    }

	    @Override
	    public void execute() {
	        dao.create(createDir);
	    }
	    @Override
		public void undo() {
			dao.undo(backup);
		}
}
