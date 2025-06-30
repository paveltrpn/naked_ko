#!/bin/bash

BUILD_ARTIFACTS_DIR="out"
JVM_TARGET=21
MAIN_PACKAGE="naked"

shopt -s nullglob

MAIN_SOURCE='test/src/main/kotlin/Main.kt test/src/main/kotlin/Additional.kt'
MODULE_PKG_SOURCE='modules/src/main/kotlin/first/Module.kt'

cleanBuildArtifacts() {
    echo -e "=== clean..."
    rm -r $BUILD_ARTIFACTS_DIR
}

if [ ! -d "$BUILD_ARTIFACTS_DIR" ]; then
  echo -e "=== $BUILD_ARTIFACTS_DIR does not exist. Create one...\n"
  mkdir $BUILD_ARTIFACTS_DIR
fi

compile() {
    echo -e "=== compile... jvm target=$JVM_TARGET, sources:\n$MAIN_SOURCE\n$MODULE_PKG_SOURCE\n"
    kotlinc -jvm-target $JVM_TARGET \
        $MAIN_SOURCE \
        $MODULE_PKG_SOURCE \
        -d $BUILD_ARTIFACTS_DIR -verbose
}

run() {
    echo -e "=== run...\n"
    java -cp $BUILD_ARTIFACTS_DIR naked.MainKt
}

while [[ $# -gt 0 ]]; do
  case $1 in
    -r|--run)
      run

      shift # past argument
      shift # past value
      ;;
    -c|--compile)
      compile

      shift # past argument
      shift # past value
      ;;
    -a|--all)
      compile
      run

      shift # past argument
      shift # past value
      ;;
    --clean)
      cleanBuildArtifacts
      exit 1
      ;;
    -*|--*)
      echo "Unknown option $1"
      exit 1
      ;;
    *)
      POSITIONAL_ARGS+=("$1") # save positional arg
      shift # past argument
      ;;
  esac
done

