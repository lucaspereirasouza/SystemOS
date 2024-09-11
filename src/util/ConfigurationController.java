package util;

import java.io.FileNotFoundException;
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
	
	public Reader readConfig() throws FileNotFoundException {
		FileReader _read = new FileReader("./configuration.conf");
		
		
		//Reader read = new Reader(_read);
		return null;
	}
	
	public Configuration getConfiguration() {
		// validation if exist
		Configuration getConfig = new Configuration();
		try {
			getConfig = new Configuration();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return getConfig;
	}
} 
