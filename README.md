## Node Traverser

This is a Java program that does the following:

* It fetches the XML representation of the exemplary ‘invoice approval’ BPMN diagram depicted above from a remote server.
* It parses the XML into a traversable data structure.
* It finds one possible path on the graph between a given start node and a given end node.
* It prints out the IDs of all nodes on the found path to System.out.

### Prerequisites:

* Java 11+
* Gradle 6.3.0+

Hint: [sdkman](https://sdkman.io/) tool might be helpful for managing parallel versions of multiple SDKs on most Unix based systems.

### Run the application

```shell script
./gradlew clean build && java -jar build/libs/*.jar approveInvoice invoiceProcessed
```

#### Sample Output: 

```
ledion.spaho@Ledions-MBP diagram-traverser % ./gradlew clean build && java -jar build/libs/*.jar approveInvoice invoiceProcessed

BUILD SUCCESSFUL in 2s
4 actionable tasks: 4 executed

2020-07-10 15:31:33.911 [main] INFO DiagramTraverserApplication - STARTING THE APPLICATION
2020-07-10 15:31:34.856  INFO 91033 --- [main] o.c.b.d.DiagramTraverserApplication      : Starting DiagramTraverserApplication on Ledions-MBP with PID 91033 (/Users/ledion.spaho/Desktop/diagram-traverser/build/libs/diagram-traverser-0.1.0-SNAPSHOT.jar started by ledion.spaho in /Users/ledion.spaho/Desktop/diagram-traverser)
2020-07-10 15:31:34.858  INFO 91033 --- [main] o.c.b.d.DiagramTraverserApplication      : No active profile set, falling back to default profiles: default
2020-07-10 15:31:35.924  INFO 91033 --- [main] o.c.b.d.DiagramTraverserApplication      : Started DiagramTraverserApplication in 1.802 seconds (JVM running for 2.438)
2020-07-10 15:31:35.925  INFO 91033 --- [main] o.c.b.d.DiagramTraverserCLIRunner        : EXECUTING : command line runner with args: [approveInvoice, invoiceProcessed]
2020-07-10 15:31:35.926  INFO 91033 --- [main] o.c.b.d.xmlfetcher.XmlFetcher            : Fetching xml from https://n35ro2ic4d.execute-api.eu-central-1.amazonaws.com/prod/engine-rest/process-definition/key/invoice/xml
2020-07-10 15:31:37.081  INFO 91033 --- [main] o.c.b.d.core.DiagramTraverserService     : Reading BPMN model
2020-07-10 15:31:37.588  INFO 91033 --- [main] o.c.b.d.core.GraphFactory                : Create a traversable data structure from the parsed BPMN model.
2020-07-10 15:31:37.604  INFO 91033 --- [main] o.c.b.d.core.GraphPathService            : Finding a GraphPath from approveInvoice to invoiceProcessed using RANDOM algorithm.
2020-07-10 15:31:37.614  INFO 91033 --- [main] o.c.b.d.resultprinter.ConsoleDataOutput  : The path from approveInvoice to invoiceProcessed is: [approveInvoice, invoice_approved, prepareBankTransfer, ServiceTask_1, invoiceProcessed]
2020-07-10 15:31:37.615  INFO 91033 --- [main] o.c.b.d.DiagramTraverserApplication      : APPLICATION FINISHED
```

#### NOTES

The `application-default.yml` file contains the configuration. Feel free to change the url of the xml bpmn model, and the algorithm to find a simple path between 2 nodes of a graph.

