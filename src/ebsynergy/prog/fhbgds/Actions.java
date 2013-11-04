package ebsynergy.prog.fhbgds;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import savetofile.prog.fhbgds.Save;

public class Actions {
	static Save save = new Save();
	
	public static void printGrades(){
		if(EBSynergy.grades.isEmpty()){
			UI.outputArea.append("ERROR: No loaded data!\n");
			return;
		}else{
			Set<String> set = EBSynergy.grades.keySet();
			Iterator<String> it = set.iterator();
			int count = 0;
			int nameCount = set.size();
			String[] names = new String[nameCount];
			while(it.hasNext()){
				names[count] = it.next();
				if(names[count] != null){
					count++;
				}
			}
			
			int i = 0;
			for(; i <= names.length; i++){
				try{
					if(names[i] != null){
						UI.outputArea.append(names[i] + ":\n");
						String[] grades = EBSynergy.grades.get(names[i]);
						for(int i1 = 0; i1 <= 6; i1++){
							UI.outputArea.append(grades[i1] + "\n");
							if(i1 > grades.length){
								i1 = 0;
								return;
							}
						}
					}
				}catch (Exception e){
					System.err.println("Reached end of array!");
				}
			}
			UI.outputArea.append("Done!\n");
		}
	}
	
	public static void load(){
		File f = new File("new.txt");
		if(f.exists()){
			try{
				String[] loaded = save.loadArrayFromTxt("new.txt");
				String name = null;
				
				for(int i = 0; i <= 8; i++){
					System.out.println(loaded[i]);
				}
				
				name = loaded[0];
				//String[] newGrades = new String[8];
				
				for(int i = 0; i <= 7; i++){
					loaded[i] = loaded[i + 1];
				}
				
				if(!EBSynergy.grades.containsKey(name)){
					EBSynergy.grades.put(name, loaded);
					UI.outputArea.append("Loaded new entry from \"new.txt\"\n");
				}else{
					UI.outputArea.append("The system already knows about the student in \"new.txt\"\n");
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return;
		}
		try {
			EBSynergy.grades = save.load("data.ebsyn");
			UI.outputArea.append("Successfully loaded from \"data.ebsyn\"\n");
		} catch (Exception e) {
			e.printStackTrace();
			UI.outputArea.append("Failed to load\n");
		}
	}
	
	public static void save(){
		try {
			save.save(EBSynergy.grades, "data.ebsyn");
			UI.outputArea.append("Saved!\n");
			System.out.println("Saved data to \"data.ebsyn\"");
		} catch (Exception e) {
			e.printStackTrace();
			UI.outputArea.append("Failed to save\n");
		}
	}
	
	public static void exit(){
		save();
		System.exit(1);
	}
}
