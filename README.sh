#! /bin/sh

cat README.md \
    | sed '
        s_https://github.com/un-nubivago/burning_https://modrinth.com/mod/burning_'
