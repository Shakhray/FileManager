package command;

import dao.DirDao;
import filesystem.Dir;

public class Rename extends Command{

	private String renameto;
	private Dir dir;
	
	public Rename(DirDao dirdao, Dir currentDir, Dir dir, String renameto){
		super(dirdao, currentDir);
		this.renameto = renameto;
		this.dir = dir;
	}
	
	public void execute(){
		super.dirdao.rename(dir, renameto);
	}
	public void undo(){
		super.undo();
	}

}
