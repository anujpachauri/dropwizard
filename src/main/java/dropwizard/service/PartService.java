package dropwizard.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dropwizard.model.Part;

public class PartService {

	
	private static List<Part> repository=new ArrayList<>();
	 static int counter=0;
	public List<Part> getParts(){
		return repository;
	}
	public Part getPart(int id){
		
		for(Part part: repository) {
			if(part.getId()==id) {
				return part;
			}
		}
		
		return null;
	}
	public Part createPart(Part part){
		counter++;
		part.setId(counter);
		repository.add(part);
		return part;
	}
	
	
	public String editPart(Part part){
		return null;
	}
	public String deletePart(final int id){
		
		boolean status=false;
		Iterator<Part> itr=repository.iterator();
		while(itr.hasNext()) {
			Part p=itr.next();
			if(p.getId()==id) {
				itr.remove();
				status=true;
			}
		}
		
		if(status) {
			return "Success...";
		}else {
			return "PART_NOT_FOUND";
		}
	}
	
}
