# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [4.0.0] - 6/17/21
### Changed
- The main package with endpoint classes from `org.conjur.sdk.api` to `org.conjur.sdk.endpoint`
  [cyberark/conjur-sdk-java#28](https://github.com/cyberark/conjur-sdk-java/pull/28)

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

[Unreleased]: https://github.com/cyberark/conjur-openapi-spec/compare/v4.0.0...HEAD
