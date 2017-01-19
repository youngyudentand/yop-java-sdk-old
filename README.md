#SDK使用说明
易宝开放平台的SDK是由程序自动化生成的代码包，其中包含了构建请求、加密、返回解析等一些必要的功能。目前支持的开发环境如下：

java sdk支持1.5及以上（目前仅支持j2se标准java平台使用，不适合andriod平台）；且需依赖spring框架及fastxml包

##1.开始
前提已注册成为开放平台开发者，并创建应用。如果未注册，请访问[开放平台](https://open.yeepay.com/yop-mbr/)注册并创建应用。

如果是易宝商户，也可以不创建应用，直接以商户身份可登录开放平台、申请API权限、并直接发起调用

##2. 开发准备
普通java工程请将sdk内各jar包加入classpath（若工程已有相同jar或有其他版本jar可不添加）
易宝子公司使用YOP可通过maven依赖：

```xml
<dependency>
	<groupId>com.yeepay.g3.yop</groupId>
	<artifactId>yop-sdk</artifactId>
	<version>1.0</version>
</dependency>
```

##3. 示例

###3.1. 对称

代码示例：

```java
YopConfig.setAppKey("<Your appKey>");
YopConfig.setAesSecretKey("<Your appSecret>");
YopRequest request = new YopRequest();
request.addParam("address", "13812345678");
YopResponse response = YopClient.get("/rest/v1.0/notifier/blacklist/add", request);
```

###3.2. 非对称

#### 默认配置文件名：
yop_sdk_config_default.json

#### 覆盖默认配置文件名的方法：
在 VM arguments 中指定配置文件名

例如：
* -Dyop.sdk.config.file=/yop_sdk_config_override.json
* -Dyop.sdk.config.file=file://home/app/yop_sdk_config_override.json

#### 配置项说明：
* yop_public_key：YOP平台密钥
* store_type
    * string：密钥文本
    * file_p12：p12格式的密钥文件
* cert_type
    * RSA2048
    * RSA4096
* value
    * 如果store_type为string，则该值为密钥文本
    * 如果store_type为file_*，则该值为密钥文件路径
* password：如果p12需要密码

#### 示例配置：

```sdk_config
{
  "yop_public_key": [{
    "store_type": "string",
    "cert_type": "RSA2048",
    "value": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4g7dPL+CBeuzFmARI2GFjZpKODUROaMG+E6wdNfv5lhPqC3jjTIeljWU8AiruZLGRhl92QWcTjb3XonjaV6k9rf9adQtyv2FLS7bl2Vz2WgjJ0FJ5/qMaoXaT+oAgWFk2GypyvoIZsscsGpUStm6BxpWZpbPrGJR0N95un/130cQI9VCmfvgkkCaXt7TU1BbiYzkc8MDpLScGm/GUCB2wB5PclvOxvf5BR/zNVYywTEFmw2Jo0hIPPSWB5Yyf2mx950Fx8da56co/FxLdMwkDOO51Qg3fbaExQDVzTm8Odi++wVJEP1y34tlmpwFUVbAKIEbyyELmi/2S6GG0j9vNwIDAQAB"
  }],
//  "isv_private_key": [{
//    "store_type": "file_p12",
//    "cert_type": "RSA2048",
//    "password": "123",
//    "value": "/certificate.p12"
//  }],
    "isv_private_key": [{
      "store_type": "string",
      "cert_type": "RSA2048",
      "value": "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC6iLkZOHl5VOvFN7i1jf6uu8UMyrU4fwrdxsvfTaeYVypOX+4SbEiCj9IqMiP7io2RPkERQae8Vt3kUXR1JSNYmofq27G3hXkZGMjjmmJ5X3hMPdfJtm0V8Vf6wHIpwpgE+cmJjtgYkmRnqRB+CVHFjkwoMGzJj8EhVu2uLoNM+XHwVKr4aME70gWKCUp31lHVzR+CHiBrrNvgVUBHWPoP79Yn3A8+lkh63qwje3pP2yD8ZX4/2xXWaxkrJ2l3xBVGy3jp2GBh3RsQlDM9R5MP24fiHCjAZ2+It5s4Li6pqe+Cs13zNWJh3K5Py2zh9u30V56dO37GN57Ox3E0pErTAgMBAAECggEATgkOro9iujCdpdbmzgVGS6FAEVboM2dqi1c0SQn6mA+3Iiyds1VbjEtQl9h8FypiiSWrIOX/nxoUIAU3nB5aSCH1xHn+QhFzlN06hYU7tx0KfZLNqJRJ1IkvzjYeTHwdDs/U0VhR7Q2pNXiT7rZFYElkcNe5WyVpnWH5cwoAYf2tPrQ+X+tIq7w/SsmLAt5luLRirkgjYxfVYKmTnWcEXNCthwX/OCrBJTQF2p6WWdqqd740asWEGZHo3Ow5PfTj9bSH9xfEq5MdokeHUeS3ComO7kwBlJr0t+FF+RMU2uQGdqzrjoUfrAY03ekqXhZUhvxHlu0d8FgL9DgT598McQKBgQDxaUwrXJeVOA1A4YiUhKshM14kPiNqYOZDyBrsJ+ghWozargEz0lbW18gDNK3RWoDeX2sUoF/0G/v86Tj4PcC/9Xbrqz99FuoFe1VOVLLihdzIbvwUp/KNC9OfLPBoSDShKRFuJPQqlooB0tBEQvfeDN25icCHKQW3+VRugD85mQKBgQDFznVx7ZPSDyuSQVr/11lionhJg9aw5ST9UyC2wKvB776FkVVQLLBzMmBxRtS/TGKYaz3YSd4/g2sxq2LBUe8SedcSOr5QWVJU9fDij5imM0iZ7xQEESsiy5EGNX3w+kOI+03JfaFhdxCFLVyjH60VoABKR+cMJHRtToQbH0KjSwKBgAxtrVw2Ih+uiRAkDFIJGn5VFK2s/UnGv1QSA0DRNSQyczn251zxoULiTQvNNH2ouSW5p9uh/g6lOi3lG+e71iaLzpmHcXdRLK8nkFa+CY+b8Kfo/tVZaCVWzIhPWfkkboE1ig4vnNpscaJyZgJ9qCEobGnW67CJrh+wmGYv+BFRAoGASatS4lNxMlKkD5x3n8E8cRfBZN/NHaS52f8Tt/yZ+2imtGgkNe/u53hjosyWf4ibT9jg96TLOSxgx+bkqMszFtZ52zg1mQcrOqk+RaLlX0DKUUpRUhJy1kqqgH17ojwS2vl9RwWbDQmjBXaXgclSVTN56DBPRJ5vHiaX3OIPl3ECgYBEnhsM7DoGTmF5rdXj+JHYi9h5m8tA3hJEY6xWpDqIYOs4geciFtV8XPjmdYcrBJ1Sf4xu9vqMPcVZfJMU7KJNOOpz4rNSYpc1Z7Lfl7zsUnrGmaj6/dw/5QTbE5uneRB6JyjG9hMF9Eg1rEvMCl7pv6ya6WLcpS+0ZY6ZaRD+Hw=="
    }],
  "default_protocol_version": "yop-auth-v2",
  "sdk_version": "1.1.0"
}
```

#### 代码示例：

```java
// QA 环境
// String BASE_URL = "http://10.151.30.80:18064/yop-center/";
// YopRequest request = new YopRequest("<Your appKey>", "<Your appSecret>", BASE_URL);
YopRequest request = new YopRequest("<Your appKey>", "<Your appSecret>");
request.addParam("name", "安徽四创");
request.addParam("idCardNumber", "630104063035716");
request.addParam("requestCustomerId", "yop");//子商户编号
request.addParam("requestFlowId", "test-" + RandomStringUtils.randomNumeric(15));//请求流水标识
request.addParam("requestSystem", "auth2-boss");
System.out.println(request.toQueryString());
YopResponse response = YopClient3.postRsa("/rest/v2.0/auth/idcard", request);
```

#### 测试私钥RSA2048：
MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCXsKWWClznZbdTwp9183e4Ygu/
twbQhuS6LPpu/TZ+OFwwauvIZnOyKu+rFh6apKyVxiLEkssnTsBjLjUIlypEGU2SdLGkswWAPvVd
unLjjWEz37W2w4VNkGf8bGCQ9fIxMynoBCTeeWcQz896e1y2p5YZHygUhXGLM/9q5mr3iQQgrEPd
FEAdlfLexkbVIF2bS02NsDFLNvqKNk7219cefxWPgJfN7RukUIZyy4nbeevbMAAFpNUFh1NlAh4q
zwocOfbZ3NgtwJDf29jibpM3dacS7tqYGwpeGpKazS9tZgTAYcX2kLT7s+G6vVzVQR61pvvDs5ub
yfsw/KFR8KDDAgMBAAECggEAShSE6Z+p+4AbZhaYVbxPbYbEgh5af6BBOAMbUvTqlf3kV+j/uWD/
g7WgUod87r0ZZBPdiu69tDarkkRQth9NDvDkh2/iCbM8LoOQxPN3hFXZcMICNn2KLnUls4siJelX
HFwGTT8o2lWj1fwHMaPphXKWxTIIGu2IpBkC1iwtdTF8mqe2HH+H2djBE96JXVZIf3/FgGu8ppmX
a/xG4DfrTxFnGEJzgaadT3Z+ybXbqjYgFgmmBnZOaTx1XPQfLGQVYJz9BunDhwhrqBUM+QuLr1jU
sMsj/Yud52cNXjwq9z8FfkKUdVVfE4VrzH8JpKKk7Vim7RWBQER29jlEnV+ysQKBgQDjMWxZz4Av
eXxWSx7MgXN9PEzxzmGWSApseDskSi5PAmXa4ut5XyNJUiGJ8Zf+cssPfWFNtB7suJBuoMTtrQSa
p2tgoo70y7QSO0ZlZ0v5Ny9LYh8oHvDgBJVNmS5HWv1U1/VHxNHczNmQ05smXNo1bzMYe5Xo10J2
W47UUTgOHwKBgQCq7G6B5RfD+O1jdmYWlilh5oi1XGdYJGnzhs9DmAUN5plQ3VxpUFxxQCgOwXCs
kfT9QUVYhsIpQIs2iCylwuNDuxxiEQyRpeBirRaqmxvosv08Trwsr1Vs/Cuh17ZZOS+OUehN0fDZ
CiruK4e2btVfv8LlE1KMuoiUsn1X2gWQ3QKBgCyqBrcRSA4NQBhm5EMoH+A6/pV7EUxOFV6FtHrJ
6pi1y/hgLBLMVU+Qye8og80OHEWLTJnOE1ZOYnadPJnNLd6Jk16IFrqhYWFELe65hAIWi0GypJVq
n8gqnn+G4cY9aRhI7HuTgf56dzs1nobIMk3W8qCZizsfNn22OjobTX3ZAoGBAJsTusvF1IMs5g05
DjTt9wvpQx3xgZ46I5sdNA3q7qMHFxGEVeUDUWw7Plzs61LXdoUU5FsGoUEWW3iVopSett3r9TuQ
pmu7KVO+IXOXGYJOa259LUQJrKMeRGQpuDtJpDknXXLFyRTSodLH0fEWrCecb7KxjlM6ptLrAshj
emtNAoGBAMzGo6aNER8VZfET8Oy0i5G8aVBp6yrMiQsNOj4S1VPoHI+Pc6ot5rDQdjek9PRzF9xe
CU4K7+KLaOs6fVmTfsFpPbDafCTTmos9LGr5FIyXpU7LQCl3QPHWPDd5ezsu9SPVjzsEPX3WTSOJ
uUA8hE7pJnAzMHLGAFpIXJRu3Z/y

#### 测试公钥RSA2048：
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl7Cllgpc52W3U8KfdfN3uGILv7cG0Ibk
uiz6bv02fjhcMGrryGZzsirvqxYemqSslcYixJLLJ07AYy41CJcqRBlNknSxpLMFgD71Xbpy441h
M9+1tsOFTZBn/GxgkPXyMTMp6AQk3nlnEM/PentctqeWGR8oFIVxizP/auZq94kEIKxD3RRAHZXy
3sZG1SBdm0tNjbAxSzb6ijZO9tfXHn8Vj4CXze0bpFCGcsuJ23nr2zAABaTVBYdTZQIeKs8KHDn2
2dzYLcCQ39vY4m6TN3WnEu7amBsKXhqSms0vbWYEwGHF9pC0+7Phur1c1UEetab7w7Obm8n7MPyh
UfCgwwIDAQAB

#### 易宝生产公钥RSA2048：
-----BEGIN RSA2048 PUBLIC KEY BLOCK-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6p0XWjscY+gsyqKRhw9MeLsEmhFdBRhT
2emOck/F1Omw38ZWhJxh9kDfs5HzFJMrVozgU+SJFDONxs8UB0wMILKRmqfLcfClG9MyCNuJkkfm
0HFQv1hRGdOvZPXj3Bckuwa7FrEXBRYUhK7vJ40afumspthmse6bs6mZxNn/mALZ2X07uznOrrc2
rk41Y2HftduxZw6T4EmtWuN2x4CZ8gwSyPAW5ZzZJLQ6tZDojBK4GZTAGhnn3bg5bBsBlw2+FLkC
QBuDsJVsFPiGh/b6K/+zGTvWyUcu+LUj2MejYQELDO3i2vQXVDk7lVi2/TcUYefvIcssnzsfCfja
orxsuwIDAQAB
-----END RSA2048 PUBLIC KEY BLOCK-----

#### 易宝生产公钥RSA4096：
-----BEGIN RSA4096 PUBLIC KEY BLOCK-----
MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEArbJ1oNf7JmQ5k9HxebaxPet34gzTOPXu
LNQJZ8LsgfiDs/J6k8pv3xahGaGWddF+Hl0JYKov69eoOe7aMIvg6M9hM+mz/7bEo2N3IjsgN3gb
YxxJAD0zPZsYfcRnjdtjsahDD1sUcarzjQte7mNdUOLwp4bGbYRiudaxZv9aRngonubf5trCk4V0
c0EuJJQPi8X3iUbKebS/ou1CpDi47b0tPyNr69bgd7wtnazVdf7gTJyL1hL9yw6OFPL+HoUQwCLk
wl87oi4kdUlw7Y9LCjpveesIwroo8bB8rQdvtbugjHRuzbbxwy4k5ls8icqUjzj1fHAlJ6xJZpJB
/tUK37CnDZd+D/sryXeAvclz/d5F1PszE/bseEgn48CWV4H1iiNw1+xKUhnobBnDjPkLUtJ7o2sR
5392KpJLuRb8jJe5On25u3FUPA/tQQID1T07rkbC1KhKuXoiDMTmFqxKz/v1BbFWjWhBuxQOaqkz
o4jqs2JgtwU5JR4XnQlAWLPYv6QMuBa+G8/26Zpun1A9JOztpRKBTaTeQ5olX6Oq4hjdfCL9GBh8
N5OjuAlOY7hJYwRdv9YdOUcf5yfgGgDIKNpGRqEJBUSQGVQiEnwO93QZOIaoGmld/gzjnN+zVFkK
evf+n+LRMqQlb1RU9oKOcv4g0GW5mojWy3Fy6UI32kUCAwEAAQ==
-----END RSA4096 PUBLIC KEY BLOCK-----

##4. 详细说明

###4.1. 应用-密钥配置
默认使用`YopConfig.setXXX`初始化`appKey`及`AES密钥`（商户身份仅需设置Hmac密钥）
开放平台默认调用地址为[https://open.yeepay.com/yop-center](https://open.yeepay.com/yop-center)

如需非生产环境联调，需手动设置调用地址：
```java
YopConfig.setServerRoot("http://qa.yeepay.com:18083/yop-center");
```
如需同时使用多个appKey则通过
```java
YopRequest(String appKey, String secretKey)初始化请求对象
```

###4.2. 请求
传参：
```java
request.addParam("address", "13812345678");
```
>相同参数名自动构建为数组参数

签名：使用YopClient发起请求自动签名
签名逻辑
(1) 所有请求参数按参数名升序排序，数组/列表值排序;
(2) 按 请 求 参 数 名 及 参 数 值 相 互 连 接 组 成 一 个 字 符 串 :<paramName1><paramValue1><paramName2><paramValue2>...;  （注：restful接口自动将URI作为method参数参与签名）
(3) 将应用密钥分别添加到以上请求参数串的头部和尾部:<secret><请求参数字符串><secret>;
(4) 对该字符串按指定算法（默认为SHA1） 签名
(5) 该签名值使用sign系统级参数一起和其它请求参数一起发送给服务开放平台

发起请求：
```java
YopResponse response = YopClient.get("/rest/v1.0/notifier/blacklist/add", request);
```

###4.3. 响应
`YopResponse`主要内容是状态、业务结果、错误/子错误
业务结果被解析为Map，可直接取值；

若觉得Map型结果不便于使用，可通过`response.unmarshal(xxx.class)`反序列化为自定义java对象；

若不想自定义java对象，`response.getStringResult()`即为字符串形式的业务结果，格式为request指定格式（json/xml)，可直接做json/xml解析。

SDK已提供工具类方法`YopMarshallerUtils.parse`支持json解析，具体解析方法可自选。

###4.4. 验签
请求是必须做签名验证的，SDK&YOP自动完成

响应结果也可签名，通过`request.setSignRet(true)`指定（默认不做结果签名），YopResponse自动验签

某些API指使用非SHA1签名算法，请求对象需明确指定，示例：`request.setSignAlg("SHA");`


###4.5. 加解密
某些API参数使用明文参数有安全风险，如支付接口可能需要传信用卡CVV码，这些信息明确要求不能明文传输，为此需要对请求进行加密

SDK中，通过`request.setEncrypt(true)`指定此请求需加密，当前支持AES及Blowfish加解密算法，开放应用调用使用AES、商户身份调用使用Blowfish

请求加密则响应默认加密，YopResponse自动解密、验签、反序列化

##5. 常见问题

###5.1. 报错：Java Security: Illegal key size or default parameters

 说明：异常java.security.InvalidKeyException:illegal Key Size的解决方案
 <ol>
 	<li>在官方网站下载JCE无限制权限策略文件（JDK7的下载地址：
      http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html</li>
 	<li>下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt</li>
 	<li>如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件</li>
 	<li>如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件</li>
 </ol>