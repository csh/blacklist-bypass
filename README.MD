blacklist-bypass
================

_For those who do not keep up to date on drama, Mojang has recently deployed a blacklist to encourage EULA compliance from
some troublesome members of the community. This blacklist affects all versions of Minecraft that utilise netty._

Publishing this project is not intended as an act of defiance, but rather something to encoruage Mojang to step up their game.
The actual code used to bypass the blacklist took no longer than 10 minutes to write, and most of that time was spent drinking coffee.

![Quick Demonstration](https://vgy.me/4cPUHU.gif)
_Logging into DatPixel, a server blocked at time of writing, with the patch enabled._
_If you represent DatPixel and happen to be reading this then I will remove the GIF if desired._

## Test It

I'm not going to give out all the details, as that would be no fun. All code here has only been tested with 1.8.8 and should be
considered experimental on other versions.

1. Create a version using the JSON snippet below as a template.
2. Place the bypass JAR in the correct subdirectory in `libraries`.
3. Start the game and join a server.

```
{
  "inheritsFrom": "<version>",
  "id": "<version>-bypass",
  "time": "2015-07-27T11:31:28+01:00",
  "releaseTime": "2015-07-27T11:31:28+01:00",
  "type": "release",
  "minecraftArguments": "--username ${auth_player_name} --version ${version_name} --gameDir ${game_directory} --assetsDir ${assets_root} --assetIndex ${assets_index_name} --uuid ${auth_uuid} --accessToken ${auth_access_token} --userProperties ${user_properties} --userType ${user_type}  --tweakClass xyz.smirking.blacklist.BlacklistTweaker",
  "libraries": [
    {
      "name": "xyz.smirking:Bypass:1.0-SNAPSHOT"
    },
    {
      "name": "net.minecraft:launchwrapper:1.7"
    }
  ],
  "mainClass": "net.minecraft.launchwrapper.Launch",
  "minimumLauncherVersion": 14,
  "assets": "<major>.<minor>"
}
```

## Note

If, by chance, anybody from Mojang happens to read this and wishes for it to be taken down then please, reach out via
a Tweet or DM to [@charries96](https://twitter.com/charries96) and it will be done.
