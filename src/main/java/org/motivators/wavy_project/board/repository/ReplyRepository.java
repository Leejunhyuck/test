package org.motivators.wavy_project.board.repository;


import org.motivators.wavy_project.board.domain.Board;
import org.motivators.wavy_project.board.domain.Reply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends CrudRepository<Reply,Long> {
    
    @Query("SELECT r FROM Reply r WHERE r.board = ?1 " +
            " AND r.rno > 0 ORDER BY r.rno ASC")
    public List<Reply> getRepliesOfBoard(Board board);
}