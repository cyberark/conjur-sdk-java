# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## Unreleased
### Added
- Automatic authentication for client based on environment variables. Users will not have to manually
  authenticate or refresh an AccessToken if CONJUR_ACCOUNT, CONJUR_LOGIN, and CONJUR_AUTHN_API_KEY are set.
  [cyberark/conjur-sdk-java#7](https://github.com/cyberark/conjur-sdk-java/issues/7)
- Basic client infrastructure and generation tools as well as minimal templates.
  [cyberark/conjur-sdk-java#1](https://github.com/cyberark/conjur-sdk-java/pull/1)
