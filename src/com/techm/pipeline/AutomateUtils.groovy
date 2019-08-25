//Add Code Here
package com.techm.pipeline;

public class AutomateUtils{

	def pipeline;
	public AutomateUtils(pipeline){
		this.pipeline=pipeline;
	}
	
	def assignRole(email,role){
		def user="admin";
		def password="admin";
		
		def url=pipeline.env.JENKINS_URL+"role-strategy/strategy/assignRole";
		def data="type=globalRoles&roleName=${role}&sid=${email}"
		def contentType="application/x-www-form-urlencoded";
		
		post(url,contentType,data,"${user}:${password}");
		pipeline.echo("[INFO] Assigned role - ${role} to ${email}");
		
	}
	def assignProject(email,project){
		def user="admin";
		def password="admin";
		
		def url=pipeline.env.JENKINS_URL+"role-strategy/strategy/assignRole";
		def data="type=projectRoles&roleName=${project}&sid=${email}"
		def contentType="application/x-www-form-urlencoded"
		
		post(url,contentType,data,"${user}:${password}");
		pipeline.echo("[INFO] Assigned project ${url} - ${project} to ${email}");
	}
	
	def get(url,credentials){
		//Lets call GET to APIs from here
		def base64Encode="${credentials}".bytes.encodeBase64().toString();
		def get=new URL(url).openConnection();
		pipeline.echo("[REST API] GET URL - ${url}");
		get.setRequestMethod("GET");
		get.setDoOutput(true);
		get.setRequestProperty("Authorization","Basic ${base64Encode}");
		def responseCode = get.getResponseCode();
		pipeline.echo("[REST API] Status Code ${responseCode}");
		if(responseCode.equals(200) || responseCode.equals(201)){
			def responseBody=get.getInputStream().getText();
			return responseBody;
		}else{
			throw new Exception("[ERROR] Error Status - ${responseCode}");
		}
	}
	
	def post(url,contentType,data,credentials){
		//Lets call POST to APIs from here
		pipeline.echo("[REST API] Data - ${data}")
		def base64Encode="${credentials}".bytes.encodeBase64().toString();
		def post=new URL(url).openConnection();
		//pipeline.echo("[REST API] POST URL - ${url}");
		post.setRequestMethod("POST");
		post.setDoOutput(true);
		post.setRequestProperty("Authorization","Basic ${base64Encode}");
		post.setRequestProperty("Content-Type","${contentType}");
		post.getOutputStream().write(data.getBytes("UTF-8"));
		def responseCode = post.getResponseCode();
		pipeline.echo("[REST API] Status Code ${responseCode}");
		if(responseCode.equals(200) || responseCode.equals(201)){
			def responseBody=post.getInputStream().getText();
			return responseBody;
		}else{
			throw new Exception("Error Status - ${responseCode}");
		}
	}
}