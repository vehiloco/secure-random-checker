#!/usr/bin/env bash

set -e

# Environment
export JSR308=$(cd $(dirname "$0")/../../ && pwd)
export CF=${JSR308}/checker-framework
export JAVAC=${CF}/checker/bin/javac
export SRCHECKER=$(cd $(dirname "$0")/../ && pwd)

# Dependencies
export CLASSPATH=${SRCHECKER}/build/classes/java/main:${SRCHECKER}/build/resources/main:\
${SRCHECKER}/build/libs/secure-random-checker.jar

# Command
DEBUG=""
CHECKER="org.checkerframework.checker.securerandom.SecureRandomChecker"

declare -a ARGS
for i in "$@" ; do
    if [[ ${i} == "-d" ]] ; then
        echo "Typecheck using debug mode. Listening at port 5005. Waiting for connection...."
        DEBUG="-J-Xdebug -J-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
        continue
    fi
    ARGS[${#ARGS[@]}]="$i"
done

cmd=""

if [[ "$DEBUG" == "" ]]; then
    cmd="$JAVAC -cp "${CLASSPATH}" -processor "${CHECKER}" "${ARGS[@]}""
else
    cmd="$JAVAC "${DEBUG}" -cp "${CLASSPATH}" -processor "${CHECKER}" "${ARGS[@]}""
fi

eval "${cmd}"
