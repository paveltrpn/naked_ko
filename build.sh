#!/bin/bash

SOURCE_DIR="src"
BUILD_ARTIFACTS_DIR="out"
JVM_TARGET=24

cleanBuildArtifacts() {
    echo -e "clean...\n"
    rm -r $BUILD_ARTIFACTS_DIR
}
if [ ! -d "$BUILD_ARTIFACTS_DIR" ]; then
  echo "$BUILD_ARTIFACTS_DIR does not exist. Create one..."
  mkdir $BUILD_ARTIFACTS_DIR
fi

while [[ $# -gt 0 ]]; do
  case $1 in
    -r|--run)
      echo -e "run...\n"
      java -cp $BUILD_ARTIFACTS_DIR MainKt
      shift # past argument
      shift # past value
      ;;
    -c|--compile)
      echo -e "compile... jvm target=$JVM_TARGET\n\n"
      kotlinc -jvm-target $JVM_TARGET $SOURCE_DIR/Main.kt -d $BUILD_ARTIFACTS_DIR -verbose
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

