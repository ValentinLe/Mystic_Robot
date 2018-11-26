#!/bin/sh

cd $(dirname $0)
if sh compile.sh
then
    cd ..
    cp -r src/ressources/ build/
    cd build/
    java $@
fi
