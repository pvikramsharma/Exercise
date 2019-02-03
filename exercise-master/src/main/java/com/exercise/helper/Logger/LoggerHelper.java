package com.exercise.helper.Logger;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	private static boolean root = false;

	public static Logger getLogger(Class clas) {
		if (root) {
			return Logger.getLogger(clas);
		}
		File propertiesFile = new File(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");

		PropertyConfigurator.configure(propertiesFile.toString());
		root = true;
		return Logger.getLogger(clas);
	}
}
