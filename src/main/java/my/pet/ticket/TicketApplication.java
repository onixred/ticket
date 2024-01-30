package my.pet.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}
}


//		EtcdProperty property = new EtcdProperty(
//				"http://etcd:2379",
//				"10",
//				"PT1S",
//				"PT1S",
//				"PT1S",
//				"keyPrefix",
//				"component",
//				"DC",
//				"instance");
//		ETCD.init(property);
//		ETCD.readEtcd(null, "etcdKey", null, null);
