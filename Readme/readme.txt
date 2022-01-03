1. git repository:https://github.com/haunguyen89/RestAPI-Demo-Nals.git
2. You can test with db local or db public at Heroku
	- With database local:
		+ uncomment line 7->9 and comment line 11->13 file <RestAPI-Demo-Nals\src\main\resources\application.properties>
		+ open Command Prompt and run command to create docker container: docker run -d --rm --name mysql-spring-boot-tutorial -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_USER=haunguyencuu -e MYSQL_PASSWORD=123456 -e MYSQL_DATABASE=test_db -p 3309:3306 --volume mysql-spring-boot-tutorial-volume:/var/lib/mysql mysql:5.7
	- With database public Heroku:
		+ comment line 7->9 and uncomment line 11->13 file <RestAPI-Demo-Nals\src\main\resources\application.properties>
		+ you can connect database public using MySQL Workbench with infomation
			hostname:us-cdbr-east-05.cleardb.net
			port:3306
			username:be49662d085a32
			passwork:8eb6a481
3. Test API with full infomation in file <RestAPI-Demo-Nals\Readme\envident_REST_API.xlsx>
4. You can test local with source code Or run with
	- test API: https://demo-springboot-nalsolution.herokuapp.com/api/v1/works