#!bin/sh

cd $(dirname $0)/..
[ -d build/ ] || mkdir build/
cp -r src/ressources/ build/
javac src/*/*.java -d build/
