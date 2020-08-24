package Steps;

import com.example.Customer;
import cucumber.deps.com.thoughtworks.xstream.XStream;
import exemplo.aninhamento.informacaoUsuario;
import pageObjects.KafkaAvroJavaConsumerUsuario;
import pageObjects.KafkaAvroJavaConsumerV1Demo;
import pageObjects.KafkaAvroJavaProducerUsuario;
import pageObjects.KafkaAvroJavaProducerV1Demo;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ExemploSteps extends KafkaAvroJavaConsumerV1Demo {

    public Customer avroConsumido;
    public informacaoUsuario avroConsumidoUsuario;

    @Dado("^que eu produzo uma mensagem$")
    public void produzir_evento_para_kafka() {
//        KafkaAvroJavaProducerV1Demo producer = new KafkaAvroJavaProducerV1Demo();
//        producer.main();
        KafkaAvroJavaProducerUsuario producerUsuario = new KafkaAvroJavaProducerUsuario();
        producerUsuario.main();
    }

    @Quando("^eu realizar o consumo dessa mensagem$")
    public void consumir_evento_para_kafka() {
//        KafkaAvroJavaConsumerV1Demo consumer = new KafkaAvroJavaConsumerV1Demo();
//        avroConsumido = consumer.main();
        KafkaAvroJavaConsumerUsuario consumerUsuario = new KafkaAvroJavaConsumerUsuario();
        avroConsumidoUsuario = consumerUsuario.main();
    }

    @Então("^quero ver a idade de \"([^\"]*)\" na mensagem consumida$")
    public void validar_idade_na_mensagem_consumida_kafka(int idade) {
        Assert.assertEquals(idade, avroConsumido.getAge().intValue());
    }

    @Então("^quero ver o pais \"([^\"]*)\"$")
    public void validar_pais_na_mensagem_consumida(String pais) {
        List<informacaoUsuario> result = Arrays.asList(avroConsumidoUsuario);
        List<String> paisavro = result.stream()
                .map(x -> x.getEndereco().getPais())
                .collect(Collectors.toList());
        paisavro.forEach(System.out::println);

        Assert.assertEquals(pais, paisavro.toString());
    }
}
