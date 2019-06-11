                                                   Installation Serenity

1. You should add Serenity annotation @RunWith(SerenityRunner.class) above !test! class.
   It let you run the JUnit test using the Serenity test runner.

2. Add @Steps annotation marks a Serenity step library, above the instance test page class.

3. The unit test is composed of logical steps, each of which will appear in the reports.
   That`s why you should place annotations @Step above  methods you use of test page class.
   The @Step annotation marks this as a method that will be recorded and will appear in the test report.

4. You should add several dependencies into pom-file:
   a)<!--for using Serenity-->:
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-rest-assured</artifactId>
            <version>2.0.50</version>
        </dependency>
	b)<!--To enable default Serenity logging-->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.26</version>
            <scope>test</scope>
        </dependency>
	c)<!--for SerenityRunner-->
	    <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-junit</artifactId>
            <version>2.0.50</version>
		
5. Add plugin section into pom-file in section build (section 'build' use on assemble stage of the project!)
   for ability assembling Serenity html-report:
    <build>
        <plugins>
            <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>2.0.50</version>
				  <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
	
6. Add maven-surefire-plugin for ability to create Serenity-report on "verify" stage of Life Cycle. There are also included 2 options: 
testFailureIgnore = true and skip=false. It let you ignored failed tests and continue to assemble the project anyway.

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${surefire.version}</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore> <!--for ignoring failed tests-->
                    <skip>false</skip>
                </configuration>
            </plugin>
	
6. Run Junit-tests

7. Сall command "mvn serenity:aggregate" into console after your build is done.

8. Run index.html (placed in folder <project directory>../target/site/serenity) and you will see Serenity report.