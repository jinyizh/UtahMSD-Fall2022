#!/bin/sh
set -e
if test "$CONFIGURATION" = "Debug"; then :
  cd /Users/jinyizhou/myGithubRepo/CS6010/DrawAnythingWithSFML/xcode
  make -f /Users/jinyizhou/myGithubRepo/CS6010/DrawAnythingWithSFML/xcode/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "Release"; then :
  cd /Users/jinyizhou/myGithubRepo/CS6010/DrawAnythingWithSFML/xcode
  make -f /Users/jinyizhou/myGithubRepo/CS6010/DrawAnythingWithSFML/xcode/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "MinSizeRel"; then :
  cd /Users/jinyizhou/myGithubRepo/CS6010/DrawAnythingWithSFML/xcode
  make -f /Users/jinyizhou/myGithubRepo/CS6010/DrawAnythingWithSFML/xcode/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "RelWithDebInfo"; then :
  cd /Users/jinyizhou/myGithubRepo/CS6010/DrawAnythingWithSFML/xcode
  make -f /Users/jinyizhou/myGithubRepo/CS6010/DrawAnythingWithSFML/xcode/CMakeScripts/ReRunCMake.make
fi

