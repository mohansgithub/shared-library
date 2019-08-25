import com.techm.pipeline.*;
def call(usersMap){
	AutomateUtils automateUtils=new com.techm.pipeline.AutomateUtils(this);
	pipeline{
		agent any
		parameters {
			string(defaultValue: true, description: '', name: 'PARAMETER')
		}

		stages{
			stage("Clean WorkSpace"){
				steps{
					script{
						echo "${PARAMETER}"
						cleanWs();
					}
				}
			}
			stage("Assign roles"){
				steps{
					script{
						echo "Assiging roles";
						usersMap.each {
							def user = it;
							def email=user.email;
							def role=user.role;
							echo("[INFO] Assigning role : ${role}  to ${email}-[${user.name}]");
							automateUtils.assignRole(email,role);
						}
					}
				}
			}
			stage("Assign Project"){
				steps{
					script{
						echo "Assigning project";
						usersMap.each {
							def user = it;
							def email=user.email;
							user.project.each{
								def project = it;
								echo("[INFO] Assigning project : ${project}  to ${email}");
								automateUtils.assignProject(email,project);	
							}
						}
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