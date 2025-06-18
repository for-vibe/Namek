# LocalChat

LocalChat is an Android application for chatting over a local network using UDP broadcast messages. This repository is maintained with test-driven development and includes best practices for contributions using AI agents.

## Getting Started
1. Open the project in Android Studio.
2. Build and run on devices within the same local network.
3. Use the simple chat screen to send messages that appear on all peers.

## Development Guidelines
- Follow the principles in `AGENTS.md`.
- Document all significant changes in `HISTORY.md`.
- Write tests before implementing features.

## Roadmap
The project is split into the following milestones:
1. **Networking Layer**: Implement UDP broadcast sender and receiver with tests.
2. **UI Design**: Create screens for listing messages and composing new ones.
3. **Message Storage**: Add local persistence for chat history.
4. **Peer Discovery**: Identify active peers on the network.
5. **Polish**: Improve reliability and handle edge cases.

Contributions should tackle these tasks incrementally.
