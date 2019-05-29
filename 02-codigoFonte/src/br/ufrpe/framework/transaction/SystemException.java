package br.ufrpe.framework.transaction;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class SystemException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7575349858269050342L;

	private Exception exception;
	private int type;
	private String msgDebug;

	public SystemException() {
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(int type, Exception exception) {
		this.exception = exception;
		this.type = type;
	}

	public SystemException(String message, Exception exception) {
		super(message);
		this.exception = exception;
	}

	public SystemException(Exception exception) {
		this.exception = exception;
	}

	public Exception getException() {
		return this.exception;
	}

	public String listStackTrace() {
		StringWriter writer = new StringWriter(500);
		printStackTrace(new PrintWriter(writer));
		return writer.toString();
	}

	public void printStackTrace(PrintStream p) {
		if (exception != null) {
			exception.printStackTrace(p);
		} else {
			super.printStackTrace(p);
		}
	}

	public void printStackTrace(PrintWriter p) {
		if (exception != null) {
			exception.printStackTrace(p);
		} else {
			super.printStackTrace(p);
		}
	}

	public void printStackTrace() {
		if (exception != null) {
			exception.printStackTrace();
		} else {
			super.printStackTrace();
		}
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public String getMsgDebug() {
		return msgDebug;
	}
	public void setMsgDebug(String msgDebug) {
		this.msgDebug = msgDebug;
	}
}