package es.urjc.etsii.grafo.HashCode.io;

import es.urjc.etsii.grafo.io.serializers.AbstractSerializerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "serializers.solution-hashcode")
public class HCSolutionIOConfig extends AbstractSerializerConfig {
}
