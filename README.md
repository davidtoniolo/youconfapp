youconfapp
==========

The Maven parent module. youconf!app is a hierarchical multi-module Maven project.

Travis CI build status (latest): [![Build Status](https://travis-ci.org/davidtoniolo/youconfapp.png?branch=master)](https://travis-ci.org/davidtoniolo/youconfapp)

Author: David Toniolo <youconfapp@unittestcloud.com>


youconfapp_app
==============

The Android app (.apk) as Maven submodule.


youconfapp_localization
=======================

An Android Library Project as a Maven submodule.

Just a kind of resource storage module for localization of strings, drawables, etc.


Testing & Continuous Integration
================================

[JUnit](http://junit.org) & [Robolectric](https://github.com/robolectric/robolectric) is used for unit/integration testing.

[Travis CI](https://travis-ci.org/davidtoniolo/youconfapp) is watching youconf!app.

Run unit tests:

	mvn test
	
Run integration tests & unit tests:

	mvn integration-test

Install, deploy & run
=====================

Build youconfapp (parent module):

	mvn install

Deploy and run youconfapp_app (app submodule):

	mvn android:deploy android:run