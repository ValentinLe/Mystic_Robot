#!bin/sh

cd $(dirname $0)/..
[ -d build/ ] || mkdir build/
javac src/*/*.java -d build/
