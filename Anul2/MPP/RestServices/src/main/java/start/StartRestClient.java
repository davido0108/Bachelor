package start;

import chat.domain.Spectacol;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import chat.services.rest.ServiceException;
import rest.client.SpectacoleClient;

public class StartRestClient {
    private final static SpectacoleClient usersClient=new SpectacoleClient();

    public static void main(String[] args) {
        RestTemplate restTemplate=new RestTemplate();
        Spectacol userT=new Spectacol("sdasd","sdasd","123",20, 20);
        try{
            //  User result= restTemplate.postForObject("http://localhost:8080/chat/users",userT, User.class);

            //  System.out.println("Result received "+result);
      /*  System.out.println("Updating  user ..."+userT);
        userT.setName("New name 2");
        restTemplate.put("http://localhost:8080/chat/users/test124", userT);

*/
            // System.out.println(restTemplate.postForObject("http://localhost:8080/chat/users",userT, User.class));
            //System.out.println( restTemplate.postForObject("http://localhost:8080/chat/users",userT, User.class));

            show(()-> System.out.println(usersClient.create(userT)));
            show(()->{
                Spectacol[] res=usersClient.getAll();
                for(Spectacol u:res){
                    System.out.println(u.getId()+": "+u.getArtist());
                }
            });
        }catch(RestClientException ex){
            System.out.println("Exception ... "+ex.getMessage());
        }

    }

    private static void show(Runnable task) {
        try {
            task.run();
        } catch (ServiceException e) {
            //  LOG.error("Service exception", e);
            System.out.println("Service exception"+ e);
        }
    }
}
