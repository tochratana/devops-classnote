Every file/dirctory has 3 things : 
- Owner (user)
- Group
- Permission for:
  - Owner
  - Group
  - Others (everyone else)
Example : 
> -rw-r----- 1 tochratana devops 1024 app.log
- Owner -> tochratana
- Group -> devops
- Permission:
  - Owner: rw-
  - Group: r--
  - Other: ---


Docker Architechture 
The Docker client (docker) is the agent to interact with Docker Daemon. When you use commands such as docker run, the client sends these commands to dockerd to perform what you want docker do. The docker command uses the Docker API. 
The Docker daemon (dockerd) listens for Docker API requests and manages Docker objects such as images, containers, networks, and volumes. 
A Docker registry stores Docker images. Docker Hub is a public registry that anyone can use, and Docker is configured to look for images on Docker Hub by default. You can even run your own private registry. When you use the docker pull or docker run commands, the required images are pulled from your configured registry. When you use the docker push command, your image is pushed to your configured registry.
Docker objects- When you use Docker, you are creating and using images, containers, networks, volumes, plugins, and other objects. This section is a brief overview of some of those objects.



FROM <image>: Specifies the base image your build will start from (e.g., FROM ubuntu:22.04). This must be the first instruction in a Dockerfile.
RUN <command>: Executes commands in a new layer on top of the current image, such as installing packages (e.g., RUN apt-get update && apt-get install -y python3).
WORKDIR <path>: Sets the working directory for subsequent instructions like RUN, CMD, COPY, and ADD.
COPY <src> <dest>: Copies files from your local machine (host) into the image's filesystem.
ENV <name> <value>: Sets environment variables within the image that a running container can use.
EXPOSE <port>: Documents which ports the container will listen on at runtime (e.g., EXPOSE 8080).
CMD ["executable", "param1"]: Provides the default command and arguments for executing the application when a container starts from this image. This command can be overridden when running the container.
ENTRYPOINT ["executable"]: Configures a container to run as an executable. Arguments provided to docker run are appended to the entrypoint command.
USER <user>: Sets the user name or UID to use for subsequent instructions or when running the container, which is a security best practice to avoid running as root.