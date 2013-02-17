package command;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import filesystem.Dir;
import filesystem.File;
import filesystem.Link;

public class Rename extends Command{

	private String renameto;
	private Dir dir;
	private File file;
	private Link link;
	
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
	public Rename(LinkDao linkDao, Dir currentdir, Link link, String renameto) {
		super(linkDao, currentdir);
		this.renameto = renameto;
		this.link = link;
		KEY = 3;
	}
	public void execute(){
		if (KEY == 1) super.dirdao.rename(dir, renameto);
		if (KEY == 2) super.filedao.rename(file, renameto);
		if (KEY == 3) super.linkdao.rename(link, renameto);
	}
	public void undo(){
		super.undo();
	}

}
