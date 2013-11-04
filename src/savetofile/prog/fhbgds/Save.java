package savetofile.prog.fhbgds;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Save {

	public void save(Object o, String filename) throws Exception{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
		out.writeObject(o);
		out.close();
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String[]> load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
		HashMap<String, String[]> o = (HashMap<String, String[]>) in.readObject();
		in.close();
		return o;
	}
	
	@SuppressWarnings("deprecation")
	public String[] loadArrayFromTxt(String path) throws Exception{
		if(path == null) return null;
		String[] strings = new String[10];
		DataInputStream in = new DataInputStream(new FileInputStream(path));
		String string;
		int count = 0;
		while((string = in.readLine()) != null){
			if(count >= 9) break;
			strings[count] = string;
			count++;
		}		
		in.close();
		return strings;
	}
}
