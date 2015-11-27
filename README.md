# OSA Release Bundle Package

This small package is used to build OSA new releases.

OSA uses a maven "flat" multi-modules structure. This structure is known to cause problm with some of the maven plugins, and in particular the maven-release plugin. A classic work-around is to use a small pom file in the parent directory of all
modules, that will provide the hierarchical structure required by the maven release plugin.



## How to use this package


Check out this package in the *parent* directory and all other OSA packages (including osa-root and maven-config) as children directories within this parent.
