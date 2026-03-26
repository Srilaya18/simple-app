import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws Exception {
        // This starts a server on Port 8086 to match your Deployment
        HttpServer server = HttpServer.create(new InetSocketAddress(8086), 0);
        
        server.createContext("/", (exchange -> {
            String response = "Hello! Your Java App is running on Kubernetes!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }));

        System.out.println("Server started on port 8086...");
        server.setExecutor(null); 
        server.start();
    }
}