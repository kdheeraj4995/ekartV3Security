# EkartV3 Security

Java dependency with play core libraries that can be imported in play sbt projects directly, to provide security annotations and httpClient for accessing api's. 

Play 2.7.3
## Installation

Environment variables that needed to be configured.

```bash
SERVICE
VERSION
BUILD_DATE
PUBLIC_KEY
PRIVATE_KEY
ENVIRONMENT
API_HOST_DOMAIN
```

Update app.conf to add the following code.

```bash
service = {
    name = ${SERVICE},
    version = ${VERSION},
    buildDate = ${BUILD_DATE},
    publicKey = ${PUBLIC_KEY},
    privateKey = ${PRIVATE_KEY},
    environment = ${ENVIRONMENT},
    apiHostDomain = ${API_HOST_DOMAIN}
}

play.modules = {
    enabled += "com.dheeraj.ekartv3.modules.SecurityModule"
}
```

## Usage

Generic controller api for all Ms.
```scala
GET        /        com.dheeraj.ekartv3.controllers.PublicKeyController.aboutMe(request:Request)
```

Inject HttpClient directly into any Java class.
```java
import com.dheeraj.ekartv3.services.HttpClient;
import com.google.inject.Inject;

@Inject
public HomeController(HttpClient httpClient) {
   this.httpClient = httpClient;
}     
```

Inject AboutMe directly into any Java class to access all environment variables configured.
```java
import com.dheeraj.ekartv3.models.AboutMe;
import com.google.inject.Inject;

@Inject
public HomeController(AboutMe aboutMe) {
   this.aboutMe = aboutMe;
}     
```
