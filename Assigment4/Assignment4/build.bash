#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Pipe.java Json.java Mario.java
java Game
