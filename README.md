nuxeo-security-policy-logger
============================


## About

This is a simple nuxeo plugin that defines a new `SecurityPolicy`.

This `SecurityPolicy` does no real check : it simply provides extended loggin about :

 - what is the context object
 - what permission is checked
 - what permissions are compatibles
 - who is the user
 - what are the user's groups

You may wany to activate LOG4J INFO level :

    <category name="org.nuxeo.security.logger">
      <priority value="INFO" />
    </category>

  
## Building / Install

Build : 

    mvn clean install

Deploy :

 - copy the jar into nxserver/bundles or nxserver/plugins
 - restart the server



