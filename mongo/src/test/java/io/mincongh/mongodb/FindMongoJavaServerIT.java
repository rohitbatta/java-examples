package io.mincongh.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import de.bwaldvogel.mongo.MongoServer;
import de.bwaldvogel.mongo.backend.memory.MemoryBackend;
import java.net.InetSocketAddress;

public class FindMongoJavaServerIT extends FindAbstractIT {

  private MongoClient client;
  private MongoServer server;

  @Override
  protected MongoDatabase database() {
    return client.getDatabase("testdb");
  }

  @Override
  protected void preSetup() {
    server = new MongoServer(new MemoryBackend());
    InetSocketAddress serverAddress = server.bind();
    client = new MongoClient(new ServerAddress(serverAddress));
  }

  @Override
  protected void postTeardown() {
    client.close();
    server.shutdown();
  }
}
