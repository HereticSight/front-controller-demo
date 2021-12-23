# Here we're calling the base image, which is a tomcat server compatible with JDK 8
FROM tomcat:8.0-jre8

LABEL maintainer="Kenny Phu"

# Copy the .war file to the tomcat webapps directory
ADD target/FrontControllerDemo.war /usr/local/tomcat/webapps

# The EXPOSE instruction informs Docker that the container listens to the specified port at runtime.
EXPOSE 8080

# The CMD instruction specifies what to run when the container is run.
# In our case, the tomcat server is started by runnign hte following shell script:
CMD ["catalina.sh", "run"]