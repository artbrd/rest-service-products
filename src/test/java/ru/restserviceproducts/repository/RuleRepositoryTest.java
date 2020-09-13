package ru.restserviceproducts.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.restserviceproducts.entity.Rule;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class RuleRepositoryTest {
    @Autowired
    RuleRepository ruleRepository;

    @Test
    public void findByActiveTrueAndProductIdTest() {
        List<Rule> ruleList = ruleRepository.findByActiveTrueAndProductId(1L);
        assertThat(ruleList).isNotEmpty();
    }

    @Test
    public void findByActiveTrueAndProductIdBadTest() {
        List<Rule> ruleList = ruleRepository.findByActiveTrueAndProductId(10L);
        assertThat(ruleList).isEmpty();
    }

    @Test
    public void findByProductIdAndIdTest() {
        Rule ruleFoud = ruleRepository.findByProductIdAndId(1L, 1L).orElse(null);
        assertThat(ruleFoud).isNotNull();
    }

    @Test
    public void findByProductIdAndIdBadTest() {
        Rule ruleFoud = ruleRepository.findByProductIdAndId(10L, 10L).orElse(null);
        assertThat(ruleFoud).isNull();
    }
}
