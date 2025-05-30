#!/bin/bash
# Script to download & install Kotlin's command line compiler

if [[ $# == 0 ]]; then
    echo "Usage: $0 <install-dir>"
    exit 1
fi

curl -L -o kotlinc.zip https://github.com/JetBrains/kotlin/releases/download/v2.1.21/kotlin-compiler-2.1.21.zip
unzip -DD kotlinc.zip -d $1
rm kotlinc.zip

echo
echo "Remember to modify PATH to include $1/kotlinc/bin"
