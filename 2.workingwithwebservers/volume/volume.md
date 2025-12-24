## 1. Practice about Backup and Restore data
```bash
## 1. Example create container postgres with volume pg_data
docker run -d \
  --name pg \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=testdb \
  -v pg_data:/var/lib/postgresql/data \
  postgres:16
## 2. after create this container we exec this container
docker exec -it pg psql -U postgres -d testdb
# - create table users
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  name TEXT
);
# - insert data to users
INSERT INTO users(name) VALUES ('Ratana'), ('Docker');
# - select data from users
SELECT * FROM users;

## 3. -> backup data logic 
docker exec pg pg_dump -U postgres testdb > backup.sql # now backup.sql in on host machine(In vagrant)
## 4. Delete container and volume 
docker stop pg
docker rm pg
docker volume rm pg_data
## 5. Recreate a postgres impty
docker volume create pg_data
## 6. Create container that use pg_data volume
docker run -d \
  --name pg \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=testdb \
  -v pg_data:/var/lib/postgresql/data \
  postgres:16
## 7. Restore data
docker exec -i pg psql -U postgres -d testdb < backup.sql
## 8. Testing
docker exec -it pg psql -U postgres -d testdb
SELECT * FROM users; ## select in db, it will be show all data
```


## 2. Practice about tmpfs volume
```bash
# Syntax for tmpfs
docker run --mount type=tmpfs,dst=<container-path>
docker run --volume <container-path>

## Run alpine linux OS with shell (sh) and use (--mount)
docker run -it --name tmpfs-mount-demo --mount type=tmpfs,dst=/cache \
 alpine sh

## Run alpine linux OS with shell (sh) and use (--volume)
docker run -it --name tmpfs-volume-demo --volume /cache \
 alpine sh
```






## 3. 