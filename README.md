# Conjur Generated Java Client
An API client for Conjur written in Java and generated by the [OpenApi](https://github.com/cyberark/conjur-openapi-spec) specification

Find [more from CyberArk](https://github.com/cyberark).

## Certification level

![](https://img.shields.io/badge/Certification%20Level-Certified-28A745?link=https://github.com/cyberark/community/blob/master/Conjur/conventions/certification-levels.md)

This repo is a **Certified** level project. It's a community contributed project that **has been reviewed and tested by CyberArk
and is trusted to use with Conjur Open Source, Conjur Enterprise, and Conjur Cloud****. For more detailed information on our certification levels, see [our community guidelines](https://github.com/cyberark/community/blob/master/Conjur/conventions/certification-levels.md#trusted).

Disclaimer: Please note that OIDC and LDAP are not currently supported for Conjur Cloud. Please refer to the Conjur Cloud documentation for more information on supported authentication methods.
https://docs.cyberark.com/conjur-cloud/latest/en/content/operations/authn/cjr-authn-support.htm?tocpath=Authenticate%20workloads%7C_____1

## Requirements

This project requires Docker and access to DockerHub.

The OpenAPI Specification is compatible with [Conjur Open Source v1.9+](https://github.com/cyberark/conjur).
Clients are generated using [OpenAPI Generator v4.3.1](https://github.com/OpenAPITools/openapi-generator/tree/v4.3.1).

Each Java Client version corresponds to a specific API version release, the API client having
its own version allows changes to be made to the client outside of updates to the API.

| Java Client Version | [Conjur API Version](https://github.com/cyberark/conjur-openapi-spec/releases) |
| :-----------------: | :----------------: |
| 4.0.x               | 5.1.1              |
| 4.1.x               | 5.3.0              |

## Configuring the Client

### Prerequisites

It is assumed that Conjur (OSS or Enterprise) and the Conjur CLI have already been
installed in the environment and running in the background. If you haven't done so,
follow these instructions for installation of the [OSS](https://docs.conjur.org/Latest/en/Content/OSS/Installation/Install_methods.htm)
and these for installation of [Enterprise](https://docs.cyberark.com/Product-Doc/OnlineHelp/AAM-DAP/Latest/en/Content/HomeTilesLPs/LP-Tile2.htm).

Once Conjur and the Conjur CLI are running in the background, you are ready to start
setting up your Java app to work with our Conjur Java API!

#### Using conjur-sdk-java with Conjur Open Source 

Are you using this project with [Conjur Open Source](https://github.com/cyberark/conjur)? Then we 
**strongly** recommend choosing the version of this project to use from the latest [Conjur OSS 
suite release](https://docs.conjur.org/Latest/en/Content/Overview/Conjur-OSS-Suite-Overview.html). 
Conjur maintainers perform additional testing on the suite release versions to ensure 
compatibility. When possible, upgrade your Conjur version to match the 
[latest suite release](https://docs.conjur.org/Latest/en/Content/ReleaseNotes/ConjurOSS-suite-RN.htm); 
when using integrations, choose the latest suite release that matches your Conjur version. For any 
questions, please contact us on [Discourse](https://discuss.cyberarkcommons.org/c/conjur/5).

### Setup
The Conjur Java SDK can be imported manually through building the source code locally, 
or by using a dependency configuration to import from Maven Central. Please refer to
the following instructions for your specific use case.

#### Using the Source Code

You can grab the library's dependencies from the source by using Maven **or** locally
by generating a JAR file and adding it to the project manually.

To do so from the source using Maven, following the setup steps below:

1. Create new Maven project using an IDE of your choice
2. If you are using Maven to manage your project's dependencies, include the following
   Conjur API dependency snippet in your `pom.xml` under `<project>`/`<dependencies>`:

```xml
    <dependency>
      <groupId>com.cyberark</groupId>
      <artifactId>conjur-sdk-java</artifactId>
      <version>4.0.0</version>
    </dependency>
```

_NOTE:_ Depending on what version of the Java compiler you have, you may need to update
the version. At this time, the `{version}` that we are targeting compatibility with is
Java 8:

```xml
  <properties>
    <maven.compiler.source>{version}</maven.compiler.source>
    <maven.compiler.target>{version}</maven.compiler.target>
  </properties>
```

3. Run `mvn install -DskipTests` in this repo's directory to install Conjur API into your
   local maven repository.

#### Using the Jarfile

If generating a JAR is preferred, you can build the library locally and add the dependency
to the project manually by following the setup steps below:

1. Clone the Conjur Java API repo locally: `git clone {repo}`
2. Go into the cloned repository with `cd conjur-sdk-java`
3. Run `mvn package -DskipTests` to generate a JAR file. The output `.jar` files will be located
   in the `target` directory of the repo

_NOTE:_ The above command runs `mvn package` without running the integration tests, since
these require access to a Conjur instance. You can run the integration tests with
the `./bin/test_integration` script. For more information on how to run the tests, take a look at
our [Contributing](https://github.com/cyberark/conjur-api-java/blob/main/CONTRIBUTING.md) guide.

4a. For Intellij, Follow the steps outlined [here](https://www.jetbrains.com/help/idea/library.html)
    to add the SDK JAR files into the new app's project.

4b. For Eclipse you `Right click project > Build Path > Configure Build Path > Library > Add External JARs`.

4c. If you are working with the Maven CLI, you can manually install the `.jar` into your Maven.
    repository by running the following (replacing `$VERSION` with the appropriate version
    of the API):

```sh-session
$ mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file \
    -Dfile=/path/to/api/repo/target/conjur-sdk-java-$VERSION.jar
```

or

```sh-session
$ mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file \
    -Dfile=/path/to/api/repo/target/conjur-sdk-java-$VERSION-with-dependencies.jar
```

or

```sh-session
$ mvn install:install-file -Dfile=/path/to/api/repo/target/conjur-sdk-java-$VERSION-with-dependencies.jar \
    -DgroupId=com.cyberark \
    -DartifactId=conjur-sdk-java \
    -Dversion=$VERSION \
    -Dpackaging=jar
```

#### Using Maven Releases

To make use of tagged releases published to Maven, verify that you have the dependency 
added to your `pom.xml`

1. Add the following snippet to `pom.xml`
```xml
<dependency>
  <groupId>com.cyberark</groupId>
  <artifactId>conjur-sdk-java</artifactId>
  <version>x.x.x</version>
</dependency>
```

#### Using Maven Snapshots
To make use of SNAPSHOTS, which are deployed following a nightly build, there are 
several steps required for configuring your project.

> Note: Snapshots contain the latest changes to `conjur-sdk-java`, but it is recommended
> to use the current stable release unless there is a significant update required by your
> project 

1. Add the following to your `settings.xml`
```xml
<profiles>
  <profile>
     <id>allow-snapshots</id>
        <activation><activeByDefault>true</activeByDefault></activation>
     <repositories>
       <repository>
         <id>snapshots-repo</id>
         <url>https://oss.sonatype.org/content/repositories/snapshots</url>
         <releases><enabled>false</enabled></releases>
         <snapshots><enabled>true</enabled></snapshots>
       </repository>
     </repositories>
   </profile>
</profiles>
```

Alternatively, add the following to your list of repositories in `pom.xml`
```xml
<repository>
  <id>oss.sonatype.org-snapshot</id>
  <url>http://oss.sonatype.org/content/repositories/snapshots</url>
  <releases>
    <enabled>false</enabled>
  </releases>
  <snapshots>
    <enabled>true</enabled>
  </snapshots>
</repository>
```

2. In your `pom.xml`, verify that your `conjur-sdk-java` dependency includes `SNAPSHOT`
in the version tag.
```xml
<dependency>
  <groupId>com.cyberark</groupId>
  <artifactId>conjur-sdk-java</artifactId>
  <version>x.x.x-SNAPSHOT</version>
</dependency>
```

#### Using Other Dependency Management Configurations
Please refer to the instructions available on [Maven Central](https://search.maven.org/artifact/com.cyberark/conjur-sdk-java) 
and select a version for specific instructions on including the Conjur Java API in your
project through Gradle, Kotlin, and more!

### Configuration

Once the setup steps have been successfully run, we will now define the variables needed
to make the connection between the new app and Conjur. You can do this by setting
[environment variables](#environment-variables) or performing setup programmatically.

_NOTE:_ System properties will override enviroment values when both are defined for a
variable.

#### Environment Variables

In Conjur (both Open Source and Enterprise), environment variables are mapped to configuration variables
by prepending `CONJUR_` to the all-caps name of the configuration variable. For example,
`appliance_url` is `CONJUR_APPLIANCE_URL`, `account` is `CONJUR_ACCOUNT` etc.

The following environment variables need to be included in the app's runtime environment in
order use the Conjur API if no other configuration is done (e.g. over system properties or
CLI parameters):

- `CONJUR_APPLIANCE_URL` - The URL of the Conjur instance you are connecting to. When connecting to
  Conjur Enterprise configured for high availability, this should be the URL of the master load balancer (if
  performing read and write operations) or the URL of a follower load balancer (if performing
  read-only operations).
- `CONJUR_ACCOUNT` - Conjur account that you are connecting to. This value is set during Conjur deployment.
- `CONJUR_AUTHN_LOGIN` - User/host identity
- `CONJUR_AUTHN_API_KEY` - User/host API key
- `CONJUR_AUTHN_URL` - (optional) Alternate authentication endpoint. By default the client
  uses the standard `/authn` for generic username and API key login flow.

For example, you can specify the environment variables like so:

```sh-session
export CONJUR_APPLIANCE_URL=https://conjur.myorg.com/api
export CONJUR_ACCOUNT=myorg
export CONJUR_AUTHN_LOGIN=host/myhost.example.com
export CONJUR_AUTHN_API_KEY=sb0ncv1yj9c4w2e9pb1a2s...
```

or you could provide these at runtime to your jar:
```sh-session
$ CONJUR_APPLIANCE_URL=https://conjur.myorg.com/api \
  CONJUR_ACCOUNT=myorg \
  CONJUR_AUTHN_LOGIN=host/myhost.example.com \
  CONJUR_AUTHN_API_KEY=sb0ncv1yj9c4w2e9pb1a2s... \
  java -jar myConjurClient.jar
```
If you are using a host-based user like this example shows, you will need to add the host
to Conjur with the proper privileges in policy in order to know the appropriate
`CONJUR_AUTHN_LOGIN` and `CONJUR_AUTHN_API_KEY` values.

### Set Up Trust Between App and Conjur

By default, the Conjur appliance generates and uses self-signed SSL certificates. Without
trusting them, your Java app will not be able to connect to the Conjur server over APIs
and so you will need to configure your app to trust them. This is accomplished by loading
the Conjur certificate into the client either by way of setting an environment variable
or setting it programmatically.

#### Environment Variable

There are two environment variables which can be used to set the certificate value:
`CONJUR_CERT_FILE` which should be a filepath to the certificate file and
`CONJUR_SSL_CERTIFICATE` which should be the full text of the certificate.

```sh
export CONJUR_CERT_FILE="test.crt"
export CONJUR_SSL_CERTIFICATE="<Certificate Text>"
```

#### Setting Certificate Programmatically

The API client can be configured by using either the `setCertFile`, or `setSslCaCert`
methods if you have a filepath or certificate text respectively.

```java
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.ApiClient;

// Get the systems default API client
ApiClient client = Configuration.getDefaultApiClient();

client.setCertFile("<filepath>");
// or
client.setSslCaCert(CERT_INPUT_STREAM);

// If we want to make sure this API client configuration is always used
Configuration.setDefaultApiClient(client);
```

### Authorization Examples

As mentioned in the [Configuration](#configuration) section, you can provide varying ways
for your app to authenticate against a Conjur server. Environment variables are the best
way to do this as authentication will all occur behind the scenes. However you can set these
values manually. Once you have chosen from one of the patterns below that works for you, you
can now create a `Conjur` class instance values to access Conjur services and make RESTful API calls.

#### Environment Variables

```bash
export CONJUR_ACCOUNT=<account specified during Conjur setup>
export CONJUR_APPLIANCE_URL=<Conjur endpoint URL>
export CONJUR_AUTHN_LOGIN=<user/host identity>
export CONJUR_AUTHN_API_KEY=<user/host API key (not password) - see notes about `CONJUR_AUTHN_URL`>
export CONJUR_CERT_FILE=<path to ca.crt file>
```
```java
import com.cyberark.conjur.sdk.endpoint.SecretsApi;

// Configured/authenticated using environment variables
SecretsApi api = new SecretsApi();
```

#### Username and API key

```bash
export CONJUR_ACCOUNT=<account specified during Conjur setup>
export CONJUR_APPLIANCE_URL=<Conjur endpoint URL>
export CONJUR_CERT_FILE=<path to ca.crt file>
```

```java
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.ApiClient;

// Get the systems default API client
ApiClient client = Configuration.getDefaultApiClient();
// Given you already have an api key you can just set the clients value
client.setUsername("username");
client.setApiKey("API key");

// If we want to make sure this API client configuration is always used
Configuration.setDefaultApiClient(client);
```

#### Username and Password

_Note:_ This is only for non host users.

```bash
export CONJUR_ACCOUNT=<account specified during Conjur setup>
export CONJUR_APPLIANCE_URL=<Conjur endpoint URL>
export CONJUR_CERT_FILE=<path to ca.crt file>
```

```java
import com.cyberark.conjur.sdk.ApiClient;
import com.cyberark.conjur.sdk.Configuration;
import com.cyberark.conjur.sdk.endpoint.AuthenticationApi;

// Get the systems default API client
ApiClient client = Configuration.getDefaultApiClient();
// Given you already have an api key you can just set the clients value
client.setUsername("username");
client.setPassword("password")

// Use our configured client to get an instance of the AuthenticationApi
AuthenticationApi authApi = new AuthenticationApi(client);
// Retrieve a new API key using our credentials
String apiKey = authApi.getAPIKey("account");
client.setApiKey(apiKey);

Configuration.setDefaultApiClient(client);
```

### Client APIs

To use the client, you will first create an instance of the specific api client and then call methods
to send requests to the Conjur API. The most common use case is adding and retrieving
a secret from Conjur, so we provide some sample code for this use case below.

#### Secrets (`SecretsApi`)

These examples assume a properly configured default ApiClient with authentication
credentials and a proper conjur policy loaded declaring the secret.

##### `void createSecret(String account, String kind, String secretId, String expirations, String requestID, String variableValue)`

Example:
```java
import com.cyberark.conjur.sdk.SecretsApi;

SecretsApi api = new SecretsApi();
secretsApi.createSecret(ACCOUNT, "variable", VARIABLE_ID, null, null, VARIABLE_VALUE);
```
_Note:_ The ACCOUNT constant is the Conjur account the variable was loaded under.

##### `String getSecret(String account, String kind, String secretId)`

Retireves a secret based on its ID.

Example:
```java
import com.cyberark.conjur.sdk.SecretsApi;

SecretsApi api = new SecretsApi();
String result = api.getSecret(ACCOUNT, "variable", VARIABLE_ID);
```

## Contributing

We welcome contributions of all kinds to this repository. For instructions on how to get started and descriptions
of our development workflows, please see our [contributing guide](CONTRIBUTING.md).

## License

Copyright (c) 2020 CyberArk Software Ltd. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

For the full license text see [`LICENSE`](LICENSE).
