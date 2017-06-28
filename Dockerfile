FROM openjdk:8-jre-alpine
MAINTAINER douglasjohnson

ADD tictactoe.war /usr/local/

CMD ["java", "-jar", "/usr/local/tictactoe.war"]