plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "developbeginner"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // Spring Boot Starter Test 3.1.5
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.5")
    // MariaDB Java Client 3.2.0
    implementation("org.mariadb.jdbc:mariadb-java-client:3.2.0")
    // H2 Database Engine 2.2.224
    testImplementation("com.h2database:h2:2.2.224")
    // MyBatis Spring Boot Starter 3.0.2
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
