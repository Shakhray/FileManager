package command;

import java.util.ArrayList;

import dao.DirDao;
import dao.FileDao;
import filesystem.Dir;
import filesystem.File;

public class Replace extends Command{

	private Dir replacedir;
	private File replacefile;
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

	public void execute(){
		if (KEY == 1) super.dirdao.replace(replacedir, replaceto);
		if (KEY == 2) super.filedao.replace(replacefile, replaceto);
	}
	public void undo(){
		super.undo();
	}
}
