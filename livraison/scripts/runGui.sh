#!bin/sh

cd $(dirname $0)
if sh compile.sh
then
cd ../build/
java gui.Main
fi
