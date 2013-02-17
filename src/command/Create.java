package command;

import dao.DirDao;
import dao.FileDao;
import filesystem.Dir;
import filesystem.File;

public class Create extends Command{

	private Dir newDir;
	private File newfile;
	
	private int KEY = 0;
	
	public Create(DirDao dirdao, Dir currentDir, Dir newDir){
		super(dirdao, currentDir);
		this.newDir = newDir;
		KEY = 1;
	}

	public Create(FileDao filedao, Dir currentDir, File newfile){
		super(filedao, currentDir);
		this.newfile = newfile;
		KEY = 2;
	}
	
	public void execute(){
		if (KEY == 1) super.dirdao.create(newDir);
		if (KEY == 2) super.filedao.create(newfile);
	}
	public void undo(){
		super.undo();
	}

}
