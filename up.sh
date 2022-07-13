echo "Starting postgres container.."
docker run --name giveaway-mysql \
  -e MYSQL_ALLOW_EMPTY_PASSWORD=true \
  -e MYSQL_ROOT_PASSWORD \
  -e MYSQL_DATABASE=giveaway \
  -d \
  -p 3306:3306 \
  mysql
