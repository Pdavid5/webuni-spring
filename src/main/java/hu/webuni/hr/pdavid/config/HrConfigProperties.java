package hu.webuni.hr.pdavid.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
@Getter
@Setter
public class HrConfigProperties {

    private Smart smart = new Smart();

    @Getter
    @Setter
    public static class Smart{
        private Limit limit = new Limit();
        private Percent percent = new Percent();
    }

    @Getter
    @Setter
    public static class Limit{
        private int ten;
        private int five;
        private double twoandhalf;
    }

    @Getter
    @Setter
    public static class Percent{
        private int ten;
        private int five;
        private int two;
        private int nothing;
    }

}
