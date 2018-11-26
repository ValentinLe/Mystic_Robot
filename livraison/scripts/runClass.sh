#!/bin/sh

cd $(dirname $0)
if sh compile.sh
then
    cd ..
    cp src/config src/texture build/
    cd build/
    java $@
fi
