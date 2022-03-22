package com.web.wam.model.repository.freeboard;

import com.web.wam.model.entity.freeboard.FreeArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeArticleCommentRepository extends JpaRepository<FreeArticleComment, Integer> {
}