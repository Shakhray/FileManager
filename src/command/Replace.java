package command;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;
import filesystem.Dir;
import filesystem.File;
import filesystem.Link;

public class Replace extends Command{

	private Dir replacedir;
	private File replacefile;
	private Link replacelink;
	private ArrayList<String> replaceto = new ArrayList<String>();
	
	private int KEY = 0;
	
	public Replace(DirDao dirdao, Dir currentdir, Dir replacedir, ArrayList<String> replaceto){
		super(dirdao, currentdir);
		this.replacedir = replacedir;
		this.replaceto = replaceto;
		KEY = 1;
	}

	public Replace(FileDao fileDao, Dir currentdir, File replacefile, ArrayList<String> replaceto) {
		super(fileDao, currentdir);
		this.replacefile = replacefile;
		this.replaceto = replaceto;
		KEY = 2;
	}

	public Replace(LinkDao linkDao, Dir currentdir, Link replacelink,	ArrayList<String> replaceto) {
		super(linkDao, currentdir);
		this.replacelink = replacelink;
		this.replaceto = replaceto;
		KEY = 3;
	}

	public void execute(){
		if (KEY == 1) super.dirdao.replace(replacedir, replaceto);
		if (KEY == 2) super.filedao.replace(replacefile, replaceto);
		if (KEY == 3) super.linkdao.replace(replacelink, replaceto);
	}
	public void undo(){
		super.undo();
	}
}
