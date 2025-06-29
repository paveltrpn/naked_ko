#!/bin/bash

SOURCE_DIR="src"
BUILD_ARTIFACTS_DIR="out"

while [[ $# -gt 0 ]]; do
  case $1 in
    -r|--run)
      java -cp $BUILD_ARTIFACTS_DIR MainKt
      shift # past argument
      shift # past value
      ;;
    -c|--compile)
      kotlinc $SOURCE_DIR/Main.kt -d $BUILD_ARTIFACTS_DIR -verbose
      shift # past argument
      shift # past value
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
