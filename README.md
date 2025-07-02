# MomentumControl

## Overview
MomentumControl is a Minecraft Paper plugin that allows command blocks to **change the velocity (speed) of a desired player** and grant them temporary no-fall-damage protection. This is useful for custom maps, minigames, or automation where you want to speed up or slow down players and prevent fall damage after triggering a command block (for example, with a tripwire).

## Features
- Change the velocity (speed) of a player via command block, console, or as an operator (supports player names and selectors: `@p`, `@a`, `@s`).
- Grant no-fall-damage protection to affected players.
- Automatically removes protection when the player lands on the ground.
- No slime block mechanics required, you can also use other movement methods.
- `/changevelocity` flings players upward, `/changehorizontalvelocity` flings them forward in the direction they are facing.

## Usage
- Use `/changevelocity <player|selector> <value>`
  - `<player|selector>` can be a player name or a selector (`@p`, `@a`, `@s`, etc.).
  - `<value>` is the upward velocity to apply.
- The player(s) will be launched upward and will not take fall damage until they next touch the ground.

- Use `/changehorizontalvelocity <player|selector> <value>`
  - `<player|selector>` can be a player name or a selector (`@p`, `@a`, `@s`, etc.).
  - `<value>` is the horizontal velocity to apply.
- The player(s) will be launched forward in the direction they are facing and will not take fall damage until they next touch the ground.

## Commands
- `/changevelocity <player|selector> <value>`: Launches the player(s) upward and grants temporary no-fall-damage protection.
- `/changehorizontalvelocity <player|selector> <value>`: Launches the player(s) forward in the direction they are facing and grants temporary no-fall-damage protection.
  **Note:** These commands can be run by a command block, the console, or an operator.

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