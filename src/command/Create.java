package command;

import dao.DirDao;
import filesystem.Dir;

public class Create extends Command{

	private Dir newDir;
	
	public Create(DirDao dirdao, Dir currentDir, Dir newDir){
		super(dirdao, currentDir);
		this.newDir = newDir;
	}

	public void execute(){
		super.dirdao.create(newDir);
	}
	public void undo(){
		super.undo();
	}

}
