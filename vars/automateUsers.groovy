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
			stage("Assign roles"){
				steps{
					script{
						echo "Creating users";
						def listmap = [
							[a: 4, b: 16, c: 64],
							[x: 5, y: 25, z: 625],
						]

						echo('Using "it":');
						listmap.each {
							def newmap = it;
							echo("first level item: " + newmap);
							newmap.each {
								echo("    ${it.key}: ${it.value}");
							}
						}
						automateUtils.assignRole("user","jenkins","email");
					}
				}
			}
			stage("Assign Project"){
				steps{
					script{
						echo "Assigning project";
						automateUtils.assignProject("user","project");
					}
				}
			}
		}
		post{
			always{
				script{
					echo "Notify stakeholders here";
				}
			}
		}
	}
}