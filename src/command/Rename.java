package command;

import dao.DirDao;
import dao.FileDao;
import filesystem.Dir;
import filesystem.File;

public class Rename extends Command{

	private String renameto;
	private Dir dir;
	private File file;
	
	private int KEY = 0;
	
	public Rename(DirDao dirdao, Dir currentDir, Dir dir, String renameto){
		super(dirdao, currentDir);
		this.renameto = renameto;
		this.dir = dir;
		KEY = 1;
	}
	
	public Rename(FileDao fileDao, Dir currentdir, File file, String renameto) {
		super(fileDao, currentdir);
		this.renameto = renameto;
		this.file = file;
		KEY = 2;
	}

	public void execute(){
		if (KEY == 1) super.dirdao.rename(dir, renameto);
		if (KEY == 2) super.filedao.rename(file, renameto);
	}
	public void undo(){
		super.undo();
	}

}
