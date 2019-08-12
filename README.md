# quarkus-words-api

This is a simple Quarkus RESTful API designed to extend the radanalytics.io [GrafZahl](https://radanalytics.io/applications/grafzahl) demo. 

It takes data from the GrafZahl Flask app via the `data` endpoint, and converts this into a tree-map structure that can be integrated with a ReactJSXHighcharts graph.

# deployment

Currently this app requires the OpenJDK 11 source-to-image builder for OpenShift. You can find a guide to using the `openjdk-11-rhel8` image on the [Quarkus github](https://github.com/quarkusio/quarkus-images/issues/13#issuecomment-497160304)
