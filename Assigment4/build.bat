@REM #!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Pipe.java
java Game
