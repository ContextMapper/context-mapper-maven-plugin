# context-mapper-maven-plugin
Maven plugin for Context-Mapper generator.

Usage:

``` xml
<plugin>
  <groupId>org.contextmapper</groupId>
  <artifactId>context-mapper-maven-plugin</artifactId>
  <executions>
    <execution>
      <id>create-context-map</id>
      <goals>
        <goal>generate-context-map</goal>
      </goals>
      <configuration>
        <skip>false</skip>
        <inputFile>src/main/cml/Insurance-Example-Model.cml</inputFile>
        <outputDirectory>target/generated-sources/cml</outputDirectory>        
      </configuration>
    </execution>
    <execution>
      <id>create-plant-uml</id>
      <goals>
        <goal>generate-plant-uml</goal>
      </goals>
      <configuration>
        <skip>false</skip>
        <inputFile>src/main/cml/Insurance-Example-Model.cml</inputFile>
        <outputDirectory>target/generated-sources/cml</outputDirectory>        
      </configuration>
    </execution>
    <execution>
      <id>custom-generation</id>
      <goals>
        <goal>generate-template</goal>
      </goals>
      <configuration>
        <skip>false</skip>
        <templateFile>src/main/cml/template.ftl</templateFile>
        <inputFile>src/main/cml/Insurance-Example-Model.cml</inputFile>
        <outputDirectory>target/generated-sources/cml</outputDirectory>
        <outputFile>list-of-bcs.md</outputFile>
      </configuration>
    </execution>
</execution>
</plugin>

```
