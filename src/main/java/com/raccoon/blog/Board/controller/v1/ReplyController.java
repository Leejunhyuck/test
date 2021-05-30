package com.raccoon.blog.Board.controller.v1;

import com.raccoon.blog.Board.domain.Board;
import com.raccoon.blog.Board.domain.Reply;
import com.raccoon.blog.Board.dto.ReplyDto;
import com.raccoon.blog.Board.repository.BoardRepository;
import com.raccoon.blog.Board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/v1/replies/*")
@RequiredArgsConstructor
@CrossOrigin
@Log
public class ReplyController {
    private final ReplyRepository replyRepo;
    private final BoardRepository boardRepo;

    @GetMapping
    public ResponseEntity<List<Reply>> getReplies(@PathVariable("bno") Long bno){

        log.info("get All Replies..........................");

        Board board = Board.builder()
                .bno(bno)
                .build();

        return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK );

    }

    @Transactional
    @PostMapping("/{bno}")
    public ResponseEntity<List<Reply>> addReply(@PathVariable("bno") Long bno, @RequestBody ReplyDto replyDto){

        log.info("addReply..........................");
        log.info("BNO: " + bno);
        log.info("REPLY: " + replyDto);

        Board board = Board.builder()
                .bno(bno)
                .build();

        Reply reply = Reply.builder()
                .board(board)
                .build();

        replyRepo.save(reply);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);

    }

    @Transactional
    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<List<Reply>> remove(
            @PathVariable("bno")Long bno,
            @PathVariable("rno")Long rno){

        log.info("delete reply: "+ rno);

        replyRepo.deleteById(rno);

        Board board = Board.builder()
                .bno(bno)
                .build();

        return  new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);

    }

    @Transactional
    @PutMapping("/{bno}")
    public ResponseEntity<List<Reply>> modify(@PathVariable("bno")Long bno,
                                              @RequestBody ReplyDto replyDto){

        log.info("modify reply: "+ replyDto);

        replyRepo.findById(replyDto.getRno()).ifPresent(origin -> {
            origin.modifyReply(replyDto.getReplyText(),replyDto.getReplyer());
            replyRepo.save(origin);
        });

        Board board = Board.builder()
                .bno(bno)
                .build();

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    private List<Reply> getListByBoard(Board board) throws RuntimeException{

        log.info("getListByBoard...." + board);
        return replyRepo.getRepliesOfBoard(board);

    }
}
