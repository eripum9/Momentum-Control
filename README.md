# MomentumControl

## Overview
MomentumControl is a Minecraft Paper plugin that allows command blocks to **change the velocity (speed) of a desired player** and grant them temporary no-fall-damage protection. This is useful for custom maps, minigames, or automation where you want to speed up or slow down players and prevent fall damage after triggering a command block (for example, with a tripwire).

## Features
- Change the velocity (speed) of a player via command block (supports selectors like `@p`, `@a`, `@s`, etc.).
- Grant no-fall-damage protection to affected players.
- Automatically removes protection when the player lands on the ground.
- No slime block mechanics required, you can also use other movement methods.
- Does **not** fling players upward—only multiplies their current movement speed.

## Installation
1. Ensure you have a Minecraft server running Paper.
2. Download the latest version of the MomentumControl JAR file.
3. Place the JAR file into the `plugins` folder of your Paper server.
4. Restart the server to load the plugin.

## Usage
- Use a command block to run `/changevelocity <player> <value>`.  
  - `<player>` can be a player name or a selector (`@p`, `@a`, `@s`, etc.).
  - `<value>` is the velocity multiplier (use any value between 0.1 and 10).
- The player(s) will have their velocity (speed) multiplied and will not take fall damage until they next touch the ground.

## Commands
- `/changevelocity <player> <value>`: Multiplies the velocity (speed) of the player(s) and grants temporary no-fall-damage protection.  
  **Note:** This command can only be run by a command block.

## Development
This plugin is developed using Java and the Paper API. To build the project, use Gradle. Ensure you have the necessary dependencies specified in the `build.gradle` file.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.