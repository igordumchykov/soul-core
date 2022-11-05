# Soul Menu Management
Manages restaurant menu for drinks and food

# Prerequisites

- Java 17.x
- Kotlin 1.7.x

# Start application

To start your application in the dev profile, run:

```
SPRING_PROFILES_ACTIVE=dev \
APPLICATION_SECURITY_AUTHENTICATION_SECRET=<provide any base 64 encoded secret value> \
INIT_USER_EMAIL_LIST=<coma separated emails to be used for users> \
INIT_USER_LOGIN_LIST=<coma separated logins to be used for users> \
INIT_USER_ROLE_LIST=<coma separated role list for users. Values: ROLE_ADMIN,ROLE_USER,ROLE_ANONYMOUS> \
INIT_USER_PASSWORD_LIST=<coma separated passwords to be used for users> \
DB_NAME=<db name> \
DB_URI='<db url>' \
./gradlew bootRun
```

# Defaults

- application port: 8080

# API Documentation

- when application is running, go to `<app host>:<app port>/swagger-ui/index.html`
- run `./gradlew generateOpenApiDocs` -> open `./build/openapi.json` -> copy file and paste to `https://editor.swagger.io/`

# Contributors
- Igor Dumchykov [igor.dumchykov@gmail.com]
