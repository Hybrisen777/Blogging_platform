package com.uep.wap.repository;

import com.uep.wap.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    //metoda pod szukanie po tagach
    //List<Post> findByTagsContaining(String tag);
}
