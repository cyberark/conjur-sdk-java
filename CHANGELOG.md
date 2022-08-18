# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [4.1.0] - 2022-04-01

### Changed
- Update config/java.yml to contain and set the license name and URL for the client
  [ONYX-16679](https://ca-il-jira.il.cyber-ark.com:8443/browse/ONYX-16679)
- Update okhttp3 to 4.9.2 to resolve 
  [SNYK-JAVA-COMSQUAREUPOKHTTP3-2958044](https://app.snyk.io/vuln/SNYK-JAVA-COMSQUAREUPOKHTTP3-2958044)
  and bump other dependencies to latest version
  [conjur-sdk-java#74](https://github.com/cyberark/conjur-sdk-java/pull/74)
- Update OpenAPI spec to version 5.3.0 and add tests for JWT authenticator
  [conjur-sdk-java#70](https://github.com/cyberark/conjur-sdk-java/pull/70)
- Update project to reflect Trusted Level
  [conjur-sdk-java#58](https://github.com/cyberark/conjur-sdk-java/pull/58)

## [4.0.0] - 2021-06-17
### Changed
- The package name from `org.conjur.sdk` to `com.cyberark.conjur.sdk`
- The main package with endpoint classes from `org.conjur.sdk.api` to `org.conjur.sdk.endpoint`
  [cyberark/conjur-sdk-java#28](https://github.com/cyberark/conjur-sdk-java/pull/28)
- Changed logging level in Okhttp3 so header values arent printed and secrets cannot be leaked.
  [cyberark/conjur-sdk-java#54](https://github.com/cyberark/conjur-sdk-java/pull/54)
- Now force users to utilize https when connecting to Conjur for security reasons.
  [cyberark/conjur-sdk-java#53](https://github.com/cyberark/conjur-sdk-java/pull/53)

### Added
- Automatic authentication for client based on environment variables. Users will not have to manually
  authenticate or refresh an AccessToken if CONJUR_ACCOUNT, CONJUR_LOGIN, and CONJUR_AUTHN_API_KEY are set.
  [cyberark/conjur-sdk-java#7](https://github.com/cyberark/conjur-sdk-java/issues/7)
- New method signatures for all API methods removing optional arguments. This allows API
  methods to be called without having to specify `null` for all optional parameters (if they are unused).
  [cyberark/conjur-sdk-java#15](https://github.com/cyberark/conjur-sdk-java/pull/15)
- Basic client infrastructure and generation tools as well as minimal templates.
  [cyberark/conjur-sdk-java#1](https://github.com/cyberark/conjur-sdk-java/pull/1)
- New script to lint handwritten test files, uses Checkstyle to enforce
  [Google's Java Style](https://google.github.io/styleguide/javaguide.html).
  [cyberark/conjur-sdk-java#3](https://github.com/cyberark/conjur-sdk-java/issues/3)

[Unreleased]: https://github.com/cyberark/conjur-sdk-java/compare/v4.1.0...HEAD
[4.1.0]: https://github.com/cyberark/conjur-sdk-java/compare/v4.0.0...v4.1.0
[4.0.0]: https://github.com/cyberark/conjur-sdk-java/releases/tag/v4.0.0
