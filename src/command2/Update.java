package command2;

import java.util.ArrayList;

import dao.NodeDao;
import filesystem.Dir;
import filesystem.Node;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 21.02.13
 * Time: 0:12
 * To change this template use File | Settings | File Templates.
 */
public class Update implements Command{

    private NodeDao<?>  dao;
    private Node updateDir;
    private ArrayList<Node> backup;

    public Update(NodeDao<?> dao, Node updateDir) {
        this.dao = dao;
        this.updateDir = updateDir;
        backup = new ArrayList(((Dir)dao.getRoot()).getInsertedNode());  
    }

    @Override
    public void execute() {
        dao.update(updateDir);
    }
    @Override
	public void undo() {
		dao.undo(backup);
	}
}
