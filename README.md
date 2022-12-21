![ServerJars](https://serverjars.com/img/icon_small.png)

# ServerJars-API
The Java API for ServerJars

**Required Libraries:**

- [Gson](https://github.com/google/gson)

**Maven:**
```xml
<repository>
    <id>public</id>
    <url>https://repo.songoda.com/repository/public/</url>
</repository>
```
 Artifact Information:
```xml
<dependency>
    <groupId>com.serverjars</groupId>
    <artifactId>ServerJarsAPI</artifactId>
    <version>1</version>
    <scope>provided</scope>
</dependency>
 ```

**Code Examples:**

**Fetching the latest Jar:**
```java
LatestResponse latestResponse = new LatestRequest("THE JAR TYPE").send();
if (latestResponse.isSuccess()) {
    JarDetails jarDetails = latestResponse.getLatestJar();
    System.out.println(jarDetails.toString());
} else {
    System.out.println(
        latestResponse.getErrorTitle() + ": " + 
        latestResponse.getErrorMessage()
    );
}
```

**Fetching the all Jars:**
```java
AllResponse latestResponse = new AllRequest("spigot").send();
if (latestResponse.isSuccess()) {
    List<JarDetails> jars = latestResponse.getJars();
    System.out.println("{");
    for (JarDetails jar : jars) {
        System.out.println("  " + jar);
    }
    System.out.println("}");
} else {
    System.out.println(
        latestResponse.getErrorTitle() + ": " +
        latestResponse.getErrorMessage()
    );
}
```

**Downloading Jars:**
```java
Response jarResponse = new JarRequest("spigot", new File("exampleserver.jar")).send();
if(jarResponse.isSuccess()) {
    System.out.println("Download Success!");
} else {
    System.out.println(
        jarResponse.getErrorTitle() + ": " + 
        jarResponse.getErrorMessage()
    );
}
```

**Fetching jar types:**
```java
// Fetching all types 
TypesResponse typesResponse = new TypesRequest().send();
if (typesResponse.isSuccess()) {
    if(!typesResponse.isChildren()) {
        Map<String, List<String>> typesMap = typesResponse.getAllTypes();
        System.out.println(typesMap);
    }
} else {
    System.out.println(
        typesResponse.getErrorTitle() + ": " + 
        typesResponse.getErrorMessage()
    );
}

// Fetching all types in a category
typesResponse = new TypesRequest("servers").send();
if (typesResponse.isSuccess()) {
    if (typesResponse.isChildren()) {
        List<String> children = typesResponse.getRequestedChildren();
        System.out.println(children);
    }
} else {
    System.out.println(
        typesResponse.getErrorTitle() + ": " + 
        typesResponse.getErrorMessage()
    );
}
```
