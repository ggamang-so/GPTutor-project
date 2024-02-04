package com.ggamangso.gptutorproject.repository;

import com.ggamangso.gptutorproject.config.JpaConfig;
import com.ggamangso.gptutorproject.constant.AuthorityType;
import com.ggamangso.gptutorproject.domain.Message;
import com.ggamangso.gptutorproject.domain.UserAccount;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("JPA 연결 테스트")
@Import(JpaRepositoryTest.TestJpaConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class JpaRepositoryTest {

    @MockBean
    private final MessageRepository messageRepository;
    @MockBean
    private final UserAccountRepository userAccountRepository;


//    Constructor 주입방식으로 의존성 주입
    public JpaRepositoryTest(@Autowired MessageRepository messageRepository,
                             @Autowired UserAccountRepository userAccountRepository) {
        this.messageRepository = messageRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorkFine() {
        //Given

        //When
        List<Message> messages = messageRepository.findAll();
        //Then
        assertThat(messages)
                .isNotNull()
                .hasSize(100);
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorkFine(){
        //Given
        long previousCount = userAccountRepository.count();
        UserAccount userAccount = UserAccount.of("test_ggamangso", "testtest", AuthorityType.USER, "test@mail.com", "ggamangso", null);
        //When
        userAccountRepository.save(userAccount);
        //Then
        assertThat(userAccountRepository.count()).isEqualTo(previousCount + 1);

    }


//    @DisplayName("update 테스트")
//    @Test
//    void givenTestData_whenUpdating_thenWorkFine(){
//        //Given
//        Article article = articleRepository.findById(1L).orElseThrow();
//        String updatedHashtag = "#springboot";
//        article.setHashtag(updatedHashtag);
//        //When
//        Article savedArticle = articleRepository.saveAndFlush(article);
//        //Then
//        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag) ;
//
//    }
//
//    @DisplayName("delete 테스트")
//    @Test
//    void givenTestData_whenDeleting_thenWorkFine(){
//        //Given
//        Article article = articleRepository.findById(1L).orElseThrow();
//        long previousArticleCount = articleRepository.count();
//        long previousArticleCommentCount = articleCommentRepository.count();
//        int deletedCommentsSize = article.getArticleComments().size();
//
//        //When
//        articleRepository.delete(article);
//
//        //Then
//        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
//        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
//    }

    @EnableJpaAuditing
    @TestConfiguration
    static class TestJpaConfig {
        @Bean
        AuditorAware<String> auditorAware() {
            return () -> Optional.of("ggamangso");
        }
    }
}
