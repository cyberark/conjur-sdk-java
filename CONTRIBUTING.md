# Contributing

For general contribution and community guidelines, please see the [community repo](https://github.com/cyberark/community).

## Table of Contents
- [Contributing](#contributing)
  * [Table of Contents](#table-of-contents)
  * [Development](#development)
    + [Refreshing the client](#refreshing-the-client)
    + [Utility Scripts](#utility-scripts)
  * [Testing](#testing)
  * [Releases](#releases)
    + [Update the version and changelog](#update-the-version-and-changelog)
    + [Tag the version](#tag-the-version)
    + [Add a new GitHub release](#add-a-new-github-release)
  * [Contributing workflow](#contributing-workflow)

## Development

This project requires only Docker for all testing and generation scripts. If you want
to run the integration tests locally you will need Java 15 and Maven installed.

### Refreshing the client

Each time there is an update to the [OpenAPI Specification](https://github.com/cyberark/conjur-openapi-spec)
the client will need to be "refreshed" and released. This can be done by running the `bin/refresh_client` script,
the commit message `Client refresh` is adequate. Make sure to follow the steps laid out in the [releases](#releases)
section when releasing the client after a refresh.

### Utility Scripts

`bin/test_integration`
* Used to run the suite of integration tests against a Conjur OSS instance.
* Stands up a new instance of Conjur OSS in a Docker environment, compiles the
  client, and runs the tests with Maven.

`bin/build`
* Used to compile & build a jar archive for the client.
* Outputs the compiled version to `client/target`

`bin/refresh_client`
* Used to update the client with new changes to the OpenAPI Specification.
* Pulls down the latest version of the spec and regenerates the client.
* Only certain files will be updated, see `client/.openapi-generator-ignore` for ignored files.

`bin/start_conjur`
* Used to start a new local Conjur OSS instance based on the project's `docker-compose.yml` file.

`bin/lint`
* Used to enforce [Google's Java Style](https://google.github.io/styleguide/javaguide.html) on
  handwritten test files.

## Testing

To run the integration tests use the `bin/test_integration` script. This will bring up an
instance of Conjur OSS, compile the client and run the tests via Maven.

## Releases

A new release must be created whenever a new version of the
[OpenAPI Specification](https://github.com/cyberark/conjur-openapi-spec) is released.

### Update the version and changelog
1. Examine the changelog and decide on the version bump rank (major, minor, patch).
1. Change the title of _Unreleased_ section of the changelog to the target
version.
   - Be sure to add the date (ISO 8601 format) to the section header.
1. Add a new, empty _Unreleased_ section to the changelog.
   - Remember to update the references at the bottom of the document.
1. Update the [README](./README.md#requirements) Version chart to reflect compatability with the
   new release version.
1. Update the `version` in the client/pom.xml and templates/libraries/okhttp-gson/pom.mustache files.
1. Commit these changes (including the changes to NOTICES.txt, if there are any).
   `Bump version to x.y.z` is an acceptable commit message.
1. Push your changes to a branch, and get the PR reviewed and merged.

### Tag the version
1. Tag the version on the master branch using eg. `git tag -s vx.y.z -m vx.y.z`. Note this
   requires you to be able to sign releases. Consult the
   [github documentation on signing commits](https://help.github.com/articles/signing-commits-with-gpg/)
   on how to set this up.

1. Push the tag: `git push vx.y.z` (or `git push origin vx.y.z` if you are working
   from your local machine).

### Add a new GitHub release

1. Create a new release from the tag in the GitHub UI
1. Add the [CHANGELOG](./CHANGELOG.txt) for the current version to the GitHub release description

## Contributing workflow

1. [Fork the project](https://help.github.com/en/github/getting-started-with-github/fork-a-repo)
2. [Clone your fork](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository)
3. Make local changes to your fork by editing files
3. [Commit your changes](https://help.github.com/en/github/managing-files-in-a-repository/adding-a-file-to-a-repository-using-the-command-line)
4. [Push your local changes to the remote server](https://help.github.com/en/github/using-git/pushing-commits-to-a-remote-repository)
5. [Create new Pull Request](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request-from-a-fork)

From here your pull request will be reviewed and once you've responded to all
feedback it will be merged into the project. Congratulations, you're a contributor!
