package util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Configuration;

public class ConfigurationController{
	public Configuration configObj = new Configuration();
	
	public Configuration setConfiguration(Configuration conf) throws IOException {
		
		// defining configuration
		configObj = conf;
		
		writeConfig(conf);
		return this.configObj;
	}
	
	public FileWriter writeConfig(Configuration conf) throws IOException {	
		FileWriter write = new FileWriter(configObj.getFile());
		
		write.write("user="+conf.getUser()+"\n");
		write.write("password="+conf.getPassword()+"\n");
		write.write("ip="+conf.getIp()+"\n");
		
		write.close();
		
		return write;
	}
	
	public Configuration getConfiguration() {
		// validation if exist
		try {
			
			FileReader read = new FileReader("./configuration.conf");
			
			//read names and results
			//insert content inside Configuration
			Configuration conf = new Configuration();
			
			return conf;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
} 
