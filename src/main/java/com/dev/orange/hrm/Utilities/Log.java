package com.dev.orange.hrm.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Log {

	public static Logger logger = LogManager.getLogger(Logger.class.getClass());

	public static void setLogDirectoryName() {

		try {

			DateTimeFormatter datetimeformat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(datetimeformat.format(now));
			String datetime = datetimeformat.format(now);
			datetime = datetime.replace("/", "-").replace(":", "-").replace(" ", "_time-");
			GlobalVariables.logFolderName = "Log_date-" + datetime;

			if (new File("ExecutionReports\\ExecutionLogs").exists()) {
				new File("ExecutionReports\\ExecutionLogs" + "\\" + GlobalVariables.logFolderName).mkdir();
			} else {
				new File("ExecutionReports\\ExecutionLogs").exists();
				new File("ExecutionReports\\ExecutionLogs\\" + GlobalVariables.logFolderName).mkdir();
			}
			GlobalVariables.logFolder = "ExecutionReports\\ExecutionLogs\\" + GlobalVariables.logFolderName;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void startTestScript(String TestScriptName) {

		logger.info("***************************************************************");
		logger.info("********************Execution started for : " + TestScriptName + " ****************");
		logger.info("***************************************************************");

	}

	public static void endTestScript(String TestScriptName, String testStatus) {

		logger.info("***************************************************************");
		logger.info("********************Test Execution Status : " + testStatus + " ****************");
		logger.info("********************Execution ended for : " + TestScriptName + " ****************");
		logger.info("***************************************************************");

	}

	public static void info(String message) {
		logger.info(message);
	}

	public static void info(double message) {
		logger.info(message);
	}

	public static void info(Object message) {
		logger.info(message);
	}

	public static void warn(String message) {
		logger.warn(message);
	}

	public static void error(String message) {
		logger.error("Failed : " + message);
	}

	public static void fatal(String message) {
		logger.fatal(message);
	}

	public static void debug(String message) {
		logger.debug(message);
	}

	public static String setLogFile(String scenarioName) {
		String logFileName = GlobalVariables.logFolder + "\\" + scenarioName + "_log.txt";
		File file = new File(logFileName);
		try {
			if (file.createNewFile()) {
				logger.info("log file is created - " + logFileName);
			} else
				logger.info("log file is already exists  - " + logFileName);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return logFileName;
	}

	public static void copyFile(String logFileName) {

		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;

		try {
			File inputfile = new File("logger.logs");
			File outputfile = new File(logFileName);
			inputStream = new FileInputStream(inputfile);
			outputStream = new FileOutputStream(outputfile);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
				inputStream.close();
				outputStream.close();
				System.out.println("File Copied Successfully");
				logger.info("File Copied Successfully");
				new FileWriter("logger.logs").close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void renameFile(String newFileName) {
		new File("src\\main\\resources\\logs.txt").renameTo(new File(newFileName));
		new File("src\\main\\resourcess\\logs.txt").delete();
	}

	public static void clearTextFile(String logFileName) {

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(logFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writer.print("");
		writer.close();

	}

	public static void blankLinesDeleter(String logFileName) {

		String nonblankData = " ";
		PrintWriter writer = null;
		Scanner deleter = null;
		File oldFile = new File(logFileName);
		try {
			deleter = new Scanner(oldFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(deleter);
		while (deleter.hasNextLine()) {
			String currentLine = deleter.nextLine();
			if (!currentLine.isEmpty()) {
				nonblankData += currentLine + System.lineSeparator();
			}
		}
		try {
			writer = new PrintWriter(new FileWriter(logFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		writer.print(nonblankData);
		writer.close();
	}

	public static void warn(String message, Throwable e) {

		logger.warn(message);
	}
}
