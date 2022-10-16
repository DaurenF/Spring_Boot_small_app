package com.TRZ.Taraz.reprository;

import com.TRZ.Taraz.models.Comment;
import com.TRZ.Taraz.models.News;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Iterable<Comment> findAllByNews(News news);
}
