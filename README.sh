#! /usr/bin/env sh

cat README.md \
    | sed '
        s_https://github.com/un-nubivago/_https://modrinth.com/mod/_;
        s_https://github.com/TechReborn/TechReborn_https://www.curseforge.com/minecraft/mc-mods/techreborn_'
