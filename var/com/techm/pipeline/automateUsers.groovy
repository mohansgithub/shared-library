//Add Code here
package com.techm.pipeline;
import com.techm.pipeline.AutomateUtils;
def call(userName,role,email){
	AutomateUtils automateUtils=new com.techm.pipeline.AutomateUtils(this);
	
	pipeline{
		agent any
		stages{
			stage("verify users"){
				steps{
					script{
						echo "pwd && ls -l"
						cleanWs();
						echo "Hi Groovy";
						echo "pwd && ls -l"
					}
				}
			}
			stage("Create users"){
				steps{
					script{
						echo "Creating users";
						automateUtils.addUsers("user","jenkins","email");
					}
				}
			}
		}
		post{
			always{
				script{
					echo "Notify stake holders here";
				}
			}
		}
	}
}