package com.sample.mvc.model;

public class LogMessage {

	private String ecsCluster;
	private String serverAddress;
	private String continerHostName;
	private String httpMethod;
	private String resourcePath;
	private String applicationName;

	public String getEcsCluster() {
		return ecsCluster;
	}

	public void setEcsCluster(String ecsCluster) {
		this.ecsCluster = ecsCluster;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getContinerHostName() {
		return continerHostName;
	}

	public void setContinerHostName(String continerHostName) {
		this.continerHostName = continerHostName;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public String toString() {
		return "LogMessage [ecsCluster=" + ecsCluster + ", serverAddress=" + serverAddress + ", continerHostName="
				+ continerHostName + ", httpMethod=" + httpMethod + ", resourcePath=" + resourcePath
				+ ", applicationName=" + applicationName + "]";
	}

}
