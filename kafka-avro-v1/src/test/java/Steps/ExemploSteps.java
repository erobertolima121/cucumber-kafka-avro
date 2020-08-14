package Steps;

import com.example.Customer;
import com.github.simplesteph.kafka.apps.v1.KafkaAvroJavaConsumerV1Demo;
import com.github.simplesteph.kafka.apps.v1.KafkaAvroJavaProducerV1Demo;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;

public class ExemploSteps extends KafkaAvroJavaConsumerV1Demo{

    public Customer avroConsumido;

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
        avroConsumido = null;
        avroConsumido = consumer.main();
        System.out.println("Quando -> " + avroConsumido);
    }

    @Então("^quero ver a idade de \"([^\"]*)\" na mensagem consumida$")
    public void validar_idade_na_mensagem_consumida_kafka(int idade)
    {
        System.out.println("Então -> " + idade + avroConsumido);
        Assert.assertEquals(idade,avroConsumido.getAge().intValue());
    }
}
