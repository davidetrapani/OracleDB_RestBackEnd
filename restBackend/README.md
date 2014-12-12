## Intro


## Usage
Download the Oracle ojdbc6.jar from <a href="http://www.oracle.com/technetwork/database/features/jdbc/index-091264.html">this link</a>

To install your Oracle jdbc driver, issue following command :
```
mvn install:install-file -Dfile={Path/to/your/ojdbc.jar} -DgroupId=com.oracle 
-DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar
```

Then, `cd` to your project and 
```
mvn clean && mvn install
```
## Credits
Thanks to MKyong and his <a href="http://www.mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/">guide</a>
