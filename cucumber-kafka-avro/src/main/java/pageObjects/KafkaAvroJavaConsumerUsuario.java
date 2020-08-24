package pageObjects;

import exemplo.aninhamento.informacaoUsuario;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaAvroJavaConsumerUsuario {

    public static informacaoUsuario main() {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.put("group.id", "customer-consumer-group-v1");
        properties.put("auto.commit.enable", "false");
        properties.put("auto.offset.reset", "earliest");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8085");
        properties.setProperty("specific.avro.reader", "true");

        KafkaConsumer<String, informacaoUsuario> kafkaConsumer = new KafkaConsumer<>(properties);
        String topic = "usuario-avro";
        kafkaConsumer.subscribe(Collections.singleton(topic));

        while (true) {
            ConsumerRecords<String, informacaoUsuario> records = kafkaConsumer.poll(50);
            try {
                for (ConsumerRecord<String, informacaoUsuario> record : records) {
                    informacaoUsuario usuario = record.value();
                    if (usuario != null) {
                        kafkaConsumer.close();
                        return usuario;
                    }
                }
            } catch (Exception kafkaException) {
                kafkaException.printStackTrace();
            }
            kafkaConsumer.commitSync();
        }
    }
}
