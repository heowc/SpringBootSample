package com.tistory.heowc.repository;

import com.tistory.heowc.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}