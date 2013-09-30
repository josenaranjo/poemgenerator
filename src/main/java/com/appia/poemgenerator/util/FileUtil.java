package com.appia.poemgenerator.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class FileUtil {
	
	private final static Logger logger = Logger.getLogger(FileUtil.class);
	
	public static List<String> readFileLines(String path) {
		logger.info("Reading the file " + path);
		File file = new File(path);
		List<String> lines = new ArrayList<>();
		if(file.exists()) {
			try {
	      lines = FileUtils.readLines(file);
      } catch (IOException e) {
	      logger.error("There was an error reading the file " + path, e);
      }
		} else {
			logger.debug("The file " + path + " doesn't exist");
		}
		return lines;
	}

}
