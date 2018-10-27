#!bin/sh

cd $(dirname $0)/..
mkdir build/ || rm -rf build/
javac src/*/*.java -d build/
