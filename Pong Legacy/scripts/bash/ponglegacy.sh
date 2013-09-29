#! /bin/sh
# This script sets up the environment variables under *nix based machines
# Change the environment variables to suit your environment.

export SVN_HOME="/home/kota/IdeaProjects/PongProto/"
export JDK_HOME="/usr/lib/jvm/java-6-sun/"
export ANT_HOME="/usr/"
export JUNIT_HOME="/"

$ANT_HOME/bin/ant -buildfile $SVN_HOME/build.xml $1 $2 $3 $4 $5 $6 $7 $8
