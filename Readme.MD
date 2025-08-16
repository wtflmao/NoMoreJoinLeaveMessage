# NoMoreJoinLeaveMessage Mod

## Description

The NoMoreJoinLeaveMessage mod is a Minecraft mod that allows you to customize or completely remove the join and leave server messages. This feature can be useful to reduce spam in the server chat or to customize the player experience.

## Features

- **Message Removal**: You can choose to completely remove the join and leave messages.
- **Configuration**: The mod uses a configuration file in YAML format (`notification.yml`) to manage the message settings.

## Configuration

The mod's configuration is done through the `notification.yml` file. This file is loaded when the server starts.

Here is an example of what the configuration file might look like:

```yaml
{joinNotification: false, leaveNotification: false}
```

In this example, join messages are enabled while leave messages are disabled.

## Installation

To install the mod, follow these steps:

1. Download the latest version of the mod.
2. Copy the mod file into your server's `mods` folder.
3. Restart the server to load the mod.

After installation, the mod will automatically create the `notification.yml` configuration file in the `./config/no-more-join-leave-message` folder if it does not already exist.

## Support

If you need help with the mod, you can open an issue on the GitHub page or directly make pull requests.

## License

The NoMoreJoinLeaveMessage mod is released under the MIT license. You can use, modify, and distribute the mod as you like, as long as you respect the terms of the license.
