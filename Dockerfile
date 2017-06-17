FROM tomcat:alpine
MAINTAINER douglasjohnson

ADD tictactoe-application.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]