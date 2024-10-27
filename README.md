# Terminal Local Chat
***This project is a terminal local chat between two devices connected to the same Wi-Fi network (alternativelly you can use hotspot-Wi-Fi)***.

![Build state](https://github.com/gabrielmjr/terminal-local-chat/actions/workflows/maven.yml/badge.svg)

### How it works?
As the name suggests, it works locally between two devices (Later on this number will be increased), connected to the same Wi-Fi, or one turns on [hotspot](https://en.wikipedia.org/wiki/Wi-Fi_hotspot#:~:text=A%20hotspot%20is%20a%20physical,to%20an%20Internet%20service%20provider.), other turns on [WI-Fi](https://en.wikipedia.org/wiki/Wi-Fi) and connects to the hotspot, both run the [jar file](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jarGuide.html), follow the instructions, after all, they can chat together locally using terminal.

To make they communicate themselves, I used server socket, where after one device connect to the other, both sides waits for the received message and print it from a background [Thread](https://www.w3schools.com/java/java_threads.asp) and while the background thread waits for the received message, the main thread is sending the message by using socket connections and [streams](https://www.javatpoint.com/java-8-stream) does this conversion from Strings to byte arrays and the reverse work.

The String before converted to bytes and be sent, it's serialized to [JSON](https://www.json.org/json-en.html) and then converted to bytes and sent, who receives the JSON String only does the reverse work of who sends it (I'm about the codes, not persons).


### Get started
To use of the terminal local chat, you must to:
1. Have two devices (and terminal in them).
2. Connect them to the same Wi-FI (or turn on hotspot in one device and connect to it from other device).
3. Have a [Java Envinronment](https://www.java.com/en/download/manual.jsp).
4. Download a [release](https://github.com/gabrielmjr/terminal-local-chat/releases) executable jar file (I recommend the latest). [Optionally you can clone the repository and compile it yourself]].
5. Run the jar file by using:
  ```
     java -jar terminal-local-chat_<version>_.jar
  ```
6. The console will show you:
  ```
     Enter your username/nickname: 
  ```
Just enter an username.

> [!NOTE]
> This user/nick name will be shown in the other device.

7. The console will show you:
```
  Type 1 to be the server.
  Type 2 to be the client.
```   

8. Enter 1 in one of the devices, then the console will show you:
  ```
    >>> Enter the port to listen: 
  ```
  - Just choose the port (1024-65535) and wait the other device to connect.
  The terminal will show you:
  ```
    Serving in ip: [local ip], port: [choosen port]
  ```
  Use the local ip and choosen port in the other device doing:

9. In the other device, enter 2 and the console will show you:
  ```
    >>> Enter the ip address: 
  ```
  - Enter the ip shown in the other device device, then the terminal will show you;
  ```
    Enter the listened port: 
  ```
  - Enter the choosen port and voilà, they're connected to each other, you're able to send messages, just enter something and it will show you in the other device's terminal.

## License
```Copyright 2024 Gabriel MJr```.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
