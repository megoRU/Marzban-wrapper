# Marzban-wrapper

An API wrapper for [Marzban](https://github.com/Gozargah/Marzban) written in Java by @megoRU

### Maven

https://jitpack.io/#megoRU/Marzban-wrapper

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
   <groupId>com.github.megoRU</groupId>
   <artifactId>Marzban-wrapper</artifactId>
   <version>1.0.0</version>
</dependency>
```

## Examples

### Delete User
```java
public class Main {
    public static void main(String[] args) throws IOException, UnsuccessfulHttpException {
        TokenRequest tokenRequest = new TokenRequest("login", "password");

        MarzbanAPI marzbanAPI = new MarzbanAPIImpl.Builder()
                .setTokenRequest(tokenRequest)
                .setHost("https://example.com:8000")
                .build();

        DeleteUserResponse deleteUser = marzbanAPI.deleteUser("user");
        System.out.println(deleteUser.isSuccess()); //boolean
    }
}
```

### Add User
```java
public class Main {
    public static void main(String[] args) throws IOException, UnsuccessfulHttpException {
        TokenRequest tokenRequest = new TokenRequest("login", "password");

        MarzbanAPI marzbanAPI = new MarzbanAPIImpl.Builder()
                .setTokenRequest(tokenRequest)
                .setHost("https://example.com:8000")
                .build();

        List<String> vlessTcpRealityStatus = List.of(UserRequest.Protocol.VLESS_TCP_REALITY.getValue());
        UserRequest.Vless vless = new UserRequest.Vless();
        UserRequest.Proxies proxies = UserRequest.Proxies.builder().vless(vless).build();
        UserRequest.Inbounds inbounds = UserRequest.Inbounds.builder().vless(vlessTcpRealityStatus).build();

        UserRequest userRequest = new UserRequest("user", proxies, inbounds);

        UserResponse userResponse = marzbanAPI.addUser(userRequest); //Object UserResponse
        System.out.println(userResponse.getLinks()); // [links]
    }
}
```

### Get User
```java
public class Main {
    public static void main(String[] args) throws IOException, UnsuccessfulHttpException {
        TokenRequest tokenRequest = new TokenRequest("login", "password");

        MarzbanAPI marzbanAPI = new MarzbanAPIImpl.Builder()
                .setTokenRequest(tokenRequest)
                .setHost("https://example.com:8000")
                .build();

        UserResponse userResponse = marzbanAPI.getUser("user"); //Object UserResponse
        System.out.println(userResponse.getLinks()); // [links]
    }
}
```