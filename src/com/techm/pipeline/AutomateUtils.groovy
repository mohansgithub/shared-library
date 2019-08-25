//Add Code Here
package com.techm.pipeline;

public class AutomateUtils{

	def pipeline;
	public AutomateUtils(pipeline){
		this.pipeline=pipeline;
	}
	
	def addUsers(userName,role,email){
		pipeline.echo("${userName} (${role}) - ${email}");
		
		def user;
		def password;
		
		get("URL","${user}:${password}");
		
	}
	def get(url,credentials){
		pipeline.echo("Lets call API from here");
	}
	
	def post(url,data,contentType,credentials){
		pipeline.echo("Lets call API from here using groovy class");
	}
}