#!/bin/sh

cd $(dirname $0)/..
sh scripts/compile.sh
[ -d dist ] || mkdir dist/

nameArchive="Mystic_Robot.jar"
jar cf $nameArchive build/ src/ ressources/
mv $nameArchive dist/
