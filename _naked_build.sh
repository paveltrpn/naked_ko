#!/bin/bash

BUILD_ARTIFACTS_DIR="out"
JVM_TARGET=21
KOTLIN_VERSION="2.2"
MAIN_PACKAGE="naked"

shopt -s nullglob

TEST_SOURCE=(
  test/src/main/kotlin/Main.kt
  test/src/main/kotlin/Additional.kt
)

FIBO_SOURCE=(
  fibo/src/main/kotlin/fibo/fibo.kt
)

MODULE_PKG_SOURCE=(
  modules/src/main/kotlin/first/Module.kt
  modules/src/main/kotlin/algebra/mtrx2.kt
)

cleanBuildArtifacts() {
    echo -e "=== clean..."
    rm -r $BUILD_ARTIFACTS_DIR
}

if [ ! -d "$BUILD_ARTIFACTS_DIR" ]; then
  echo -e "=== $BUILD_ARTIFACTS_DIR does not exist. Create one...\n"
  mkdir $BUILD_ARTIFACTS_DIR
fi

build() {
    echo -e "=== build..."
    echo -e "jvm target=$JVM_TARGET"
    echo -e "kotlin version=$KOTLIN_VERSION"
    echo -e "sources:"
    echo -e "${TEST_SOURCE[@]}"
    echo -e "${FIBO_SOURCE[@]}"
    echo -e "${MODULE_PKG_SOURCE[@]}"

    kotlinc \
        -jvm-target $JVM_TARGET \
        -language-version $KOTLIN_VERSION \
        -include-runtime \
        "${TEST_SOURCE[@]}" \
        "${FIBO_SOURCE[@]}" \
        "${MODULE_PKG_SOURCE[@]}" \
        -d $BUILD_ARTIFACTS_DIR/a.jar -verbose
}

run() {
    echo -e "=== run...\n"
    java -cp $BUILD_ARTIFACTS_DIR/a.jar naked.MainKt
    java -cp $BUILD_ARTIFACTS_DIR/a.jar fibo.FiboKt
}

while getopts ':-:brac' VAL ; do
  case $VAL in
    b )
      build
      ;;
#    r ) OFILE="$OPTARG" ;;
    r )
      run
      ;;
    a )
      build
      run
      ;;
    c )
      cleanBuildArtifacts
      ;;
    - )
      case $OPTARG in
        build )
          build
          ;;
        run )
          run
          ;;
        all )
          build
          run
          ;;
        clean )
          cleanBuildArtifacts
          ;;
        * )
          echo "unknown long argument: $OPTARG"
          exit
          ;;
      esac
      ;;
  #--------------------------------------------------------
    : )
      echo "error: no argument supplied"
      ;;
    * )
      echo "error: unknown option $OPTARG"
      echo " valid options are: aov"
      ;;
  esac
done
shift $((OPTIND -1))
