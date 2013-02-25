package command2;

import dao.NodeDao;
import filesystem.Dir;
import filesystem.Node;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: alex
 * Date: 20.02.13
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class Copy implements Command {

    private NodeDao<?>  dao;
    private Node copyDir;
    private ArrayList<Integer> copyTo;
    private ArrayList<Node> backup;

    public Copy(NodeDao<?> dao, Node copyDir, ArrayList<Integer> copyTo) {
        this.dao = dao;
        this.copyDir = copyDir;
        this.copyTo = copyTo;
        backup = new ArrayList(((Dir)dao.getRoot()).getInsertedNode());
    }

    @Override
    public void execute() {
        dao.copy(copyDir, copyTo);
    }

	@Override
	public void undo() {
		dao.undo(backup);
	}
}
