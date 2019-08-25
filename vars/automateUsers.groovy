import com.techm.pipeline.*;
def call(userName,role,email){
	AutomateUtils automateUtils=new com.techm.pipeline.AutomateUtils(this);
	
	pipeline{
		agent any
		stages{
			stage("verify users"){
				steps{
					script{
						sh "pwd && ls -l"
						cleanWs();
						echo "Hi Groovy";
						sh "pwd && ls -l"
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