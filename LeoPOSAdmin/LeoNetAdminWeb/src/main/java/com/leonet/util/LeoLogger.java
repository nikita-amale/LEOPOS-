package com.leonet.util;
import java.io.PrintWriter;

import java.io.StringWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



public class LeoLogger  {

	private static Logger logger = LoggerFactory.getLogger("leonetlogger");
	
	private static Logger errorlogger = LoggerFactory.getLogger("errorlogger");
	
	public static void error(String message, Object...arguments) {
		logger.error(message, arguments);
	}
	
	public static void debug(String message, Object...arguments) {
		logger.debug(message, arguments);
	}
	

	
	public static void info(String message, Object...arguments) {
		logger.info(message, arguments);
	}

	public static void warn(String message, Object...arguments) {
		logger.warn(message, arguments);
	}
	
	public static void trace(String message, Object...arguments) {
		logger.trace(message, arguments);
	}
	
	public static void logStackTrace(Throwable exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		errorlogger.trace("Error : {}", sw.toString());
	}
}