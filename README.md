# MomentumControl

## Overview
MomentumControl is a Minecraft Paper plugin that allows command blocks to **change the velocity (speed) of a desired player** and grant them temporary no-fall-damage protection. This is useful for custom maps, minigames, or automation where you want to speed up or slow down players and prevent fall damage after triggering a command block (for example, with a tripwire).

## Features
- Change the velocity (speed) of a player via command block (supports selectors like `@p`, `@a`, `@s`, etc.).
- Grant no-fall-damage protection to affected players.
- Automatically removes protection when the player lands on the ground.
- No slime block mechanics required, you can also use other movement methods.
- Does **not** fling players upward—only multiplies their current movement speed.

## Usage
- Use a command block to run `/changevelocity <player> <value>`.
  - `<player>` can be a player name or a selector (`@p`, `@a`, `@s`, etc.).
  - `<value>` is the velocity multiplier.
- The player(s) will have their velocity (speed) multiplied and will not take fall damage until they next touch the ground.

- Use a command block to run `/changehorizontalvelocity <player> <value>`.
  - `<player>` can be a player name or a selector (`@p`, `@a`, `@s`, etc.).
  - `<value>` is the horizontal velocity multiplier.
- The player(s) will have only their horizontal (X/Z) velocity multiplied and will not take fall damage until they next touch the ground.

## Commands
- `/changevelocity <player> <value>`: Launches the player(s) in the direction they are facing and grants temporary no-fall-damage protection.
- `/changehorizontalvelocity <player> <value>`: Multiplies only the horizontal (X/Z) velocity of the player(s) and grants temporary no-fall-damage protection.
  **Note:** These commands can only be run by a command block.

## Installation

### Using the Prebuilt JAR (Recommended)
1. Go to the [Releases](https://github.com/yourusername/yourrepo/releases) section of this repository.
2. Download the latest `MomentumControl-x.x.x.jar` file.
3. Place the JAR file into the `plugins` folder of your Paper server.
4. Restart the server to load the plugin.

### Building the Plugin Yourself
1. Clone or download this repository to your computer.
2. Open a terminal in the project directory.
3. Run `gradlew build` (Windows) or `./gradlew build` (Linux/macOS).
4. The compiled JAR will be in the `build/libs` folder.
5. Place the JAR file into the `plugins` folder of your Paper server.
6. Restart the server to load the plugin.

## Development
This plugin is developed using Java and the Paper API. To build the project, use Gradle. Ensure you have the necessary dependencies specified in the `build.gradle` file.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.