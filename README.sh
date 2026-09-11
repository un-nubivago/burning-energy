#! /usr/bin/env sh

cat README.md \
    | sed '
        s_https://github.com/TechReborn/TechReborn_https://www.curseforge.com/minecraft/mc-mods/techreborn_;
        s_https://github.com/un-nubivago/_https://modrinth.com/mod/_;
        s_img/Showcase\_1\.png_https://cdn.modrinth.com/data/ozVPxKY5/images/8a588691c4f840b3abbceacf6b8efc8b42e9b3b8\_350.webp_'
