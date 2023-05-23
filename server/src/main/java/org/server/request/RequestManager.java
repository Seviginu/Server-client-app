package org.server.request;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.server.command.CommandManager;


public class RequestManager {

  private CommandManager commandManager;
  private final ServerSocket channel;
  private Socket currentSocket;
  private final Logger logger = LogManager.getLogger("org.server.request.RequestManager");

  public RequestManager(ServerSocket channel) {
    this.channel = channel;
  }

  public CommandPackage getRequest() {
    try {Socket socket = channel.accept();
      this.currentSocket = socket;
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectStream = new ObjectInputStream(inputStream);
      logger.debug("request received");
      CommandPackage result = (CommandPackage) objectStream.readObject();
      logger.trace(result);
      return result;
    } catch (Exception ignored) {
      logger.debug("fail during receive request");
      return null;
    }
  }

  public void sendResponse(Request<?> request) throws IOException {
    try (
        OutputStream outputStream = this.currentSocket.getOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(outputStream)) {
      objectStream.writeObject(request);
      logger.debug("send response");
      logger.trace(request);
      this.currentSocket.close();
    } catch (Exception e) {
      logger.error("fail during send response");
      logger.trace(e.getStackTrace());
      throw e;
    }
  }

//  private void accept(SelectionKey key) throws IOException {
//    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
//    SocketChannel socket = ssc.accept();
//    key.attach(new NetworkUserChannel(this));
//    socket.configureBlocking(false);
//    socket.register(key.selector(), SelectionKey.OP_READ);
//    logger.info("new connection");
//  }
//
//  private void read(SelectionKey key) throws IOException {
//    SocketChannel socket = (SocketChannel) key.channel();
//    NetworkUserChannel networkUserChannel = (NetworkUserChannel) key.attachment();
//    try{
//      ByteBuffer buffer = ByteBuffer.allocate(1000000);
//      buffer.clear();
//      socket.read(buffer);
//      CommandPackage commandPackage =
//          (CommandPackage)
//              new ObjectInputStream(new ByteArrayInputStream(buffer.array())).readObject();
//        this.commandManager.setOutputChannel(networkUserChannel);
//        commandManager.executeCommand(commandPackage);
//    }
//    catch (ClassNotFoundException ignore){}
//    socket.register(key.selector(), SelectionKey.OP_WRITE);
//    logger.info("read request");
//  }
//
//  private void write(SelectionKey key) throws IOException {
//    SocketChannel socket = (SocketChannel) key.channel();
//    NetworkUserChannel networkUserChannel = (NetworkUserChannel) key.attachment();
//    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.socket().getOutputStream());
//    objectOutputStream.writeObject(networkUserChannel.getRequest());
//    logger.info("send response");
//  }
//
//  public void startLoop(){
//    try (Selector selector = Selector.open();
//            ServerSocketChannel server = ServerSocketChannel.open()) {
//      server.bind(new InetSocketAddress(7566));
//      server.configureBlocking(false);
//      server.register(selector, SelectionKey.OP_ACCEPT);
//      CommandManager commandManager = new CommandManager(this);
//      this.commandManager = commandManager;
//      FileManager fileManager =
//              new FileManager(
//                      new File("C:\\Users\\1500k\\IdeaProjects\\untitled1\\server\\collection.json"));
//      CommandManager.registerAllCommands(commandManager, fileManager.jsonToObj(), fileManager);
//      logger.info("server ready");
//      while(true){
//        selector.select();
//        Set<SelectionKey> keys = selector.selectedKeys();
//        for(var iter = keys.iterator(); iter.hasNext();){
//          SelectionKey key = iter.next();
//          iter.remove();
//          if(key.isValid()){
//            if(key.isAcceptable()){
//              accept(key);
//            }
//            if(key.isReadable()){
//              read(key);
//            }
//            if(key.isWritable()){
//              write(key);
//            }
//          }
//        }
//      }
//
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
}
