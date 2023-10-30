package rest.client;

import chat.domain.Spectacol;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import chat.services.rest.ServiceException;

import java.util.concurrent.Callable;

public class SpectacoleClient {
    public static final String URL = "http://localhost:8080/chat/users";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Spectacol[] getAll() {
        return execute(() -> restTemplate.getForObject(URL, Spectacol[].class));
    }

    public Spectacol create(Spectacol user) {
        return execute(() -> restTemplate.postForObject(URL, user, Spectacol.class));
    }

    public void update(Spectacol user) {
        execute(() -> {
            restTemplate.put(String.format("%s/%s", URL, user.getId()), user);
            return null;
        });
    }

    public void delete(String id) {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }



}
