#PITEST
### official Documentation 
PIT is a mutation framework test code for java.
https://pitest.org/quickstart/maven/

we can implement PIT by adding a maven plugin dependency to our pom.xm

````xml
<plugin>
    <groupId>org.pitest</groupId>
    <artifactId>pitest-maven</artifactId>
    <version>1.6.9</version>
    <dependencies> // junit 5 support add dependency
        <dependency>
            <groupId>org.pitest</groupId>
            <artifactId>pitest-junit5-plugin</artifactId>
            <version>0.14</version>
        </dependency>
    </dependencies>
</plugin>
````

Before running any mvn command, we need to add JAVA_HOME and MAVEN_HOME variable 

JAVA_HOME should be configured first
 
### to see how add JAVA_HOME variable on Windows 10
https://mkyong.com/java/how-to-set-java_home-on-windows-10/

### to see how add MAVEN_HOME variable on Windows 10
https://mkyong.com/maven/how-to-install-maven-in-windows/

### the next step is a coverage run for all test 

right-click on your java test folder and choose -> 'Run all test with coverage'

### the last step is about running PITEST 

you can run PITEST using plugin maven UI or line command type 

    mvn org.pitest:pitest-maven:mutationCoverage

we can also configure pitest to limit which package to cover and more configuration that we can find on  http://pitest.org/quickstart/maven/

PITEST is also available as an intellij plug-in 

we can find it by a simple search on plug-in section.
