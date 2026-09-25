#!/bin/sh
set -eu

if [ "$#" -gt 1 ]; then
    echo "Usage: $0 [server-directory]" >&2
    exit 2
fi
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-27.jdk/Contents/Home
export JAVA_HOME
export PATH="$JAVA_HOME/bin:$PATH"

if [ ! -x "$JAVA_HOME/bin/java" ]; then
    echo "Required test JDK is missing: $JAVA_HOME" >&2
    exit 1
fi

cd "$(dirname "$0")/.."
cd "${1:-servers/Paper-26.3}"
if [ ! -f Paper-26.3-41.jar ]; then
    echo "Required server jar is missing: $PWD/Paper-26.3-41.jar" >&2
    exit 1
fi
printf '%s  %s\n' \
    2b77166ee61886a9bc9ab33dc9e4847fa3538b36d9ba6e5f2fa7ed90973aa748 \
    Paper-26.3-41.jar | shasum -a 256 -c -
java -version
exec java -Xms512M -Xmx2G -Dfile.encoding=UTF-8 -Djava.awt.headless=true \
    --enable-native-access=ALL-UNNAMED --sun-misc-unsafe-memory-access=allow \
    -Dterminal.ansi=false -Dplotmarkers.test-session=plotmarkers-paper-26-3-032 \
    -jar Paper-26.3-41.jar --nogui
