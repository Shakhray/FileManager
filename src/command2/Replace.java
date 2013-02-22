package command2;

import java.util.ArrayList;

import dao.NodeDao;
import filesystem.Dir;
import filesystem.Node;

public class Replace implements Command {

    private NodeDao<?>  dao;
    private Node replaceDir;
    private ArrayList<String> replaceTo;
    private ArrayList<Node> backup;

    public Replace(NodeDao<?> dao, Node replaceDir, ArrayList<String> replaceTo) {
        this.dao = dao;
        this.replaceDir = replaceDir;
        this.replaceTo = replaceTo;
        backup = new ArrayList(((Dir)dao.getRoot()).getInsertedNode());

    }

    @Override
    public void execute() {
        dao.replace(replaceDir, replaceTo);
    }
    @Override
	public void undo() {
		dao.undo(backup);
	}
}
