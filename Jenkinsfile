pipeline {
    agent any

    environment {
        // GitHub Credentials
        GITHUB_CREDENTIALS = '7455b1c0-63a1-4e06-8c7f-016578578bdd'

        // Docker Hub Credentials
        DOCKER_CREDENTIALS = 'docker-hub-credentials'
        DOCKER_IMAGE_NAME = 'imadovetch/your-image-name'  // Update with your Docker image name
        DOCKER_TAG = 'latest'  // Tag for the Docker image
    }

    stages {
        stage('Checkout Code') {
            steps {
                script {
                    // Clone the GitHub repository using GitHub credentials
                    checkout scm
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run the tests (e.g., for a Maven project)
                    sh './mvnw clean test'  // For Maven projects, replace with appropriate command
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Log into Docker Hub
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_CREDENTIALS) {
                        // Build the Docker image
                        def customImage = docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_TAG}")
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    // Push the Docker image to Docker Hub
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_CREDENTIALS) {
                        def customImage = docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_TAG}")
                        customImage.push()
                    }
                }
            }
        }

        stage('Push Changes to GitHub') {
            steps {
                script {
                    // Push changes to GitHub using GitHub credentials
                    withCredentials([usernamePassword(credentialsId: GITHUB_CREDENTIALS, usernameVariable: 'GITHUB_USERNAME', passwordVariable: 'GITHUB_TOKEN')]) {
                        sh """
                            git config --global user.name "${GITHUB_USERNAME}"
                            git config --global user.email "your-email@example.com"  // Replace with your email
                            git add .
                            git commit -m "Automated commit from Jenkins"
                            git push https://${GITHUB_USERNAME}:${GITHUB_TOKEN}@github.com/yourusername/your-repository.git
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker images after the pipeline completes
            cleanWs()
        }
    }
}
