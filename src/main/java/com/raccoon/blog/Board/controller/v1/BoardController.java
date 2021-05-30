package com.raccoon.blog.Board.controller.v1;

import com.raccoon.blog.Board.domain.Board;
import com.raccoon.blog.Board.dto.BoardDto;
import com.raccoon.blog.Board.dto.PageDto;
import com.raccoon.blog.Board.repository.BoardRepository;
import com.raccoon.blog.Board.vo.PageMaker;
import com.raccoon.blog.Board.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/board/*")
@CrossOrigin
@RestController
@RequiredArgsConstructor
@Log
public class BoardController {
    private final BoardRepository repo;

    @PostMapping("register")
    public ResponseEntity<Board> register(@RequestBody BoardDto boardDto) {
        log.info("hello" + boardDto);
        Board board = Board.builder().title(boardDto.getTitle()).content(boardDto.getContent())
                .writer(boardDto.getWriter()).build();
        repo.save(board);

        return new ResponseEntity<>(board, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<PageMaker> list(PageDto pageDto) {

        log.info("hello" + pageDto);
        PageVO pageVO = PageVO.builder().page(pageDto.getPage()).size(pageDto.getSize()).keyword(pageDto.getKeyword())
                .type(pageDto.getType()).build();

        Pageable page = pageVO.makePageable(0, "bno");
        Page<Board> result = repo.findAll(repo.makePredicate(pageDto.getType(), pageDto.getKeyword()), page);

        log.info("" + page);
        log.info("" + result);
        log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());

        return new ResponseEntity<>(new PageMaker(result), HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<PageVO> delete(Long bno, PageDto pageDto) {
        log.info("delete..." + bno);

        repo.deleteById(bno);

        PageVO pageVO = PageVO.builder().page(pageDto.getPage()).size(pageDto.getSize()).keyword(pageDto.getKeyword())
                .type(pageDto.getType()).build();

        return new ResponseEntity<>(pageVO, HttpStatus.OK);
    }

    @PostMapping("modify")
    public ResponseEntity<PageVO> modify(BoardDto boardDto, PageDto pageDto) {
        log.info("modify..." + boardDto + pageDto);

        repo.findById(boardDto.getBno()).ifPresent(origin -> {

            origin.modifyBoard(boardDto.getTitle(), boardDto.getContent(), boardDto.getWriter());

            repo.save(origin);

        });
        // 페이징과 검색했던 결과로 이동하는경우 처리 필요(PageVO)

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<Board> view(Long bno) {

        log.info("BNO: " + bno);

        Board board = repo.findById(bno).orElseThrow(() -> new IllegalArgumentException("no such data"));

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

}
