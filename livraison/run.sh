#!bin/sh

cd $(dirname $0)
if sh scripts/compile.sh
then
cd build/
java game.Main
fi
