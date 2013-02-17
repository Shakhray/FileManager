package command;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import filesystem.Dir;
import filesystem.File;
import filesystem.Link;

public class Delete extends Command{

	private Dir deldir;
	private File delfile;
	private Link dellink;
	
	private int KEY = 0;
	
	public Delete(DirDao dirdao, Dir currentDir, Dir deldir){
		super(dirdao, currentDir);
		this.deldir = deldir;
		KEY = 1;
	}

	public Delete(FileDao fileDao, Dir currentdir, File delfile) {
		super(fileDao, currentdir);
		this.delfile = delfile;
		KEY = 2;
	}

	public Delete(LinkDao linkDao, Dir currentdir, Link dellink) {
		super(linkDao, currentdir);
		this.dellink = dellink;
		KEY = 3;
	}

	public void execute(){
		if (KEY == 1) super.dirdao.delete(deldir);
		if (KEY == 2) super.filedao.delete(delfile);
		if (KEY == 3) super.linkdao.delete(dellink);
	}
	public void undo(){
		super.undo();
	}
}
