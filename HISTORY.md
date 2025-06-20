# Project History

## [Unreleased]
- Fixed chat messages showing `$time` and `$text` and removed duplicates by avoiding handling our own broadcasts.
- Enabled AndroidX to fix GitHub Actions build.
- Updated AndroidManifest for Android 12 compatibility.
- Bumped Java compatibility to 17 and updated toolchains.
- Updated CI workflow to run tests on pushes.
- Fixed lint issues and added safer UDP test configuration.
- Added GitHub Actions workflow to build APK and upload it as an artifact.
- Updated workflow to use `actions/upload-artifact@v4` after deprecation of v3.
- Added Telegram step in CI to send the APK to a chat using secrets.
- CI now names the APK with its version when uploading and sending to Telegram.
- Introduced Gradle wrapper scripts and ignored the wrapper jar.
- Initial project scaffold for LocalChat Android app.
- Added best practices for AI agents in `AGENTS.md`.
- Added e2e test for UDP messaging and fixed crash on message send by declaring INTERNET permission.
- Display uncaught exceptions in a copyable crash screen before the app exits.
- Implemented basic chat UI using UDP broadcast messages.
- Fixed NetworkOnMainThreadException by sending UDP messages on a background thread.
- Telegram step now sends the latest HISTORY entry with the APK.
