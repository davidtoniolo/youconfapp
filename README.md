youconfapp
==========

The Maven parent module. youconf!app is a hierarchical multi-module Maven project and comes with the Google Play Services Library included.

Travis CI build status (latest): [![Build Status](https://travis-ci.org/davidtoniolo/youconfapp.png?branch=master)](https://travis-ci.org/davidtoniolo/youconfapp)

Author: David Toniolo <youconfapp@unittestcloud.com>


/google_play_services_lib
=========================

This is the official Google Play Services Library.

It is integrated to use GoogleMaps API v2 for Android. The pom.xml is not part of the original project.


/youconfapp_app
===============

The Android app (.apk) as Maven submodule.


/youconfapp_localization
========================

An Android Library Project as a Maven submodule.

Just a kind of resource storage module for localization of strings, drawables, etc.


Prepare, install, deploy & run
==============================

youconf!app comes with the original Google Play Service Library as submodule google_play_services_lib, which is
a regular APKLib project. You need to import this APKLib project in your IDE as an single project and you have to import
the JAR file into your local Maven repository before you run mvn install.
It is just the same procedure which you would have to do, if you are using your local google-play-services_lib project.

Prepare google_play_services_lib:

	cd <your-local-path>/youconfapp/google_play_services_lib
	mvn org.apache.maven.plugins:maven-install-plugin:2.4:install-file -DgroupId=com.google.android.gms -DartifactId=google-play-services-jar -Dversion=7 -Dpackaging=jar -Dfile=libs/google-play-services.jar

Build youconfapp (parent module):

	mvn install

Deploy and run youconfapp_app (app submodule):

	mvn android:deploy android:run


Testing & Continuous Integration
================================

[Travis CI](https://travis-ci.org/davidtoniolo/youconfapp) is watching youconf!app.

[JUnit](http://junit.org) & [Robolectric](https://github.com/robolectric/robolectric) is used for unit/integration testing.

youconf!app separates unit tests from integration tests with the 2 Maven plugins:

* [maven-surefire-plugin](http://maven.apache.org/surefire/maven-surefire-plugin)
* [build-helper-maven-plugin](http://mojo.codehaus.org/build-helper-maven-plugin)


Run unit tests:

	mvn test
	
Run integration tests & unit tests:

	mvn integration-test

	