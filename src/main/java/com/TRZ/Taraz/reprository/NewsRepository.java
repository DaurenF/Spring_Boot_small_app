package com.TRZ.Taraz.reprository;

import com.TRZ.Taraz.models.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Long> {

}
