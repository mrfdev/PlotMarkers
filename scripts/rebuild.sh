#!/bin/sh
set -eu

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.0.4.1.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"

if [ ! -x "$JAVA_HOME/bin/java" ]; then
    echo "Required build JDK is missing: $JAVA_HOME" >&2
    exit 1
fi

cd "$(dirname "$0")/.."
java -version
exec ./gradlew --no-daemon clean build docsCheck "$@"
