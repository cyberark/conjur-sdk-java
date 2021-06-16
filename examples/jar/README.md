# Pre-Compiled Jar Example

This example shows how to use the client from a pre-compiled fat jar file
(meaning all dependencies are included). The following functionality is demonstrated:

- Setting up and authenticating the client through environment variables
- Loading a policy into Conjur
- Setting and retrieving a secret
- Retrieving a resource
- Retrieving a role

The `run` script will set up a Conjur environment and environment
variables needed by the client before building a fat jar file, and compiling/running
the example.

You can build a fat jar by running the `bin/build` script, which will output
to the `client/target` directory. The fat jar will be named
`conjur-sdk-java-x.x.x-jar-with-dependencies.jar`.
