#!/bin/sh

cd $(dirname $0)/..
sh scripts/compile.sh
[ -d dist ] || mkdir dist/

nameArchive="Mystic_Robot.jar"
jar cvfe $nameArchive  build.gui.Main build/ src/
mv $nameArchive dist/
