package command;

import dao.DirDao;
import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;
import filesystem.Node;

public class Create extends Command{

	private String newDir;
	public Create(DirDao dirdao, String newDir) {
		super(dirdao);
		this.newDir = newDir;
	}

	public void execute() throws OperationNotSupportedException, NodeAlreadyExistsException {
		super.dirdao.create(newDir);
	}

}
