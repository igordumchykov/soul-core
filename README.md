# Soul Menu Management
Manages restaurant menu for drinks and food

# Prerequisites

- Java 17.x
- Kotlin 1.7.x
- Gradle 7.5.x

# Start application

To start your application, run:

```
AWS_REGION=<AWS region> \
AWS_ACCESS_KEY_ID=<AWS access key id> \
AWS_SECRET_ACCESS_KEY=<AWS secret key> \
SPRING_PROFILES_ACTIVE=<profile dev/qa/prod> \
APPLICATION_SECURITY_AUTHENTICATION_SECRET=<provide any base 64 encoded secret value> \
INIT_USER_EMAIL_LIST=<coma separated emails to be used for users> \
INIT_USER_LOGIN_LIST=<coma separated logins to be used for users> \
INIT_USER_ROLE_LIST=<coma separated role list for users. Values: ROLE_ADMIN,ROLE_USER,ROLE_ANONYMOUS> \
INIT_USER_PASSWORD_LIST=<coma separated passwords to be used for users> \
DB_NAME=<db name> \
DB_URI='<db url>' \
./gradlew bootRun
```

# Run in Docker
- `docker build -t multiplexor88/soul-core:v0.0.1 ./src/main/docker`
- `sudo docker run -d -p 8080:8080 \
  --env AWS_REGION='' \
  --env AWS_ACCESS_KEY_ID='' \
  --env AWS_SECRET_ACCESS_KEY='' \
  --env SPRING_PROFILES_ACTIVE=''' \
  --env APPLICATION_SECURITY_AUTHENTICATION_SECRET=''' \
  --env INIT_USER_EMAIL_LIST=''' \
  --env INIT_USER_LOGIN_LIST=''' \
  --env INIT_USER_ROLE_LIST='' \
  --env INIT_USER_PASSWORD_LIST=''' \
  --env DB_NAME=''' \
  --env DB_URI='' \
  --name soul-core multiplexor88/soul-core:v0.0.1 --network=host`

# Defaults

- application port: 8080

# API Documentation

- when application is running, go to `<app host>:<app port>/swagger-ui/index.html`
- run `./gradlew generateOpenApiDocs` -> open `./build/openapi.json` -> copy file and paste to `https://editor.swagger.io/`

# Contributors
- Igor Dumchykov [igor.dumchykov@gmail.com]
