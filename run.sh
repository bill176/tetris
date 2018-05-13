#!/bin/bash

cd ./game

javac *.java

jar -cmf manifest.txt tetris.jar *.class

mv tetris.jar ..

