package soft.progress.assignment.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soft.progress.assignment.dto.request.DealRequest;
import soft.progress.assignment.entity.Deal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        modelMapper.createTypeMap(DealRequest.class, Deal.class)
                .addMappings(mapper -> mapper.using(ctx -> LocalDate.parse((String) ctx.getSource(), DateTimeFormatter.ofPattern("dd-M-yyyy")))
                        .map(DealRequest::getDealTimestamp, Deal::setDealTimestamp));

        return modelMapper;
    }
}
