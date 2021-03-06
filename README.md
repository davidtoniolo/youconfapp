youconfapp
==========

The Maven parent module. youconf!app is a hierarchical multi-module Maven project and comes with the 
[Google Play Services Client Library](http://developer.android.com/google/play-services/index.html) included.

Travis CI build status (latest): [![Build Status](https://travis-ci.org/davidtoniolo/youconfapp.png?branch=master)](https://travis-ci.org/davidtoniolo/youconfapp)

Author: David Toniolo <youconfapp@unittestcloud.com>

Partner projects: [youconfweb](https://github.com/davidtoniolo/youconfweb) & [youconfws](https://github.com/davidtoniolo/youconfws)


/google_play_services_lib
=========================

This is the official Google Play Services Library.

It is integrated to use [GoogleMaps Android API v2](https://developers.google.com/maps/documentation/android).
The pom.xml is just added to build it with Maven, it is not part of the offical sources.


/youconfapp_app
===============

The Android app (.apk) as Maven submodule.


/youconfapp_localization
========================

An Android Library Project as a Maven submodule.

Just a kind of resource storage module for localization of strings, drawables, etc.


/youconfapp_menu
================

An Android Library Project as a Maven submodule.

Menus in XML format and an interface API activities have to implement when using a menu.


/youconfapp_utils
=================

An Android Library Project as a Maven submodule.

API of helper classes like factories, converters, etc.


Prepare, install, deploy & run
==============================

youconf!app comes with the original Google Play Service Library as submodule google_play_services_lib, which is
a regular APKLib project. You need to import this APKLib project in your IDE as a single project and you have to import
the JAR file into your local Maven repository before you run mvn install.

It is just the same procedure which you would have to do, if you are using your local google-play-services_lib project,
see official documentation to [setup Google Play Service Library](http://developer.android.com/google/play-services/setup.html).

Prepare google_play_services_lib:

	cd <your-local-path>/youconfapp/google_play_services_lib
	mvn org.apache.maven.plugins:maven-install-plugin:2.4:install-file -DgroupId=com.google.android.gms -DartifactId=google-play-services -Dversion=3.2.65 -Dpackaging=jar -Dfile=libs/google-play-services.jar

WARNING
 
You should use your local Google Play Services Library project, because this one is primarly shipped for Travis CI Build Platform.

To use your local google-play-services_lib project add it as Android Lib project to the youconfapp_app project.


Build youconfapp (parent module):

	cd <your-local-path>/youconfapp
	mvn install

Deploy and run youconfapp_app (app submodule):

	cd <your-local-path>/youconfapp/youconfapp_app
	mvn android:deploy android:run


Testing & Continuous Integration
================================

[Travis CI](https://travis-ci.org/davidtoniolo/youconfapp) is watching youconf!app.

[JUnit](http://junit.org)
, [mockito](https://code.google.com/p/mockito)
, [PowerMock](http://code.google.com/p/powermock)
, [mockwebserver](https://code.google.com/p/mockwebserver)
, [Robolectric](https://github.com/robolectric/robolectric)
& [Cobertura](http://cobertura.github.io/cobertura) are used for unit and integration testing.

youconf!app separates unit tests from integration tests with the 2 Maven plugins:

* [maven-failsafe-plugin](http://maven.apache.org/surefire/maven-failsafe-plugin)
* [build-helper-maven-plugin](http://mojo.codehaus.org/build-helper-maven-plugin)


Run unit tests:

	cd <your-local-path>/youconfapp
	mvn test

Run integration tests & unit tests:

	cd <your-local-path>/youconfapp
	mvn integration-test


Troubleshooting
===============

1. GoogleMaps doesn't load

Usage of GoogleMaps requires a Google Developer Account with enabled Android GoogleMaps API v2. In the Google Developer console an API key
has to be created and the fingerprint-keystore + package name of each app must be added there. So, if you have built the youconfapp_app you 
did not use a keystore which is added to the API key configured in the AndroidManifest.xml, because you can't have such a keystore.

So the MapActivity which uses the GoogleMaps can't display the map until you changed these settings in your own Google Developer console.

A great tutorial is here: http://www.vogella.com/articles/AndroidGoogleMaps/article.html#maps_key


Open Source Software
====================

This project uses the following great libraries:

[Apache Maven](http://maven.apache.org)
[Android SDK](http://developer.android.com/sdk/index.html)
[Google Play Services Lib](http://developer.android.com/google/play-services/index.html)
[GoogleMaps Android API v2](https://developers.google.com/maps/documentation/android)
[JUnit](http://junit.org)
[mockito](https://code.google.com/p/mockito)
[PowerMock](http://code.google.com/p/powermock)
[mockwebserver](https://code.google.com/p/mockwebserver)
[Robolectric](https://github.com/robolectric/robolectric)
[Cobertura Code Coverage](http://cobertura.github.io/cobertura)
[STAND for Android](http://stand.spree.de)
[ActionBarSherlock](https://github.com/JakeWharton/ActionBarSherlock)
[SQLite](http://www.sqlite.org)
[OrmLite](http://ormlite.com)
[Jackson](http://jackson.codehaus.org)
[RoboSpice](https://github.com/octo-online/robospice)