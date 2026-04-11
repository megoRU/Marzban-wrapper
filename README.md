<h2 align="center" style="font-size: 1.2rem;">
  『 <a href="README.md">EN</a> •
  <a href="README_RU.md">RU</a> 』
</h2>

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
   <version>1.0.9</version>
</dependency>
```

## Examples

### Delete User
```java
public class Main {
    static void main(String[] args) throws IOException, UnsuccessfulHttpException {
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
    static void main(String[] args) throws IOException, UnsuccessfulHttpException {
        TokenRequest tokenRequest = new TokenRequest("login", "password");

        MarzbanAPI marzbanAPI = new MarzbanAPIImpl.Builder()
                .setTokenRequest(tokenRequest)
                .setHost("https://example.com:8000")
                .build();

        List<String> vlessTcpRealityStatus = List.of(UserRequest.Protocol.VLESS_TCP_REALITY.getValue());
        UserCommon.Vless vless = new UserCommon.Vless();
        UserCommon.Proxies proxies = UserCommon.Proxies.builder().vless(vless).build();
        UserCommon.Inbounds inbounds = UserCommon.Inbounds.builder().vless(vlessTcpRealityStatus).build();

        UserRequest userRequest = new UserRequest("user", proxies, inbounds);

        UserResponse userResponse = marzbanAPI.addUser(userRequest); //Object UserResponse
        System.out.println(userResponse.getLinks()); // [links]
    }
}
```

### Modify User
```java
public class Main {
    static void main(String[] args) throws IOException, UnsuccessfulHttpException {
        TokenRequest tokenRequest = new TokenRequest("login", "password");

        MarzbanAPI marzbanAPI = new MarzbanAPIImpl.Builder()
                .setTokenRequest(tokenRequest)
                .setHost("https://example.com:8000")
                .build();

        List<String> vlessTcpRealityStatus = List.of(UserRequest.Protocol.VLESS_TCP_REALITY.getValue());
        UserCommon.Vless vless = new UserCommon.Vless();
        UserCommon.Proxies proxies = UserCommon.Proxies.builder().vless(vless).build();
        UserCommon.Inbounds inbounds = UserCommon.Inbounds.builder().vless(vlessTcpRealityStatus).build();

        UserRequest modifiedUser = new UserRequest("user", proxies, inbounds);

        String status = UserSearchRequest.Status.DISABLED.getValue();

        modifiedUser.setStatus(status);

        UserResponse user = marzbanAPI.modifyUser(modifiedUser);

        System.out.println(user.getStatus()); //String [status]
    }
}
```

### Get User
```java
public class Main {
    static void main(String[] args) throws IOException, UnsuccessfulHttpException {
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