#!/bin/sh

cd $(dirname $0)/..
sh scripts/compile.sh
[ -d jar ] || mkdir jar
[ -d jar/dist ] || mkdir jar/dist

nameArchive="Archive.jar"
jar cf $nameArchive build/ src/ ressources/
mv $nameArchive jar/dist/
