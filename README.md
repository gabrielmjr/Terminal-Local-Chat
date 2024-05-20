# Terminal Local Chat
***This project is a terminal local chat between two devices where one is hosting (by turning on hotspot) and other is client (by connecting to the turned on hotspot)***.

![Build state](https://github.com/gabrielmjr/terminal-local-chat/actions/workflows/maven.yml/badge.svg)

### How it works?
As the name suggests, it works locally between two devices (Later on this number will be increased), where one turns on [hotspot](https://en.wikipedia.org/wiki/Wi-Fi_hotspot#:~:text=A%20hotspot%20is%20a%20physical,to%20an%20Internet%20service%20provider.), other turns on [WI-Fi](https://en.wikipedia.org/wiki/Wi-Fi) and connects to the other's hotspot, both run the [jar file](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jarGuide.html), follow the instructions, after all, they can send message from terminal for each other.

To make they communicate themselves, I used server socket, where after one device connect to the other, both sides waits for the received message and print it from a background [Thread](https://www.w3schools.com/java/java_threads.asp) and while the background thread waits for the received message, the main thread is sending the message by using socket connections and [streams](https://www.javatpoint.com/java-8-stream) does this conversion from Strings to byte arrays and does the reverse work.

The String before converted to bytes and sent, it's serialized to [JSON](https://www.json.org/json-en.html) and then converted to bytes and sent, who receives the JSON String only does the reverse work of who sends it (I'm about the codes, not persons).


### Get started
To use of the terminal local chat, you must to:
1. Have two devices (and terminal in them).
2. In both devices, they must have, where one turns on hotspot and other connect to it by WI-FI to run it.
3. Have a [Java Envinronment](https://www.java.com/en/download/manual.jsp).
4. Download a [release](https://github.com/gabrielmjr/terminal-local-chat/releases) jar file (I recommend the latest). [Optionally you can clone the repository and compile it yourself]].
5. Run the jar file by using:
  ```
     java -jar terminal-local-chat_<version>_.jar
  ```
6. The console will show you:
  ```
     Enter your username/nickname: 
  ```
Just enter an username.

***NOTE:*** This user/nick name will be shown in the other device.

7. The console will show you:
```
  Type 1 to be the server (Turn on hotspot).
  Type 2 to be the client (Turn on wifi).
```   

  To the device where hotspot is on, enter 1 and the terminal will show you:
  ```
    >>> Enter the port to listen: 
  ```
  Just choose the port (1024-65535) and wait the other device to tonnect.
  Use the shown data in the device who turned on hotspot, it will be:
  ```
    Serving in ip: [your hotspot ip], port: [choosen port]
  ```

  To the device where other's device hotspot is connected, enter 2 and the terminal will show you:
  ```
    >>> Enter the ip address: 
  ```
  Enter the ip shown in the device where hotspot is on, after that, the terminal will show you;
  ```
    Enter the listened port: 
  ```
  Enter the choosen port and voilà, they're connected to each other, you're able to send messages, just enter something and it will show you in the other device's terminal.

## License
```Copyright 2024 Gabriel MJr```

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
