package command;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import filesystem.Dir;
import filesystem.File;
import filesystem.Link;

public class Copy extends Command{

	private Dir copydir;
	private File copyfile;
	private Link copylink;
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

	public Copy(LinkDao linkDao, Dir currentdir, Link copylink,	ArrayList<String> copyto) {
		super(linkDao, currentdir);
		this.copylink = copylink;
		this.copyto = copyto;
		KEY = 3;
	}

	public void execute(){
		if (KEY == 1) super.dirdao.copy(copydir, copyto);
		if (KEY == 2) super.filedao.copy(copyfile, copyto);
		if (KEY == 3) super.linkdao.copy(copylink, copyto);
	}
	public void undo(){
		super.undo();
	}
}
