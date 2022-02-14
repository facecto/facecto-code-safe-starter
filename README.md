# About facecto-code-safe-starter
A http interface encryption and decryption component.
Receive encrypted http request data and decrypt and submit business processing.
Return the encrypted data to the http client.

# Quick Start
## Step 0: setting the pom.xml add dependency 
```
<dependency>
  <groupId>com.facecto.code</groupId>
  <artifactId>facecto-code-safe-starter</artifactId>
  <version>1.1.2</version>
</dependency>
```

## Setp 1: setting application.yaml
```
app:
  safe:
    has-rsa: true|false. Boolean. If it is true, it means that the configured IV, KEY, and SECRET have been encrypted with asymmetric public key. The pri-key asymmetric private key string must be configured.
    has-dynamic: true|false. Boolean, if it is true, it means that the configured IV, KEY, and SECRET will be read from redis, please configure spring redis. And make sure that the IV, KEY, and SECRET have been stored in redis.
    iv: String format. Symmetrically encrypted IV. The length is 16 bits. example: "1234567890123456"
    key: String format. The key of symmetric encryption. The length is 32 bits. example: "01234567891234560123456789123456"
    secret: String format. The secret of symmetric encryption. The length is 32 bits. example: "1829b4abbba0794301a075fc2283d2ba"    
    pri-key: String format. If has-rsa is true, this value must be configured. It represents the private key string of asymmetric encryption used by the system. example: "MIIEvQIBADANBgkqh...."
```

## Step 2: add @EnableCodeSafe in SpringBoot Application
Like this:
```java
@SpringBootApplication
@EnableCodeSafe
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
```
Of course you can also use
```java
@SpringBootApplication
@ComponentScan({"com.facecto.code.safe","com..."})
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }

}
```

The @EnableCodeSafe annotation is recommended. Here's the answer: who ask me why it doesn't work.

## Step 3: Use @Encrypt annotation before the method that needs encryption
example:
```
    @GetMapping(value = "/user")
    @Encrypt
    public CodeResult getUser(){
        User u = new User()
                .setEmail("ijonso123@gmail.com")
                .setId(1)
                .setName("JonSo");
        return CodeResult.ok("OK", u);
    }
```
It maybe return
```json
{
    "code": 0,
    "message": "OK",
    "sign": "46E5FC4DF302BF74F66666C3DBC05810CCBB486B",
    "data": "97XVcP9tJ7Vh7glQlc4R/SpSDKI6gWKcZHnmqfrSkpKUQmkTe4fYtw83qKgz5tTGkeMyaC9F4UmzGPqDVxa0U2A5h/jRn1eUnYvHFARky6ZWY99VFBJ3WDHYQBBZTK9P11C4a1J+Zw=="
}
```
## Step 4: Use @Decrypt annotation in the parameter to decrypt.
example:
```
    @PostMapping(value = "/user")
    public CodeResult setUser(@RequestBody @Decrypt User u){
        return CodeResult.ok("OK",u);
    }
```
the json data:
```json
{
    "sign": "46E5FC4DF302BF74F66666C3DBC05810CCBB486B",
    "data": "97XVcP9tJ7Vh7glQlc4R/SpSDKI6gWKcZHnmqfrSkpKUQmkTe4fYtw83qKgz5tTGkeMyaC9F4UmzGPqDVxa0U2A5h/jRn1eUnYvHFARky6ZWY99VFBJ3WDHYQBBZTK9P11C4a1J+Zw=="
}
```
And then return:
```json
{
    "data": {
        "id": 1,
        "name": "JonSo",
        "email": "ijonso123@gmail.com"
    },
    "code": 0,
    "message": "OK",
    "status": "SUCCESS"
}
```
## Step 5 : No more step. Enjoy it.

## Default Key
### public key 
```
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr7EzZwdUqO5K/PZpS7dhVUT6DBslpzKcAgrV8GmPnksjbH4QzM5OrMtePvmLPxLZGxc4PClSo0+xLVsc9C9ycQs71xB/8TcDcWugoWMITJAiqbB1mF0zo0aruklJLJZSyjSLbWqZXE7dcW2f86FQ+CduBpZWojTW2WVRSP7urxGR8pc2Rxm21bvGW0i9BgbUVgrvRZxrvXtL9pUDPFZw96eB85ZY8p7/Dbz6yK+JENRn2ePIsLhfD2ut/YlR0SfPq5NPKAmtOvH2EwawU6XQ24i1dpuNRPTdQUET9c78LHcfGlWvY2ccdgudecHR+2C7hN5owsp5d6FnVW4EV5qNGQIDAQAB
```
### private key
in the code
Warning: For data security, please do not use the default key pair.

# About facecto.com
https://facecto.com

# Document update time
2020-02-14