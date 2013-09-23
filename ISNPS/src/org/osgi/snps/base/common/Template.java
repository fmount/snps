package org.osgi.snps.base.common;

import java.util.List;

public class Template {

	String op;
	
	public Template(String op){
		this.op = op;
	}
	
	public String process(List<String> datas){
		
		if(op.equals("avg")){
			double sum=0;
			for(int i=0;i<datas.size();i++)
				sum+=Double.parseDouble(datas.get(i));
			return String.valueOf(sum/datas.size());
		}
		else{
			System.out.println("Op not supported!");
			return null;	
		}
		
		
	}
}
