#!/bin/sh

cd $(dirname $0)/..
sh scripts/compile.sh
[ -d dist ] || mkdir dist/

nameArchive="Mystic_Robot.jar"
jar cfe $nameArchive  src.gui.Main build/ src/
mv $nameArchive dist/
