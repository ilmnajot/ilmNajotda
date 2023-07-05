package uz.ilmnajot.samps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditAwareimpl();
    }


    //USTOZ IKKITA SAVOL:

    // 1. MEN BOYA EXCEPTION YOZGANDIM, ApiReponse ni o'rniga exception tashlagan yaxshimi yoki responsemi?
}
