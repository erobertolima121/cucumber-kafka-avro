package Steps;

import com.github.simplesteph.kafka.apps.v1.KafkaAvroJavaConsumerV1Demo;
import com.github.simplesteph.kafka.apps.v1.KafkaAvroJavaProducerV1Demo;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class ExemploSteps{

    @Dado("^que eu produzo uma mensagem$")
    public void produzir_evento_para_kafka() {
        KafkaAvroJavaProducerV1Demo producer = new KafkaAvroJavaProducerV1Demo();
        producer.main();
        System.out.println("Dado");
    }
    @Quando("^eu realizar o consumo dessa mensagem$")
    public void consumir_evento_para_kafka()
    {
        KafkaAvroJavaConsumerV1Demo consumer = new KafkaAvroJavaConsumerV1Demo();
        consumer.main();
        System.out.println("Quando");
    }
    @Então("^quero ver a idade de \"([^\"]*)\"$")
    public void validar_idade_na_mensagem_consumida_kafka(int idade)
    {
        System.out.println("Então");
    }
}
