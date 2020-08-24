package pageObjects;

import exemplo.aninhamento.endereco_cobranca;
import exemplo.aninhamento.informacaoUsuario;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaAvroJavaProducerUsuario {

    public static void main() {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("acks", "all");
        properties.setProperty("retries", "10");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8085");

        Producer<String, informacaoUsuario> producer = new KafkaProducer<String, informacaoUsuario>(properties);

        String topic = "usuario-avro";

        informacaoUsuario infouser = informacaoUsuario.newBuilder()
                .setUsuario("Roberto")
                .setIdade(30)
                .setTelefone("11964177962")
                .setNumerocasa("198")
                .setEnderecoBuilder(endereco_cobranca.newBuilder()
                .setRua("Rua Agenor Campanini")
                .setCidade("Salto")
                .setEstado("São Paulo")
                .setPais("Brasil")
                .setCep("13320-000"))
                .build();

        ProducerRecord<String, informacaoUsuario> producerRecord = new ProducerRecord<String, informacaoUsuario>(
                topic, infouser
        );

        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    exception.printStackTrace();
                }
            }
        });

        producer.flush();
        producer.close();
    }
}