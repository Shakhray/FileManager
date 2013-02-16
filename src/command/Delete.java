package command;

import dao.DirDao;
import filesystem.Dir;

public class Delete extends Command{

	private Dir deldir;
	
	public Delete(DirDao dirdao, Dir currentDir, Dir deldir){
		super(dirdao, currentDir);
		this.deldir = deldir;
	}

	public void execute(){
		super.dirdao.delete(deldir);
	}
	public void undo(){
		super.undo();
	}
}
