package modern.challenge;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.util.stream.Stream;
import jdk.incubator.concurrent.StructuredTaskScope;
import jdk.incubator.concurrent.StructuredTaskScope.ShutdownOnSuccess;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tT] [%4$-7s] %5$s %n");

        try (ShutdownOnSuccess scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {

            Stream.of(1, 2, 3)
                    .<Callable<String>>map(id -> () -> fetchTester(id))
                    .forEach(scope::fork);

            scope.join();

            String result = (String) scope.result();

            logger.info(result);
        }
    }

    public static synchronized String fetchTester(int id) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        // intentionally added a delay of 1-5 seconds
        Thread.sleep(Duration.ofMillis(ThreadLocalRandom.current().nextLong(5000)));

        HttpRequest requestGet = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://reqres.in/api/users/" + id))
                .build();

        HttpResponse<String> responseGet = client.send(
                requestGet, HttpResponse.BodyHandlers.ofString());

        if (responseGet.statusCode() == 200) {
            return responseGet.body();
        }

        throw new UserNotFoundException("Code: " + responseGet.statusCode());
    }
}