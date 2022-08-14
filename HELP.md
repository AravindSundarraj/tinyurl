# Simple Tiny Url

Tinyurl application provide service  to create tiny-url for long urls.

# implementation

Java & Spring boot used to develop the application.

# Containers

Application is containerized , used docker-compose (tinyurl,postgres,prometheus & grafana) comes in one network.

# Encryption
Base64 used to derive the url up to 7 characters

# Filter
used Bloom filter to validate the existing data.

# Monitoring
Prometheus and Grafana used to monitior the application performance


# docker commands
docker build  . -t tinyurl:17
docker tag tinyurl:12 <tag-path>/<tag>:tinyurl.17.0
docker push <docker-hub-repo>/<tag>>:tinyurl.17.0
docker-compose up