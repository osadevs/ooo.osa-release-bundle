#!/usr/bin/env zsh
##
## OSA uses 2 upstream branches:
##
## master branch: used for tracking latest snapshot releases
## releases branch: used for tracking release versions
##    Release tag of the form: vX.Y.Z
##
## any other branch: normally not tracked upstream, used for
##    local developemnt to be merged in master
##
## What happens when a new stable release is out:
## 1/ last changes are committed and pushed to master
## 2/ git commit + tag pre-release + push upstream to origin/master
## 2/ releases branch is checked out
## 3/ master is merged in release branch
## 4/ maven version is changed to stable (strip out "SNAPSHOT")
## 5/ a full maven build cycle is run to check that everything is ok
## 6/ site and artifact are deployed
## 7/ maven clean
## 8/ git commit + tag + push upstream to origin/releases
## 9/ master branch is checked out
## 10/ new release is merged in
## 11/ Release version number bumped up to new version-SNAPSHOT
## 12/ git commit + tag + push upstram to origin/master

# Perl's XPATH query parser 
XPATH=/usr/bin/xpath

MODULES=( ooo.osa-root
	  ooo.maven-config
	  ooo.engines
	  ooo.runtime.newdes.launcher.event
	  ooo.engines.newdes
	  ooo.runtime.newdes.logger
	  ooo.simapis
	  ooo.simapis.newdes
	  ooo.runtime.newdes
	  ooo.simapis.newdes.osalet
	  ooo.model.newdes
	  ooo.model.newdes.helloworld-event
	  ooo.model.newdes.helloworld-process
	  ooo.experiments.newdes
	  ooo.experiments.newdes.helloworld-event
	  ooo.experiments.newdes.helloworld-process
	)

function iterate () {
    for mod in "$MODULES[@]"
    do
	echo "module $mod:"
	cd $mod && ( $* || echo "failed." )
	cd ..
	echo "ok.\n"
    done
}

function create_branch () {
    branch=${1:-release}
    echo "creating branch: $branch"
    iterate_git checkout -b "$branch"
}


function delete_branch () {
    branch=${1:-release}
    echo "deleting branch: $branch"
    iterate git branch -D "$branch"
}

function checkout_branch () {
    branch=${1:-release}
    echo "switching to branch: $branch"
    iterate git checkout "$branch"
}

function tag_branch () {
    branch=${1:-release}
    tag=${2:-missing-$$}
    iterate_git tag 
}

function parent_version () {
    file=${1:-pom.xml}
    $XPATH $file '//parent/version' 2> /dev/null  | sed -E 's/<[^>]+>//g'
}

function parent_version () {
    file=${1:-pom.xml}
    $XPATH $file '//parent/version' 2> /dev/null  | sed -E 's/<[^>]+>//g'
}

function parent_base_version () {
    parent_version | cut -d- -f1
}

function iterate_parent_version () {
    iterate parent_version
}

if [[ $# -eq 0 ]] ; then
    checkout_branch
fi


function clone () {
    for mod in "$MODULES[@]"
    do
	echo "module $mod:"
	git clone "git@github.com:osadevs/$mod.git"
	echo "ok.\n"
    done
}

if [[ "$1" == "-c" ]] ; then
    # Run internal command
    shift
    $*
else # run extrnal
    iterate $*
fi
