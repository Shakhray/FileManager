package command;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import filesystem.Dir;
import filesystem.File;

public class Copy extends Command{

	private Dir copydir;
	private File copyfile;
	private ArrayList<String> copyto = new ArrayList<String>();
	
	private int KEY = 0;
	
	public Copy(DirDao dirdao, Dir currentdir, Dir copydir, ArrayList<String> copyto){
		super(dirdao, currentdir);
		this.copydir = copydir;
		this.copyto = copyto;
		KEY = 1;
	}	
	
	public Copy(FileDao fileDao, Dir currentdir, File copyfile,	ArrayList<String> copyto) {
		super(fileDao, currentdir);
		this.copyfile = copyfile;
		this.copyto = copyto;
		KEY = 2;
	}

	public void execute(){
		if (KEY == 1) super.dirdao.copy(copydir, copyto);
		if (KEY == 2) super.filedao.copy(copyfile, copyto);
	}
	public void undo(){
		super.undo();
	}
}
