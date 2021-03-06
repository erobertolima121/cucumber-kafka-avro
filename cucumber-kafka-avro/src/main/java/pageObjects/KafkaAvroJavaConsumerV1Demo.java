package pageObjects;

import com.example.Customer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

public class KafkaAvroJavaConsumerV1Demo {

    public static Customer main() {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.put("group.id", "customer-consumer-group-v1");
        properties.put("auto.commit.enable", "false");
        properties.put("auto.offset.reset", "earliest");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8085");
        properties.setProperty("specific.avro.reader", "true");

        KafkaConsumer<String, Customer> kafkaConsumer = new KafkaConsumer<>(properties);
        String topic = "customer-avro";
        kafkaConsumer.subscribe(Collections.singleton(topic));

        while (true) {
            ConsumerRecords<String, Customer> records = kafkaConsumer.poll(50);
            try {
                for (ConsumerRecord<String, Customer> record : records) {
                    Customer customer = record.value();
                    if (customer != null) {
                        kafkaConsumer.close();
                        return customer;
                    }
                }
            } catch (Exception kafkaException) {
                kafkaException.printStackTrace();
            }
            kafkaConsumer.commitSync();
        }
    }
}
