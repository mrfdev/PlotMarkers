#!/bin/sh
set -eu

case "${1:-25}" in
    25) JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-25.0.4.1.jdk/Contents/Home ;;
    26) JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-26.0.2.1.jdk/Contents/Home ;;
    *) echo "Usage: $0 [25|26] [server-directory]" >&2; exit 2 ;;
esac
export JAVA_HOME
export PATH="$JAVA_HOME/bin:$PATH"

if [ ! -x "$JAVA_HOME/bin/java" ]; then
    echo "Required test JDK is missing: $JAVA_HOME" >&2
    exit 1
fi

cd "$(dirname "$0")/.."
cd "${2:-servers/Paper-26.2}"
java -version
exec java -Xms512M -Xmx2G -Dfile.encoding=UTF-8 -Djava.awt.headless=true \
    --enable-native-access=ALL-UNNAMED --sun-misc-unsafe-memory-access=allow \
    -Dterminal.ansi=false -jar Paper-26.2.jar --nogui
