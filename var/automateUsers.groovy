//Add Code here
def call(userName,role,email){

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
						echo "$userName ($role) - $email"
						echo "Lets call API from here using groovy class";
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