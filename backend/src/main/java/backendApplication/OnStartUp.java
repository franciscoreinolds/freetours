package backendApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class OnStartUp {

    @Autowired
    private ServiceRepository repository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //Write your business logic here.
        if (repository.findAll().size() <= 0) {
            preloadData();
        }else{
            fetchData();
        }
    }

    private void preloadData() {

        List<Service> services = new ArrayList<>();
        Service someService= new Service("name", "type");
        services.add(someService);

        repository.saveAll(services);
    }

}
