# Sample User API
A project to implement the sample user API swagger specification
# Prerequisites
1. Git

    Download Git
    
    https://git-scm.com/downloads
    
    Clone the repository
    
        git clone https://github.com/carlosunad/sampleUserApi
    
2. Mongodb Installed
    
    on mac: 
        
        brew install mongodb 
    
    other systems:
    
    download from: https://docs.mongodb.com/manual/administration/install-community/
    
    Run Mongo
    
    https://docs.mongodb.com/getting-started/shell/tutorial/install-mongodb-on-os-x/#run-mongodb
    
3. gradle
    
    Follow the instructions here
    https://gradle.org/install/#install
    
# Running the application
1. Clone the repository 
    
        git clone https://github.com/carlosunad/sampleUserApi
        
2. Access the project folder e.g. 

        cd sampleUserApi
    
3. Execute the startup command 

       gradle bootRun

# Test coverage

Currently the test coverage is around 40% %

The following command is used to verify the code coverage

    gradle test jacocoTestCoverageVerification 
    
# Log files
the log file is located at the root folder and is called:

    logging-user-api.log
    
# Swagger test url

http://localhost:8080/swagger/index.html
