## Note



### Building the docker image
1. Multi-stage docker build
```bash
## dev.Dockerfile
FROM node:alpine AS builder # base image is a image that run (Environment for run and build)
# image small (slim and alpine)



WORKDIR /app # create folder /app in container for container eveything
COPY package*.json ./ # copy to app
RUN npm i # run `npm i`

# COPY  . . 
COPY src ./src # copy everything to container
COPY public ./public  # copy to container
RUN npm run build  # after copy npm run build


# Stage:2 production 
FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf # copy to nginx
COPY --from=builder /app/build /usr/share/nginx/html # copy stage 1(builder) to /app/build in /usr/share/nginx/html
EXPOSE 80

ENTRYPOINT [ "nginx", "-g", "daemon off;" ] # Run nginx in forground mode: 
```

```bash
docker build -t multi-stage-demo \
  -f dev.Dockerfile .
```